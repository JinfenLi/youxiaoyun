package com.topview.school.controller.user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.topview.message.service.PushMsgService;
import com.topview.push.service.PushService;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.school.controller.user.bean.LoginRequest;
import com.topview.school.po.Clazz;
import com.topview.school.po.Parent;
import com.topview.school.po.School;
import com.topview.school.po.Student;
import com.topview.school.po.Subject;
import com.topview.school.po.Teacher;
import com.topview.school.po.TeacherPosition;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.school.SchoolService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.service.school.subject.SubjectService;
import com.topview.school.service.school.user.AppUserServiceImpl;
import com.topview.school.service.user.UserService;
import com.topview.school.service.user.parent.ParentService;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.service.user.teacher.TeacherPositionService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.util.StringUtil;
import com.topview.school.util.VerifyMessageUtil;
import com.topview.school.vo.User.StudentAndParentInfo;
import com.topview.school.vo.User.StudentVo;
import com.topview.school.vo.User.TeacherVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.User.enums.UserType;
import com.topview.school.vo.school.SemesterVo;

@Controller
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
@SessionAttributes("currentUser")
public class UserController {

	@Resource
	private AppUserServiceImpl appUserServiceImpl;
	@Resource
	private PushService pushService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private ParentService parentService;
	@Resource
	private StudentService studentService;
	@Resource
	private SemesterService semesterService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private TeacherPositionService teacherPositionService;
	@Resource
	private SchoolService schoolService;
	@Resource
	private UserService UserService;
	@Resource
	private PushMsgService pushMsgService;

	/**
	 * 登录并将userInfo存入session，key为"currentUser"
	 * 
	 * @param loginRequest
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login")
	public @ResponseBody Object login(@Valid LoginRequest loginRequest,
			Model model, HttpSession session, String student_id) {

		loginRequest.setModel(model);
		loginRequest.setStudent_id(student_id);
		appUserServiceImpl.login(loginRequest);
		String[] filter = { "createTime", "lastupdate", "password", "kids",
				"tag", "info", "sortName", "comment", "teaching_classes" };
		return JSONUtil.toObject(loginRequest.getResult(), filter).toString();
	}

	/**
	 * 短信验证
	 * @author dr 
	 * @param session
	 * @param Mobile
	 * @return
	 */
	@RequestMapping(value = "/verify_login")
	public @ResponseBody String verifyLogin(HttpSession session, String Mobile) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo user = new UserInfo();
		user.setPassword(StringUtil.getRandomString(6));
		user.setAccount(Mobile);
		if (this.UserService.updateUserPassword(user)) {
			VerifyMessageUtil.sendVerifyMessage(Mobile, user.getPassword());
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 用于登录后通知后台向socket发送离线消息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("getOfflineMessage")
	public @ResponseBody String getOfflineMessage(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo != null) {
			//************************查询旧的数据库未发信息，当所有数据发送完毕可将旧的模块移除***********************************
			List<com.topview.push.vo.OfflineMessageVo> oldVos = pushService
					.getOfflineMessage(userInfo.getUser_id()).getResult(); 
			List<OfflineMessageVo> vos = pushMsgService.getOfflineMessage(
					userInfo.getUser_id()).getResult(); // 查询该用户未接收信息
			if(oldVos != null && !"".equals(oldVos)) {
				for(com.topview.push.vo.OfflineMessageVo vo : oldVos) {
					pushService.pushMessage(vo);
				}
			}
			//****************************************************************************************
			if (vos != null && !"".equals(vos)) {
				for (OfflineMessageVo vo : vos) {
					if (pushMsgService.pushMessage(vo).isSuccess()) { // 推送
						if (!"我的短信".equals(vo.getType())) {
							pushService.updateMessage(vo.getEnvelopeId()); // 修改发送状态，TODO if块看上去可以不写，这里为什么要加上
						}
					}
				}
			}
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "该用户尚未登录");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 注销登录
	 * 
	 * @param logoutRequest
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public @ResponseBody String logout(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// session.removeAttribute("currentUser");
		session.invalidate();
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 获取用户基本信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public String getUserInfo(HttpSession session) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		Enum<UserType> userType = userInfo.getUser_type(); // 用户类型
		String userId = userInfo.getUser_id(); // 用户id
		SemesterVo semesterVo = semesterService.getCurrentSemester(userInfo
				.getSchool_id()); // 当前学期

		if (userType == UserType.Teacher) { // 教师类型
			String[] filter = { "createTime", "password", "superPassword",
					"tScClassId", "birthday", "education", "lastUpdate",
					"tScGradeId", "tScSchoolId" };
			resultMap.put("clazzName", new ArrayList<String>()); // 应安卓端要求，没有班级和职位则返回空数组
			resultMap.put("subectName", "无");
			resultMap.put("positions", new ArrayList<String>());

			Teacher teacher = teacherService.selectTeacherById(userId); // 查询教师基本个人信息
			resultMap.put("teacherInfo", teacher);

			List<Clazz> clazzs = clazzService.selectTeacherClazzs(userId,
					semesterVo.getId()); // 查询当前学期所带的所有班级
											// //TODO可能会出现班会等特殊课程导致班级重复
			if (clazzs.size() > 0) {
				List<String> list = new ArrayList<String>();
				for (Clazz c : clazzs) {
					list.add(c.getName());
				}
				resultMap.put("clazzName", list);
			}

			Subject subject = subjectService.getTeacherSubject(userId); // 所教学科
																		// //TODO
																		// (是否存在一个老师对应多个学科)
			if (subject != null) {
				resultMap.put("subjectName", subject.getName());
			}

			List<TeacherPosition> positions = teacherPositionService
					.getPositions(userId); // 查询职务
			if (positions.size() > 0) {
				List<String> list = new ArrayList<String>();
				for (TeacherPosition tp : positions) {
					list.add(tp.getName());
				}
				resultMap.put("positions", list);
			}
			return JSONUtil.toObject(resultMap, filter).toString();
		}

		else { // 家长类型
			String[] filter = { "createTime", "parentInfo.idcard",
					"parenttype", "password", "superPassword", "type",
					"tScClassId" };
			Parent p = parentService.selectByPrimaryKey(userId); // 查询家长个人信息
			resultMap.put("parentInfo", p);

			String studentId = userInfo.getParent_info().getStudent_id();
			Student s = studentService.selectByPrimaryKey(studentId); // 查询学生个人信息
			resultMap.put("studentInfo", StudentVo.changeToVo(s));

			Clazz c = clazzService.clazzFind(s.gettScClassId()); // 查询班级信息
			resultMap.put("clazzName", c.getName());

			return JSONUtil.toObject(resultMap, filter).toString();
		}
	}

	/**
	 * 修改当前登陆用户个人基本信息
	 * 
	 * @param studentAndParentInfo
	 * @param teacherInfo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public String updateUserInfo(HttpSession session,
			StudentAndParentInfo studentAndParentInfo, TeacherVo teacherInfo)
			throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo.getUser_type().ordinal() == 1) { // 1为教师类型
			Teacher teacher = TeacherVo.changeToPo(teacherInfo);
			resultMap.put("success", teacherService.updateTeacherInfo(teacher));
		} else {// TODO此处逻辑有错待改正
			if (studentAndParentInfo.getParentId() != null
					&& !"".equals(studentAndParentInfo.getParentId())) {
				Parent p = new Parent();
				p.setId(studentAndParentInfo.getParentId());
				p.setName(studentAndParentInfo.getParentName());
				p.setSex(studentAndParentInfo.getParentGender());
				p.setMobile(studentAndParentInfo.getParentPhone());
				p.setPassword(studentAndParentInfo.getParentPassword());
				resultMap.put("success", parentService.updateParent(p));
			}
			if (studentAndParentInfo.getStudentId() != null
					&& !"".equals(studentAndParentInfo.getStudentId())) {
				Student s = new Student();
				s.setId(studentAndParentInfo.getStudentId());
				s.setAddress(studentAndParentInfo.getStudentAddress());
				if (studentAndParentInfo.getFeteday() != null
						&& !"".equals(studentAndParentInfo.getFeteday())) {
					s.setBirthday(DateFormatUtil
							.parseToDay(studentAndParentInfo.getFeteday()));
				}
				s.setEmergencyPhone(studentAndParentInfo.getEmergencyPhone());
				s.setIdcard(studentAndParentInfo.getStudentIDCard());
				s.setName(studentAndParentInfo.getStudentName());
				s.setPhone(studentAndParentInfo.getStudentPhone());
				s.setSex(studentAndParentInfo.getStudentGender());
				resultMap.put("success", studentService.updateStudent(s));
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据用户id查询用户名称、头像、用户类型、手机号码、所在学校
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("getUserInfoById")
	@ResponseBody
	public String getUserInfoById(String id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		Teacher t = teacherService.selectTeacherById(id);
		if (t != null) {
			// TODO
			School school = schoolService
					.selectByPrimaryKey(t.gettScSchoolId());
			SemesterVo semesterVo = semesterService.getCurrentSemester(t
					.gettScSchoolId());
			List<Clazz> clazzs = clazzService.selectTeacherClazzs(t.getId(),
					semesterVo.getId());
			String clazzName = "";

			for (Clazz c : clazzs) {
				clazzName += c.getName() + ",";
			}
			if (clazzName.length() > 1) {
				resultMap.put("clazzName",
						clazzName.substring(0, clazzName.length() - 1));
			} else {
				resultMap.put("clazzName", "");
			}
			resultMap.put("name", t.getName());
			resultMap.put("phone", t.getPhone());
			resultMap.put("userType", "teacher");
			resultMap.put("userPic", t.getPicUrl());
			resultMap.put("schoolName", school.getName());
		} else {
			// TODO
			Parent p = parentService.selectByPrimaryKey(id);
			if (p != null) {
				String schoolName = "";
				String clazzName = "";
				List<School> schools = schoolService.selectByParentPhone(p
						.getMobile());
				for (School s : schools) {
					schoolName += s.getName() + ",";
				}
				List<Student> students = studentService.findByParentId(p
						.getId());
				for (Student s : students) {
					clazzName += clazzService.clazzFind(s.gettScClassId())
							.getName() + ",";
					resultMap.put("userPic", s.getPicurl());
				}
				resultMap.put("name", p.getName());
				resultMap.put("phone", p.getMobile());
				resultMap.put("userType", "parent");
				resultMap.put("clazzName",
						clazzName.substring(0, clazzName.length() - 1));
				resultMap.put("schoolName",
						schoolName.substring(0, schoolName.length() - 1));
			} else {
				resultMap.put("success", false);
				resultMap.put("msg", "查无当前用户");
			}
		}

		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 管理员重置用户密码
	 * 
	 * @param adminPassword
	 * @param newPassword
	 * @param repeatPassword
	 * @return
	 */
	@RequestMapping("resetPassword")
	@ResponseBody
	public String resetPassword(HttpSession session, String userId,
			String adminPassword, String newPassword, String repeatPassword) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (!userInfo.getPassword().equals(adminPassword)) { // TODO
																// 无法断定当前用户即为管理员
			resultMap.put("msg", "您输入的管理密码有误,请重新输入!");
			return JSONUtil.toObject(resultMap).toString();
		}
		if (!NotEmptyString.isNotEmpty(new String[] { newPassword,
				repeatPassword })) {
			resultMap.put("msg", "新密码不能为空,请重新输入！");
			return JSONUtil.toObject(resultMap).toString();
		}
		if (!newPassword.equals(repeatPassword)) {
			resultMap.put("msg", "两次输入的新密码不一致，请重新输入!");
			return JSONUtil.toObject(resultMap).toString();
		}
		Teacher t = teacherService.selectTeacherById(userId);
		if (t != null) {
			t.setPassword(newPassword);
			if (teacherService.updatePassword(t)) {
				resultMap.put("success", true);
			} else {
				resultMap.put("msg", "您输入的密码非法，请重新输入!");
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
}
