package com.topview.school.controller.multimedia.horn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.message.service.PushMsgService;
import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.horn.HornProcessContext;
import com.topview.multimedia.service.horn.HornService;
import com.topview.multimedia.util.NotEmptyString;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.HornInfo;
import com.topview.multimedia.vo.result.HornInfoResult;
import com.topview.school.po.Clazz;
import com.topview.school.po.Parent;
import com.topview.school.po.Student;
import com.topview.school.po.Teacher;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.service.user.parent.ParentService;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.JSONUtil;

@Controller
@RequestMapping(value = "/horn", produces = "text/html;charset=UTF-8")
public class HornController {

	@Autowired
	private HornService hornService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private ClazzService clazzService;
	@Resource
	private PushMsgService pushMsgService;
	@Resource
	private SemesterService semesterService;
	
	private static final Logger logger = Logger.getLogger(HornController.class);
	
	@RequestMapping("/getHorn")
	@ResponseBody
	public String getHorn(String userId) {
		Map<String,Object> resultMap = new HashMap<>();
		if(NotEmptyString.isNotNullString(userId)) {
			HornInfo hornInfo = new HornInfo();
			List<String> userIds = new ArrayList<String>();
			userIds.add(userId);
			hornInfo.setUserIds(userIds);
			HornInfoResult hornInfoResult = hornService.hornGetProcess(hornInfo);
			if(hornInfoResult != null) {
				List<HornInfo> list = hornInfoResult.getResult();
				if(list != null) {
					for(HornInfo h: list) {
						try{
							String senderId = h.getSendId();
							Teacher teacher = teacherService.selectTeacherById(senderId);
							h.setSenderName(teacher.getName());
						}catch(Exception e) {
							logger.error("小喇叭找不到发送者啦" + h.getSendId());
						}
					}
				}
			}
			resultMap.put("HornInfoResult", hornInfoResult);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	@RequestMapping("/pushHorn")
	@ResponseBody
	public String pushHorn(String message, String beginTime, String endTime, String userId, String semesterId) {
		Map<String,Object> resultMap = new HashMap<>();
		HornInfo hornInfo = new HornInfo();
		List<Student> listStudent = new ArrayList<Student>();
		resultMap.put("success", false);
		if(NotEmptyString.isNotEmpty(new String[]{message,userId,beginTime,endTime})) {
			Teacher teacher = teacherService.selectTeacherById(userId);
			if(teacher.gettScClassId() != null) {
				listStudent = studentService.selectByClazzId(teacher.gettScClassId());
				pushHorn(userId, message, beginTime, endTime, hornInfo, listStudent);
				resultMap.put("success", true);
			}else if(teacher.gettScGradeId() != null) {
				listStudent = studentService.getAllStudentByGradeId(teacher.gettScGradeId());
				pushHorn(userId, message, beginTime, endTime, hornInfo, listStudent);
				resultMap.put("success", true);
			}else if(userRoleService.hasRole(userId, "7cc0199c8ce84fb0bc181007cee6f8a5")) {
				listStudent = studentService.getAllStudentBySchool(teacher.gettScSchoolId());
				pushHorn(userId, message, beginTime, endTime, hornInfo, listStudent);
			}else {
				List<Clazz> listClazz = clazzService.selectTeacherClazzs(userId, semesterId);
				for(Clazz clazz: listClazz) {
					List<Student> aClazzStudentList = studentService.selectByClazzId(clazz.getId());
					listStudent.addAll(aClazzStudentList);
				}
				pushHorn(userId, message, beginTime, endTime, hornInfo, listStudent);
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	private void pushHorn(String userId, String message, String beginTime, String endTime, HornInfo hornInfo,
			List<Student> listStudent) {
		List<String> listUserIds = new ArrayList<String>();
		for(Student student: listStudent) {
			List<Parent> listParent = parentService.selectByStudentId(student.getId());
			for(Parent parent: listParent) {
				listUserIds.add(parent.getId());
			}
		}
		hornInfo.setSendId(userId);
		hornInfo.setId(UUIDUtil.getUUID());
		hornInfo.setUserIds(listUserIds);
		try{
			hornInfo.setBeginTime(beginTime);
			hornInfo.setEndTime(endTime);
		}catch(Exception e) {
			return ;
		}
		hornInfo.setMessage(message);
		hornInfo.setFlag(0);
		hornService.hornPushProcess(hornInfo);
	}
	
	/**
	 * @dateTime 2016年11月28日下午7:18:08
	 * @author zjd
	 * @description 根据班级id推送小喇叭
	 */
	@RequestMapping("/pushHornByClassIds")
	@ResponseBody
	public String pushHornByClassIds(String message, String beginTime, String endTime, String userId, String classIds) {
		Map<String,Object> resultMap = new HashMap<>();
		HornInfo hornInfo = new HornInfo();
		List<Student> listStudent = new ArrayList<Student>();
		resultMap.put("success", false);
		if(NotEmptyString.isNotEmpty(new String[]{message,userId,beginTime,endTime,classIds})) {
			try{
				Date beginDay = DateFormatUtil.parseToDay(beginTime);
				Date endDay = DateFormatUtil.parseToDay(endTime);
				if(beginDay.compareTo(endDay) <= 0) {
					String[] clazzIds = classIds.split(",");
					if(clazzIds.length != 0) {
						for(String clazzId : clazzIds) {
							listStudent.addAll(studentService.selectByClazzId(clazzId));
						}
						pushHorn(userId, message, beginTime, endTime, hornInfo, listStudent);
						resultMap.put("success", true);
					}
				}
			}catch(Exception e) {
				logger.error("日期输入错误");
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * @dateTime 2016年11月28日下午7:17:57
	 * @author zjd
	 * @description 获取小喇叭历史记录
	 */
	@ResponseBody
	@RequestMapping("/getHornHistory")
	public String getHornHistory(String userId, Pager pager) {
		HornInfo hornInfo = new HornInfo();
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("success", false);
		HornProcessContext context = new HornProcessContext();
		if(NotEmptyString.isNotNullString(userId)) {
			hornInfo.setSendId(userId);
			context.setHornInfo(hornInfo);
			context.setPage(pager.getPageNumber());
			context.setLimit(pager.getPageSize());
			if(hornService.hornGetHistoryProcess(context)) {
				List<HornInfo> list = context.getResult().getResult();
				for(HornInfo h: list) {
					h.setSenderName(teacherService.selectTeacherById(userId).getName());
				}
			}
			resultMap.put("hornInfoResult", context.getResult());
			resultMap.put("total", context.getTotal());
			resultMap.put("success", true);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * @dateTime 2016年11月28日下午7:30:48
	 * @author zjd
	 * @description 小喇叭删除
	 */
	@ResponseBody
	@RequestMapping("/deleteHornById")
	public String deleteHornById(String id) {
		Map<String,Object> resultMap = new HashMap<>();
		HornInfo hornInfo = new HornInfo();
		resultMap.put("success", false);
		if(NotEmptyString.isNotNullString(id)) {
			hornInfo.setId(id);
			resultMap.put("success", hornService.deleteHornById(id));
		}
		return JSONUtil.toObject(resultMap).toString();
	}
}
