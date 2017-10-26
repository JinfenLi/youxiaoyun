package com.topview.school.controller.curricula;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.school.po.Clazz;
import com.topview.school.po.Curricula;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.po.Teacher;
import com.topview.school.po.UserRoleKey;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.clazz.curricula.CurriculaService;
import com.topview.school.service.clazz.curricula.CurriculaVariableService;
import com.topview.school.service.clazz.exam.ExamService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.service.system.authc.RoleType;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.ClassLoaderUtil;
import com.topview.school.util.DownloadAndUploadUtil;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.curricula.ClassCurriculaInfo;
import com.topview.school.vo.curricula.CurriculaVariableInfo2;
import com.topview.school.vo.curricula.CurriculaVo;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;
import com.topview.school.vo.curricula.result.ClassCurriculaInfoResult;
import com.topview.school.vo.school.ClazzInfo;

/**
 * @Description 课程controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:48:04
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/curricula", produces = "text/html;charset=UTF-8")
public class CurriculaController {

	@Resource
	private CurriculaService curriculaService;
	@Resource
	private CurriculaVariableService curriculaVariableService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private ExamService examService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private SemesterService semesterService;
	@Autowired
	private UserRoleService roleService;
	private static final String roleId = RoleType.CURRICULA_MANAGER.value();

	/**
	 * 批量创建课程
	 * 
	 * @param gradesId
	 * @param curricula
	 * @return
	 */
	@RequestMapping("/createCurriculas")
	@ResponseBody
	public String createCurriculas(String[] gradesName, Curricula curricula) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int i = 0;
		curricula.setCreateTime(new Date());
		String name = curricula.getName();
		for (; i < gradesName.length; i++) {
			curricula.setId(UUIDUtil.getUUID());
			curricula.setAdaptiveGrade(gradesName[i]);
			curricula.setName(gradesName[i]+name+curricula.getAdaptiveTerm().substring(0, 1));
			if (curriculaService.createCurricula(curricula) == null) {
				break;
			}
		}
		if (i != gradesName.length) {
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 创建课程(web端接口,需登录)
	 * 
	 * @param curricula
	 * @return
	 */
	@RequestMapping("/createCurricula")
	@ResponseBody
	public String createCurricula(Curricula curricula) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		curricula.setId(UUIDUtil.getUUID());
		curricula.setCreateTime(new Date());
		curricula = curriculaService.createCurricula(curricula);

		String tId = null;
		if (curricula != null) {
			if ((tId = curricula.gettScTeacherId()) != null && !"".equals(tId)) {
				addRole(tId);// 添加新的负责人
			}
			resultMap.put("curricula", CurriculaVo.changeToVo(curricula));
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "课程信息不合法");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 获取指定学科下的所有课程 (web端接口，需登录)
	 * 
	 * @param subjectId
	 * @return
	 */
	@RequestMapping("/getCurriculaBySubjectId")
	@ResponseBody
	public String getCurriculaBySubjectId(String subjectId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CurriculaVo> curriculaVos = curriculaService
				.getCurriculaBySubjectId(subjectId);
		if (curriculaVos != null) {
			for (int i = 0; i < curriculaVos.size(); i++) {
				Teacher teacher = teacherService.selectTeacherById(curriculaVos
						.get(i).gettScTeacherId()); // 查询课程负责人
				if (teacher != null) {
					curriculaVos.get(i).setTeacherName(teacher.getName());
				}
			}
			resultMap.put("curriculas", curriculaVos);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除课程 (web端接口 ,需登录) //TODO烂到没眼看
	 * 
	 * @param curriculaId
	 * @return
	 */
	@RequestMapping("/delectCurricula")
	@ResponseBody
	public String delectCurricula(String curriculaId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Curricula c = curriculaService.selectByPrimaryKey(curriculaId);
		String tId = null;
		if (c != null) {
			tId = c.gettScTeacherId();
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}

		if (curriculaService.delectCurricula(curriculaId)) {
			if (tId != null) {
				delRole(tId);// 删除课程负责人角色
			}
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "尚有其他重要信息关联，无法删除");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 更新课程信息 (web端接口，需登录) //TODO看不下去了
	 * 
	 * @param curricula
	 * @return
	 */
	@RequestMapping("/updateCurricula")
	@ResponseBody
	public String updateCurricula(Curricula curricula) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String tId = null;
		if (curricula != null) {

			String cId = curricula.getId();
			tId = curricula.gettScTeacherId();// 新的负责人
			Curricula c = curriculaService.selectByPrimaryKey(cId);// 旧的负责人
			if (c != null && c.gettScTeacherId() != null
					&& !tId.equals(c.gettScTeacherId())) {

				delRole(c.gettScTeacherId());// 删除旧的负责人
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "课程信息不合法");
		}

		if (curriculaService.updateCurricula(curricula)) {
			if (tId != null && !"".equals(tId)) {
				addRole(tId);// 添加新的负责人
			}
			resultMap.put("success", true);
			resultMap.put("curricual", curricula);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "课程信息不合法");
		}
		return JSONUtil.toObject(resultMap).toString();

	}

	/**
	 * 
	 * 获取课程表信息 (web端、移动端共用接口)
	 * 
	 * @param @param session
	 * @param @param clazzId
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/getCurricula")
	@ResponseBody
	public String getCurricula(HttpSession session, String clazzId,
			String semesterId) {
		return JSONUtil.toObject(
				curriculaService.getCurricula(session, clazzId, semesterId))
				.toString();
	}

	/**
	 * 导出课程表 web端接口
	 * 
	 * @param request
	 * @param respone
	 * @param @return
	 * @param @throws Exception
	 * @return ResponseEntity<byte[]>
	 * @throws
	 */
	@RequestMapping(value = "/downloadCurricula", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadCurricula(HttpServletRequest request,
			String clazzId, HttpSession session, String semesterId)
			throws Exception {

		ClassCurriculaInfoResult result = curriculaService.getCurricula(
				session, clazzId, semesterId);
		List<ClassCurriculaInfo> curriculaInfos = result.getResult(); // 课程表VO
		String comment = result.getComment(); // 课表备注
		// 处理文件路径
		String fileName = "课程表";
		String relativePath = ClassLoaderUtil.getExtendResource("../"
				+ "schoolUpload/curricula", "school");
		String realPath = relativePath.replace("/", "\\");
		File file0 = new File(realPath);
		if (!file0.exists()) {
			file0.mkdirs();
		}
		String filePath = realPath + "\\" + fileName;
		// 生成excel表
		createCurriculaExcel(filePath, curriculaInfos, comment);
		File file = new File(filePath);
		return DownloadAndUploadUtil.download(request, file, fileName);
	}

	/**
	 * 将上课时间信息填充进excel表,下载课表使用
	 * 
	 * @param filePath
	 * @param curriculaInfos
	 */
	private void createCurriculaExcel(String filePath,
			List<ClassCurriculaInfo> curriculaInfos, String comment) {
		// 声明变量
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		CellStyle style = wb.createCellStyle();
		style.setAlignment((short) 1);
		Row[] row = new Row[15];
		for (int i = 0; i < 15; i++) {
			row[i] = sheet.createRow(i);
		}

		row[0].createCell(1).setCellValue("周一");
		row[0].createCell(2).setCellValue("周二");
		row[0].createCell(3).setCellValue("周三");
		row[0].createCell(4).setCellValue("周四");
		row[0].createCell(5).setCellValue("周五");
		row[0].createCell(6).setCellValue("周六");
		row[0].createCell(7).setCellValue("周日");

		row[1].createCell(0).setCellValue("第一节");
		row[2].createCell(0).setCellValue("第二节");
		row[3].createCell(0).setCellValue("第三节");
		row[4].createCell(0).setCellValue("第四节");
		row[5].createCell(0).setCellValue("第五节");
		row[6].createCell(0).setCellValue("第六节");
		row[7].createCell(0).setCellValue("第七节");
		row[8].createCell(0).setCellValue("第八节");

		// 填充内容
		for (int i = 0; i < curriculaInfos.size(); i++) {
			ClassCurriculaInfo c = curriculaInfos.get(i);
			int section = c.getSection(); // 第几节，等同于第几行
			int week = c.getWeek(); // 周几，等同于第几列
			row[section].createCell(week).setCellValue(c.getName());
		}
		row[9].createCell(0).setCellValue("备注:");
		row[9].createCell(1).setCellValue(comment);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 上传课程表 web端接口
	 * 
	 * @param file
	 * @param request
	 * @param clazzId
	 * @param semesterId
	 * @return
	 */
	@RequestMapping(value = "/uploadCurriculaExcel", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCurriculaExcel(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, String clazzId, String semesterId,
			HttpSession session) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		FileUploadVo vo = FileUploadUtil.uploadFile(file,
				userInfo.getSchool_id() + "/curricula", request, true);
		if (curriculaService.uploadCurricula(vo.getFileName(), vo.getRealPath()
				+ "\\" + vo.getFileName(), clazzId, semesterId)) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 选课时展示可选择的课程信息和相应班级 web端接口
	 * 
	 * @param subjectId
	 * @param adaptiveGrade
	 * @param adaptiveTerm
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/chooseCurricula")
	@ResponseBody
	public String chooseCurricula(String subjectId, String adaptiveGrade,
			String adaptiveTerm, String gradeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Clazz> clazzs = clazzService.getClazzByGradeId(gradeId);
		List<ClazzInfo> infos = ClazzInfo.changeToVo(clazzs);
		if (adaptiveTerm.contains("秋") || adaptiveTerm.contains("上")) { // TODO硬编码
			adaptiveTerm = "%上%";
		} else {
			adaptiveTerm = "%下%";
		}
		resultMap.put("tScSubjectId", subjectId);
		resultMap.put("adaptiveGrade", adaptiveGrade);
		resultMap.put("adaptiveTerm", adaptiveTerm);
		List<Curricula> curriculas = curriculaService.getCurriculas(resultMap);
		resultMap.clear();
		resultMap.put("success", true);
		resultMap.put("clazzs", infos);
		resultMap.put("curriculas", curriculas);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 保存班级选课信息 web端接口
	 * 
	 * @param curriculaVariable
	 * @return
	 */
	@RequestMapping("/saveCurriculaVariable")
	@ResponseBody
	public String saveCurriculaVariable(CurriculaVariable curriculaVariable) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		curriculaVariable.setId(UUIDUtil.getUUID());
		curriculaVariable.setCreateTime(new Date());
		if (!curriculaVariableService.saveCurriculaVariable(curriculaVariable)) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除选课记录 web端接口
	 * 
	 * @param curriculaVariableId
	 * @return
	 */
	@RequestMapping("deleteCurriculaVariable")
	@ResponseBody
	public String deleteCurricualVariable(String curriculaVariableId) {
		Map<String, Object> resulMap = new HashMap<String, Object>();
		boolean flag = curriculaVariableService
				.deleteCurriculaVariable(curriculaVariableId);
		resulMap.put("success", true);
		if (!flag) {
			resulMap.put("success", false);
			resulMap.put("msg", "有其他重要数据关联，无法删除！");
		}
		return JSONUtil.toObject(resulMap).toString();
	}

	/**
	 * 根据年级、学科、学期或老师单条件或多条件查看选课情况 web端接口
	 * 
	 * @param gradeId
	 * @param subjectId
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	@RequestMapping("/showCurriculaVariable")
	@ResponseBody
	public String showCurriculaVariable(String gradeId, String subjectId,
			String teacherId, String semesterId, Integer start, Integer limit) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!NotEmptyString.isAtLeastOneNotEmpty(new String[] { gradeId,
				subjectId, teacherId, semesterId })) {
			resultMap.put("success", true);
			resultMap.put("msg", "查询条件不足");
			return JSONUtil.toObject(resultMap).toString();
		}

		List<CurriculaVariableInfo2> info = curriculaVariableService
				.showCurriculaVariable(subjectId, gradeId, teacherId,
						semesterId, start, limit); // 查询选课情况
		resultMap.put("totalCount", curriculaVariableService.selectCount(
				subjectId, gradeId, teacherId, semesterId)); // 查询记录数
		resultMap.put("success", true);
		resultMap.put("info", info);

		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 显示当前班级至少考过一次试的课程（学习成绩模块接口） 移动端——家长接口
	 * 
	 * @param session
	 * @param semesterId
	 * @param clazzId
	 * @return
	 */
	@RequestMapping("selectCurriculaByClazzIdAndSemsterId")
	@ResponseBody
	public String selectCurriculaByClazzIdAndSemsterId(HttpSession session,
			String semesterId, String clazzId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UploadCurriculaInfoVo> curriculaInfoVos = new ArrayList<UploadCurriculaInfoVo>();
		List<UploadCurriculaInfoVo> result = new ArrayList<UploadCurriculaInfoVo>();
		String[] filter = { "semesterId", "clazzId", "curriculaName",
				"curriculaId" };
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		resultMap.put("success", true);

		if (semesterId == null || "".equals(semesterId)) {
			semesterId = semesterService.getCurrentSemester(
					userInfo.getSchool_id()).getId();
		}
		if (clazzId == null || "".equals(clazzId)) {
			clazzId = userInfo.getClass_id();
		}

		if (userInfo.getUser_type().ordinal() == 1) { // 如果是老师
			boolean isHeadTeacher = userInfo.getTeacher_info().isHeadTeacher(); // 是否是班主任
			String leadingClazzId = userInfo.getTeacher_info() // 是哪个班的班主任
					.getLeading_class();
			if (isHeadTeacher && !clazzId.equals(leadingClazzId)) { // 如果是班主任但不是当前班级的班主任,只查所教的课程
				curriculaInfoVos = curriculaVariableService
						.getSubjectBySemesterIdAndClazzId(semesterId, clazzId,
								userInfo.getUser_id());
			} else if (!isHeadTeacher) { // 如果只是科任只查所教的课程
				curriculaInfoVos = curriculaVariableService
						.getSubjectBySemesterIdAndClazzId(semesterId, clazzId,
								userInfo.getUser_id());
			} else if (isHeadTeacher && clazzId.equals(leadingClazzId)) { // 如果是该班级的班主任则查询所有课程
				curriculaInfoVos = curriculaVariableService
						.getSubjectBySemesterIdAndClazzId(semesterId, clazzId,
								null);
			} else {
				resultMap.put("success", false);
				resultMap.put("msg", "当前教师没有权限查看");
				return JSONUtil.toObject(resultMap).toString();
			}
		} else if (userInfo.getUser_type().ordinal() == 2) {
			curriculaInfoVos = curriculaVariableService
					.getSubjectBySemesterIdAndClazzId(semesterId, clazzId, null);
		}
		// 过滤掉没有开考过的课程
		for (int i = 0; i < curriculaInfoVos.size(); i++) {
			String cvId = curriculaInfoVos.get(i).getCurriculaVariableId();
			if (examService.selectByCurriculaVariableId(cvId).size() > 0) {
				result.add(curriculaInfoVos.get(i));
			}
		}
		resultMap.put("curriculaInfo", result);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 
	 * @Title: addRole
	 * @Description: TODO
	 * @param @param tId
	 * @return void
	 * @throws
	 */
	private void addRole(String tId) {

		if (!roleService.hasRole(tId, roleId)) {

			UserRoleKey key = new UserRoleKey(tId, roleId);
			roleService.insert(key);// 添加新的负责人
		}
	}

	/**
	 * 
	 * @Title: delRole
	 * @Description: TODO
	 * @param @param tId
	 * @return void
	 * @throws
	 */
	private void delRole(String tId) {

		UserRoleKey key = new UserRoleKey(tId, roleId);
		roleService.deleteByPrimaryKey(key);
	}

}
