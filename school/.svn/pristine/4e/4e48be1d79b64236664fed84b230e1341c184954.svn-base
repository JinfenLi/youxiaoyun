/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午11:05:51 
 * @version V1.0
 */
package com.topview.school.controller.appraise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.message.service.PushMsgService;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.multimedia.bean.Pager;
import com.topview.school.po.Clazz;
import com.topview.school.po.Parent;
import com.topview.school.po.Student;
import com.topview.school.service.appraise.AppraiseSavaRequest;
import com.topview.school.service.appraise.AppraiseService;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.service.user.parent.ParentService;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.ClassLoaderUtil;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.DownloadAndUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.PushThreadUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.StudentVo;
import com.topview.school.vo.User.TeacherVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.appraise.AppraiseForTeacherInfo;
import com.topview.school.vo.appraise.AppraiseForstudentInfo;
import com.topview.school.vo.appraise.AppraiseInfo;
import com.topview.school.vo.appraise.result.AppraiseInfoResult;
import com.topview.school.vo.school.SemesterVo;

/**
 * @ClassName: AppraiseController
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午11:05:51
 * 
 */
@Controller
@RequestMapping(value = "/appraise", produces = "text/html;charset=UTF-8")
public class AppraiseController {

	@Autowired
	private AppraiseService appraiseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private SemesterService semesterService;
	@Autowired
	private PushMsgService pushMsgService;

	private String teacherId;
	private List<AppraiseInfo> infos;

	public List<AppraiseInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<AppraiseInfo> infos) {
		this.infos = infos;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * 老师拿到学生的评价
	 * 
	 * @param session
	 * @param pager
	 * @param classid
	 * @param Semester
	 * @param stage
	 * @param type
	 * @return
	 */
	@RequestMapping("/getAppraiseByteacherForPc")
	@ResponseBody
	public String getAppraiseByteacherForPc(HttpSession session, Pager pager,
			String classid, String Semester, String stage, String type) {
		List<AppraiseForstudentInfo> list = new ArrayList<AppraiseForstudentInfo>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppraiseInfo info = new AppraiseInfo();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		info.setPager(pager);
		info.setSemester(Semester);
		info.setTeacherId(userInfo.getUser_id());
		info.setStage(stage);
		info.setGclass(classid);
		info.setType(type);
		AppraiseInfoResult result = appraiseService.AppraiseFindByTeacher(info);
		int size = result.getInforesult().size();
		for (int i = 0; i < size; i++) {
			AppraiseForstudentInfo afinfo = new AppraiseForstudentInfo();
			info = result.getInforesult().get(i);
			afinfo.setStudentId(info.getStudentId());
			afinfo.setId(info.getId());
			afinfo.setStar(info.getStar());
			afinfo.setWord(info.getWord());
			afinfo.setTime(info.getTime());
			list.add(afinfo);
		}
		list = change(list, classid);
		resultMap.put("result", list);
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}
       
	
	/**
	 * 把数据包装
	 * @param list
	 * @param classid
	 * @return
	 */
	public List<AppraiseForstudentInfo> change(
			List<AppraiseForstudentInfo> list, String classid) {
		List<AppraiseForstudentInfo> infos = new ArrayList<AppraiseForstudentInfo>();
		List<Student> students = studentService.selectByClazzId(classid);
		Clazz clazz = clazzService.clazzFind(classid);
		for (Student student : students) {
			AppraiseForstudentInfo appInfo = new AppraiseForstudentInfo();
			appInfo.setStudentId(student.getId());
			appInfo.setStudentName(student.getName());
			appInfo.setStudentNumber(student.getIdcard());
			appInfo.setGclass(clazz.getName());
			appInfo.setPicurl(student.getPicurl());
			appInfo.setParentId(parentService.selectMainParent(student.getId()).getId());
			infos.add(appInfo);

		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String studentId = list.get(i).getStudentId();
				for (int j = 0; j < infos.size(); j++) {
					if (studentId.equals(infos.get(j).getStudentId())) {
						infos.get(j).setId(list.get(i).getId());
						infos.get(j).setStar(list.get(i).getStar());
						infos.get(j).setWord(list.get(i).getWord());
						infos.get(j).setTime(list.get(i).getTime());
					}
				}
			}
		}
		return infos;
	}
	
	
	


	/**
	 * 家长端拿到所有老师对孩子的评价
	 * 
	 * @param pager
	 * @param student_id
	 * @param Semester
	 * @param stage
	 * @return
	 */

	@RequestMapping("/getAppraiseByPartent")
	@ResponseBody
	public String getAppraiseByPartent(Pager pager, String student_id,
			String Semester, String stage) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AppraiseForTeacherInfo> list = new ArrayList<AppraiseForTeacherInfo>();
		AppraiseInfo info = new AppraiseInfo();
		info.setPager(pager);
		info.setSemester(Semester);
		info.setStudentId(student_id);
		info.setStage(stage);
		AppraiseInfoResult result = appraiseService.AppraiseFindByPartent(info);
		int size = result.getInforesult().size();
		TeacherVo teacherinfo = new TeacherVo();
		for (int i = 0; i < size; i++) {
			AppraiseForTeacherInfo atinfo = new AppraiseForTeacherInfo();
			info = result.getInforesult().get(i);
			teacherinfo.setId(info.getTeacherId());
			teacherinfo = teacherService.teacherFindById(teacherinfo.getId())
					.getResult().get(0);
			if ("1".equals(info.getType())) {
				atinfo.setPosition("班主任");
			} else {
				atinfo.setPosition("任课老师");
			}
			atinfo.setPicurl(teacherinfo.getPicUrl());
			atinfo.setStar(info.getStar());
			atinfo.setWord(info.getWord());
			atinfo.setTime(info.getTime());
			atinfo.setSubject(teacherinfo.getSubject());
			atinfo.setTeacherId(teacherinfo.getId());
			atinfo.setStudentId(info.getStudentId());
			atinfo.setId(info.getId());
			atinfo.setTeacherName(teacherinfo.getName());
			list.add(atinfo);
		}
		resultMap.put("result", list);
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改评价内容
	 * 
	 * @param appraise_id
	 * @param star
	 * @param word
	 * @return
	 */

	@RequestMapping("/updateAppraise")
	@ResponseBody
	public String updateAppraise(String appraise_id, String star, String word) {
		// String appraise_id,int star,String word
		Map<String, Object> map = new HashMap<String, Object>();
		AppraiseInfo info = new AppraiseInfo();
		AppraiseInfoResult result = new AppraiseInfoResult();
		String[] list = appraise_id.split(",");
		for (String id : list) {
			info.setId(id);
			if (star != null) {
				info.setStar(star);
			}
			if (word != null) {
				info.setWord(word);
			}
			result = appraiseService.UpdateAppraiseSelective(info);
		}
		map.put("success", result.isSuccess());
		return JSONUtil.toObject(map).toString();
	}

	/**
	 * 通过excel保存评价信息
	 * 
	 * @param saverequest
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */

	@RequestMapping("/saveAppraiseByExcel")
	@ResponseBody
	public String saveAppraiseByExcel(@Valid AppraiseSavaRequest saverequest,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session)
			throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		saverequest.setTeacherId(userInfo.getUser_id());
		String path = request.getSession().getServletContext()
				.getRealPath("upload");// 设置文件存储的位置
		String fileName = file.getOriginalFilename();
		File uploadPath = new File(path, fileName);
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		if (fileName == null || fileName.equals("")) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		file.transferTo(uploadPath);
		saverequest.setPath(uploadPath.getAbsolutePath());
		if (!appraiseService.appraiseSave(saverequest)) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 保存评价
	 * 
	 * @param saverequest
	 * @param studentIds
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveAppraise")
	@ResponseBody
	public String saveAppraise(@Valid AppraiseSavaRequest saverequest,
			String studentIds, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String teacherid = userInfo.getUser_id();
		SemesterVo semesterVo;
		String currentSemester = (semesterVo = semesterService
				.getCurrentSemester(userInfo.getSchool_id())) != null ? semesterVo
				.getId() : null;
		saverequest.setTeacherId(userInfo.getUser_id());
		AppraiseInfoResult result = new AppraiseInfoResult();
		List<AppraiseInfo> infoList = new ArrayList<AppraiseInfo>();
		AppraiseInfo info;
		String[] list = studentIds.split(",");
		for (String studentId : list) {
			info = new AppraiseInfo();
			info.setStudentId(studentId);
			info.setStar(saverequest.getStar());
			info.setWord(saverequest.getWord());
			info.setTeacherId(teacherid);
			info.setType(saverequest.getType());
			if(saverequest.getSemester()!=null){
				info.setSemester(saverequest.getSemester());
			}
			else{
				info.setSemester(currentSemester);
			}
			info.setGclass(saverequest.getClass_id());
			info.setStage(saverequest.getStage());
			info.setId(UUIDUtil.getUUID());
			result = appraiseService.SaveAppraise(info);
			infoList.add(info);

		}

		PushThreadUtil pushThreadUtil = new PushThreadUtil();
		pushThreadUtil.setPushMsgService(pushMsgService);
		pushThreadUtil.setVos(getMessageVoList(teacherid, infoList));
		Thread thread = new Thread(pushThreadUtil);
		thread.start();

		resultMap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除评价
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAppraise")
	@ResponseBody
	public String deleteAppraise(String ids) {
		AppraiseInfo info = new AppraiseInfo();
		AppraiseInfoResult result = new AppraiseInfoResult();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] list = ids.split(",");
		for (String id : list) {
			info.setId(id);
			result = appraiseService.DeleteAppraise(info);
		}
		resultMap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 推送评价到家长端
	 * 
	 * @param teacherId
	 * @param info
	 * @return
	 */

	private List<OfflineMessageVo> getMessageVoList(String teacherId,
			List<AppraiseInfo> infos) {
		OfflineMessageVo vo = new OfflineMessageVo();
		StringBuilder receiversId = new StringBuilder();
		StringBuilder studentsId = new StringBuilder();
		
		for (AppraiseInfo info : infos) {
			String studentId = info.getStudentId();
			List<Parent> parents = parentService.selectByStudentId(studentId);
			for (Parent parent : parents) {
				vo.setSenderId(teacherId);
				studentsId = studentsId.append(studentId).append(",");
				receiversId = receiversId.append(parent.getId()).append(",");
			}
		}
		vo.setContent("您有新的评价待查看"); 
		vo.setMessageType("1");
		vo.setType("6");
		vo.setStatue("1");
		vo.setSendTime(DateFormatUtil.formatDateToSeconds(new Date()));
		vo.setReceiverId(receiversId.toString());
		vo.setStudentId(studentsId.toString());
		return pushMsgService.saveMassPush(vo).getResult();
	}

	/**
	 *   导出评价
	 * @param request
	 * @param respone
	 * @param session
	 * @param classid
	 * @param Semester
	 * @param stage
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadAppraise", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadAppraise(
			HttpServletRequest request, HttpServletResponse respone,HttpSession session,
			String classid, String Semester, String stage, String type) throws Exception {
		List<AppraiseForstudentInfo> list = new ArrayList<AppraiseForstudentInfo>();
		AppraiseInfo info = new AppraiseInfo();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		info.setSemester(Semester);
		info.setTeacherId(userInfo.getUser_id());
		info.setStage(stage);
		info.setGclass(classid);
		info.setType(type);
		AppraiseInfoResult result = appraiseService.AppraiseFindByTeacher(info);
		int size = result.getInforesult().size();
		for (int i = 0; i < size; i++) {
			AppraiseForstudentInfo afinfo = new AppraiseForstudentInfo();
			info = result.getInforesult().get(i);
			afinfo.setStudentId(info.getStudentId());
			afinfo.setId(info.getId());
			afinfo.setStar(info.getStar());
			afinfo.setWord(info.getWord());
			afinfo.setTime(info.getTime());
			list.add(afinfo);
		}
		list = change(list, classid);
		List<AppraiseForstudentInfo> listForExcel = new ArrayList<AppraiseForstudentInfo>();
		for(int i=0;i<list.size();i++) {
			AppraiseForstudentInfo infoForExcel = list.get(i);
			AppraiseForstudentInfo afs = new AppraiseForstudentInfo();
			afs.setStudentName(infoForExcel.getStudentName());
			afs.setStudentNumber(infoForExcel.getStudentNumber());
			afs.setStar(infoForExcel.getStar());
			afs.setWord(infoForExcel.getWord());
			afs.setGclass(infoForExcel.getGclass());
			listForExcel.add(afs);
		}
		String fileName = "评价內容";
		String relativePath = ClassLoaderUtil.getExtendResource(
				"../schoolUpload/Appraise", "school").toString();
		String realPath = relativePath.replace("/", "\\");
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String filePath = realPath + "\\" + fileName;
		// 4.生成excel文件
		appraiseService.createAppraiseExcel(filePath, listForExcel);
		File targetFile = new File(filePath);
		return DownloadAndUploadUtil.download(request, targetFile, fileName);
	}
	
	
	@RequestMapping("/getAppraiseByteacher")
	@ResponseBody
	public String getAppraiseByteacher(HttpSession session, Pager pager,
			String classid, String Semester, String stage, String type) {
		if(session.getAttribute("currentUser") == null){
			throw new RuntimeException();
		}
		List<AppraiseForstudentInfo> list = new ArrayList<AppraiseForstudentInfo>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppraiseInfo info = new AppraiseInfo();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		info.setPager(pager);
		info.setSemester(Semester);
		info.setTeacherId(userInfo.getUser_id());
		info.setStage(stage);
		info.setGclass(classid);
		info.setType(type);
		AppraiseInfoResult result = appraiseService.AppraiseFindByTeacher(info);
		int size = result.getInforesult().size();
		StudentVo studentinfo = new StudentVo();
		for (int i = 0; i < size; i++) {
			AppraiseForstudentInfo afinfo = new AppraiseForstudentInfo();
			info = result.getInforesult().get(i);
			studentinfo.setId(info.getStudentId());
			studentinfo = studentService.studentFind(studentinfo).getResult()
					.get(0);
			Clazz clazz = clazzService.clazzFind(studentinfo.gettScClassId());
			afinfo.setStudentId(info.getStudentId());
			afinfo.setStudentName(studentinfo.getName());
			afinfo.setStudentNumber(studentinfo.getIdcard());
			afinfo.setPicurl(studentinfo.getPicurl());
			afinfo.setGclass(clazz.getName());
			afinfo.setParentId(parentService.selectMainParent(studentinfo.getId()).getId());
			afinfo.setId(info.getId());
			afinfo.setStar(info.getStar());
			afinfo.setWord(info.getWord());
			afinfo.setTime(info.getTime());
			list.add(afinfo);
		}
		resultMap.put("result", list);
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	
}
