package com.topview.school.controller.user;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.message.service.PushMsgService;
import com.topview.message.util.UUIDUtil;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.school.po.Teacher;
import com.topview.school.po.ValidationResult;
import com.topview.school.service.system.authc.RoleType;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.service.user.UserService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.ClassLoaderUtil;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.DownloadAndUploadUtil;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.util.ValidationUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.TeacherExcelInfo;
import com.topview.school.vo.User.TeacherLoginVo;
import com.topview.school.vo.User.TeacherVo;
import com.topview.school.vo.User.UserInfo;

@Controller
@RequestMapping(value = "/teacher", produces = "text/html;charset=UTF-8")
public class TeacherController {

	@Resource
	private TeacherService teacherService;
	private String[] filter = { "password" };
	@Autowired
	private UserRoleService roleService;
	@Autowired
	private UserService userService;
	@Resource
	private PushMsgService pushMsgService;

	/**
	 * 根据学校id获取全部教师\根据年级id获取级长\根据班级id获取班主任
	 * 
	 * @param session
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/getAllTeacher")
	@ResponseBody
	public String getAllTeacher(HttpSession session, String schoolId,
			String gradeId, String clazzId, Integer start, Integer limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int totalCount = 0;
		if (start != null && limit != null && !"".equals(start)
				&& !"".equals(limit)) {
			resultMap.put("offset", start);
			resultMap.put("limit", limit);
		}
		if (schoolId != null && !"".equals(schoolId)) {
			resultMap.put("schoolId", schoolId);
			totalCount = teacherService.selectCount(schoolId);
		} else if (gradeId != null && !"".equals(gradeId)) {
			resultMap.put("gradeId", gradeId);
		} else if (clazzId != null && !"".equals(clazzId)) {
			resultMap.put("clazzId", clazzId);
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		List<Teacher> t = teacherService.getTeacher(resultMap);
		resultMap.clear();

		if (t.size() > 0) {
			resultMap.put("success", true);

			List<TeacherVo> ts = TeacherVo.changeToVo(t);
			for (TeacherVo v : ts) {

				if (v != null
						&& roleService.hasRole(v.getId(),
								RoleType.SCHOOL_MANAGER.value())) {

					v.setIsAuthc("1");
				} else {
					v.setIsAuthc("0");
				}
			}
			resultMap.put("teachers", ts);
			if (totalCount > 0) {
				resultMap.put("totalCount", totalCount);
			}
		} else {
			resultMap.put("success", false);
		}

		return JSONUtil.toObject(resultMap, filter).toString();
	}

	@RequestMapping("/getAllteacherAndTime")
	@ResponseBody
	public String getAllTeacherByLoginOrNotLogin(HttpSession session, String schoolId, Integer start, Integer limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int totalCount = 0;
		if (start != null && limit != null && !"".equals(start)
				&& !"".equals(limit)) {
			resultMap.put("offset", start);
			resultMap.put("limit", limit);
		}
		if (schoolId != null && !"".equals(schoolId)) {
			resultMap.put("schoolId", schoolId);
			totalCount = teacherService.selectCount(schoolId);
		}else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		List<Teacher> t = teacherService.getTeacher(resultMap);
		resultMap.clear();

		if (t.size() > 0) {
			resultMap.put("success", true);

			List<TeacherVo> ts = TeacherVo.changeToVo(t);
			List<TeacherLoginVo> tlv = TeacherLoginVo.changeToVO(ts);
			for (TeacherLoginVo v : tlv) {

				if (v != null
						&& roleService.hasRole(v.getId(),
								RoleType.SCHOOL_MANAGER.value())) {

					v.setIsAuthc("1");
				} else {
					v.setIsAuthc("0");
				}
				Date date = userService.getLoginStatusByUserId(v.getId());
				if(date == null) {
					v.setLoginStatus("暂未登陆");
				} else {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					v.setLoginStatus(df.format(userService.getLoginStatusByUserId(v.getId())));
				}
				
			}
			resultMap.put("teachers", tlv);
			if (totalCount > 0) {
				resultMap.put("totalCount", totalCount);
			}
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 根据部门id或学科id获取老师
	 * 
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("/selectTeacherByDepartmentId")
	@ResponseBody
	public String selectTeacherByDepartmentId(String departmentId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Teacher> teachers = teacherService
				.selectTeacherByDepartmentId(departmentId);
		if (teachers.size() > 0) {
			List<TeacherVo> infos = TeacherVo.changeToVo(teachers);
			resultMap.put("success", true);
			resultMap.put("teachers", infos);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 更新教师信息
	 * 
	 * @param teacherInfo
	 * @return
	 */
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public String updateTeacher(TeacherVo teacherInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(teacherInfo);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		List<Teacher> teachers = new ArrayList<Teacher>();   //前端要求返回集合
		if(teacherInfo.getId() == null || "".equals(teacherInfo.getId())) {
			resultMap.put("success", false);
			resultMap.put("teachers", teachers);
		}else {
			Teacher teacher = teacherService.judgeTeacher(teacherInfo.getPhone());
			if(null != teacher) {
				if(!teacher.getId().equals(teacherInfo.getId())) {
					teachers.add(teacher);
					resultMap.put("success", false);
					resultMap.put("teachers", teachers);
					String[] filter={"createTime","lastUpdate","education","birthday","email","id"
							,"idcard","password","phone","picUrl","sex","tScClassId","tScGradeId","tScSchoolId"};
					return JSONUtil.toObject(resultMap,filter).toString();
				}
			}
			teacher = TeacherVo.changeToPo(teacherInfo);
			boolean flag = teacherService.updateTeacherInfo(teacher);
			resultMap.put("success", flag);
			sendContact(teacherInfo.gettScSchoolId(),teacherInfo.getId());
		}
		
		
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 更新多个教师信息
	 * 
	 * @param teacherInfo
	 * @return
	 */
	@RequestMapping(value="/updateTeachers",method ={RequestMethod.POST})
	@ResponseBody
	public String updateTeachers(@RequestBody TeacherVo[] teacherInfos) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		for(TeacherVo teacherInfo : teacherInfos) {
			teacherInfo.setBirthday(null);                     //因为前端会传birthday过来，为防止后面代码出错在这里设为空
			ValidationResult res = ValidationUtil.validateEntity(teacherInfo);
			if(res.isHasErrors()) {
				resultMap.put("success", false);
				resultMap.put("errorMsg", res.getErrorMsg());
				return JSONUtil.toObject(resultMap).toString();
			}
			if(null == teacherInfo.getId()|| "".equals(teacherInfo.getId())) {
				resultMap.put("success", false);
				return JSONUtil.toObject(resultMap).toString();
			}else {
				Teacher teacher = teacherService.judgeTeacher(teacherInfo.getPhone());
				if(null != teacher) {
					if(!teacher.getId().equals(teacherInfo.getId())) {
						boolean bool = this.judgeAndUpdate(teacherInfo.getId(),teacher, teacherInfos);
						
						if(!bool) {
							teacher.setName(teacherInfo.getName());
							teachers.add(teacher);
							continue;
						}
					}
				}
				Teacher t = TeacherVo.changeToPo(teacherInfo);
				boolean flag = teacherService.updateTeacherInfo(t);
				resultMap.put("success", flag);
				if (!flag) {
					resultMap.put("msg", "请检查数据是否正确，联系方式有无错误");
					return JSONUtil.toObject(resultMap).toString();
				}
				sendContact(teacherInfo.gettScSchoolId(),teacherInfo.getId());
			}
		}
		resultMap.put("success", true);
		resultMap.put("teachers", teachers);
		String[] filter={"createTime","lastUpdate","education","birthday","email","id"
				,"idcard","password","phone","picUrl","sex","tScClassId","tScGradeId","tScSchoolId"};
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	/**
	 * 任命老师作为班主任或年级级长
	 * 
	 * @param session
	 * @param clazzId
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/appointTeacher")
	@ResponseBody
	public String appointTeacher(HttpSession session, String teacherId,
			String clazzId, String gradeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);

		if (clazzId != null && !"".equals(clazzId)) {
			teacher.settScClassId(clazzId);
		} else if (gradeId != null && !"".equals(gradeId)) {
			teacher.settScGradeId(gradeId);
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success", teacherService.updateTeacherInfo(teacher));
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 导出教师模板
	 * 
	 * @param @param request
	 * @param @param respone
	 * @param @return
	 * @param @throws Exception
	 * @return ResponseEntity<byte[]>
	 * @throws
	 */
	@RequestMapping(value = "/downloadTeacherInfo", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadCurricula(HttpServletRequest request,
			HttpServletResponse respone) throws Exception {

		String root = request.getSession().getServletContext()
				.getRealPath("/upload");
		File folder = new File(root);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String fileName = "教师信息表";
		String path = root + "\\" + fileName + ".xls";

		File file = new File(path);
		if (!file.exists()) { // 若模板文件不存在则创建
			teacherService.createNullExcel(path);
		}

		return DownloadAndUploadUtil.download(request, file, fileName);
	}

	/**
	 * 上传教师信息表Excel
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/uploadTeacherInfo", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCurricula(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session, String schoolId)
			throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		
		FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/teacherInfo",
				request, true);

		if (schoolId != null && !"".equals(schoolId)) {
			userInfo.setSchool_id(schoolId);
		}
		try {
			if (teacherService.uploadTeacherInfo(vo.getFileName(),
					vo.getRealPath(), userInfo,teachers)) {
				sendContact(userInfo.getSchool_id(),userInfo.getUser_id());
				resultMap.put("success", true);
				resultMap.put("teachers", teachers);
			} else {
				resultMap.put("success", false);
			}
		} catch (Exception e) {
			resultMap.put("success", false);
		}
		String[] filter={"createTime","lastUpdate","education","birthday","email","id"
				,"idcard","password","phone","picUrl","sex","tScClassId","tScGradeId","tScSchoolId"};
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	/**
	 * 添加教师职位
	 * 
	 * @param positionId
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/addPosition", method = RequestMethod.POST)
	@ResponseBody
	public String addPosition(String positionId, String teacherId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (positionId == null || "".equals(positionId)) {
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if (teacherId == null || "".equals(teacherId)) {
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", teacherService.addPosition(positionId, teacherId));
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 删除教师职位
	 * 
	 * @param positionId
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value = "/deletePosition")
	@ResponseBody
	public String deletePosition(String positionId, String teacherId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (positionId == null || "".equals(positionId)) {
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if (teacherId == null || "".equals(teacherId)) {
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result",
				teacherService.deletePosition(positionId, teacherId));
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 根据职位id获取教师
	 * 
	 * @param positionId
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping("getTeacherByPositionId")
	@ResponseBody
	public String getTeacherByPositionId(String positionId, Integer start,
			Integer limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Teacher> teachers = teacherService.selectTeacherByPositionId(
				positionId, start, limit);
		resultMap.put("success", true);
		resultMap.put("teachers", TeacherVo.changeToVo(teachers));
		resultMap.put("totalCount",
				teacherService.getCountByPositionId(positionId));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除教师
	 * 
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteTeacher(HttpSession session,String teacherId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if(userInfo == null) {
			resultMap.put("success", false);
			resultMap.put("error","未登录或者session失效");
			return JSONUtil.toObject(resultMap).toString();
		}
		String statement = teacherService.newDeleteTeacher(teacherId);
		if(statement != null) {
			resultMap.put("error", statement);
			resultMap.put("success", false);
		} else {
			resultMap.put("success", true);
			sendContact(userInfo.getSchool_id(),userInfo.getUser_id());
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据名称模糊查询教师
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("findLike")
	@ResponseBody
	public String findLike(String name, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String[] filter = { "birthday", "createTime", "education", "password",
				"tScClassId", "tScGradeId", "tScSchoolId" };
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo == null) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		params.put("name", name);
		params.put("t_sc_school_id", userInfo.getSchool_id());
		param.put("params", params);
		List<TeacherVo> teacherVos = teacherService.findLike(param);
		resultMap.put("success", true);
		resultMap.put("teachers", teacherVos);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 单独添加一个教师
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("addTeacher")
	@ResponseBody
	public String addTeacher(TeacherVo vo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(vo);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		Teacher teacher = teacherService.judgeTeacher(vo.getPhone());
		List<Teacher> teachers = new ArrayList<Teacher>();   //前端要求返回集合
		if(null != teacher) {
			teachers.add(teacher);
			resultMap.put("success", false);
			resultMap.put("teachers", teachers);
			String[] filter={"createTime","lastUpdate","education","birthday","email","id"
					,"idcard","password","phone","picUrl","sex","tScClassId","tScGradeId","tScSchoolId"};
			return JSONUtil.toObject(resultMap,filter).toString();
		}
		resultMap.put("teachers", teachers);
		vo.setId(UUIDUtil.getUUID());
		boolean flag = teacherService.addTeacher(vo);
		if(flag) {
			sendContact(vo.gettScSchoolId(),vo.getId());
		}
		resultMap.put("success", flag);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * @dateTime 2016年6月2日下午12:23:57
	 * @author zjd
	 * @description 下载教师Excel表格
	 */
	@RequestMapping(value = "/downloadTeacher", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadTeacher(
			HttpServletRequest request, HttpServletResponse respone,
			String schoolId) throws Exception {
		List<TeacherExcelInfo> listExcelInfo = new ArrayList<TeacherExcelInfo>();
		
		if(schoolId != null && !"".equals(schoolId)) {
			List<Teacher> list = teacherService.getTeacherBySchoolId(schoolId);
			for(Teacher teacher :list) {
				TeacherExcelInfo teacherExcelInfo = new TeacherExcelInfo();
				if (teacher.getBirthday() != null && !"".equals(teacher.getBirthday())) {
					teacherExcelInfo.setBirthday(DateFormatUtil.formatDateToDay(teacher
							.getBirthday()));
				}
				teacherExcelInfo.setEducation(teacher.getEducation());
				teacherExcelInfo.setEmail(teacher.getEmail());
				teacherExcelInfo.setIdcard(teacher.getIdcard());
				teacherExcelInfo.setName(teacher.getName());
				teacherExcelInfo.setPhone(teacher.getPhone());
				teacherExcelInfo.setSex(teacher.getSex());
				listExcelInfo.add(teacherExcelInfo);
			}
		}
		// 3.处理目标文件路径
		String fileName = "教师信息";
		String relativePath = ClassLoaderUtil.getExtendResource(
				"../schoolUpload/teacher", "school").toString();
		String realPath = relativePath.replace("/", "\\");
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String filePath = realPath + "\\" + fileName;
		// 4.生成excel文件
		teacherService.createTeacherExcel(filePath, listExcelInfo);
		File targetFile = new File(filePath);
		return DownloadAndUploadUtil.download(request, targetFile, fileName);
	}
	
	/**
	 * @dateTime 2016年9月12日下午9:49:55
	 * @author zjd
	 * @description 判断该教师是否为班主任
	 */
	@RequestMapping("judgeHeadTeacher")
	@ResponseBody
	public String judgeHeadTeacher(String teacherId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		if(NotEmptyString.isNotNullString(teacherId)) {
			Teacher teacher = teacherService.selectTeacherById(teacherId);
			if(null != teacher) {
				if(NotEmptyString.isNotNullString(teacher.gettScClassId())) {
					result.put("success", true);
				}
			}
		}
		return JSONUtil.toObject(result).toString();
	}
	
	/**
	 * @dateTime 2016年9月17日下午2:33:46
	 * @author zjd
	 * @description 判断该教师是否为级长
	 */
	@RequestMapping("judgePraepostor")
	@ResponseBody
	public String judgePraepostor(String teacherId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		if(NotEmptyString.isNotNullString(teacherId)) {
			Teacher teacher = teacherService.selectTeacherById(teacherId);
			if(null != teacher) {
				if(NotEmptyString.isNotNullString(teacher.gettScGradeId())) {
					result.put("success", true);
				}
			}
		}
		return JSONUtil.toObject(result).toString();
	}
	
	//批量修改老师时，解决号码互换等问题
	public boolean judgeAndUpdate(String oldId, Teacher teacher, TeacherVo[] teacherInfos) {
		for(TeacherVo vo : teacherInfos) {
			if(teacher.getId().equals(vo.getId()) && !teacher.getPhone().equals(vo.getPhone())) {
				Teacher t1 = teacherService.judgeTeacher(vo.getPhone());   
				if(null == t1 ) {
					Teacher t2 = TeacherVo.changeToPo(vo);
					return teacherService.updateTeacherInfo(t2);
				}else if(oldId.equals(t1.getId())) {
					t1.setPhone("null");
					if(teacherService.updateTeacherInfo(t1)) {
						Teacher t2 = TeacherVo.changeToPo(vo);
						return teacherService.updateTeacherInfo(t2);
					}else {
						return false;
					}
				}else if(null != t1) {
					if(judgeAndUpdate(oldId,t1,teacherInfos)) {    //递归
						Teacher t2 = TeacherVo.changeToPo(vo);
						return teacherService.updateTeacherInfo(t2);
					}else {
						return false;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据名字模糊查询教师	
	 * @date	2016-11-20	
	 * @param session
	 * @param schoolId		学校id
	 * @param keyword		关键字
	 * @param start			分页的开始索引
	 * @param limit			每页显示条数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/findLikeByName")
	@ResponseBody
	public String findLikeByName(HttpSession session,String schoolId,String keyword,Integer start, Integer limit) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if(userInfo == null) {
			resultMap.put("success", false);
			resultMap.put("error","未登录或者session失效");
			return JSONUtil.toObject(resultMap).toString();
		}
		int totalCount = 0;
		if (start != null && limit != null && !"".equals(start)
				&& !"".equals(limit) && start >=0 && limit >=0 && keyword != null && !"".equals(keyword.trim())) {
			//resultMap.put("offset", start);
			//resultMap.put("limit", limit);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "非法请求");
			return JSONUtil.toObject(resultMap).toString();
		}
		
		//String newKeyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		List<Teacher> list = teacherService.findLikeByName(keyword, schoolId,start,limit);
		totalCount = teacherService.countFindLike(keyword, schoolId);
		if(list == null) {
			resultMap.put("success", false);
		} else {
				if (list.size() > 0) {
					resultMap.put("success", true);
					
					List<TeacherVo> ts = TeacherVo.changeToVo(list);
					for (TeacherVo v : ts) {
	
						if (v != null
								&& roleService.hasRole(v.getId(),
										RoleType.SCHOOL_MANAGER.value())) {
	
							v.setIsAuthc("1");
						} else {
							v.setIsAuthc("0");
						}
					}
				resultMap.put("success", true);
				resultMap.put("teachers", ts);
			} 
		}
		resultMap.put("totalCount", totalCount);
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	private boolean sendContact(String receiverId,String senderId) {
		OfflineMessageVo message = new OfflineMessageVo();
		String messageId = UUIDUtil.getUUID();
		message.setMessageId(messageId);
		message.setReceiverId(receiverId);
		message.setContent(messageId);
		message.setStatue("2");
		message.setSenderId(senderId);
		message.setType("10");
		message.setMessageType("1");
		message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
		pushMsgService.saveOfflineMessage(message);
		pushMsgService.pushMessage(message);
		System.out.println(message.toJsonString());
		return true;
	}
}
