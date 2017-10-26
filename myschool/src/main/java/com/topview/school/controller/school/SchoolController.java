package com.topview.school.controller.school;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.Departments;
import com.topview.school.po.Grade;
import com.topview.school.po.Parent;
import com.topview.school.po.School;
import com.topview.school.po.Subject;
import com.topview.school.po.Teacher;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.school.SchoolService;
import com.topview.school.service.school.department.DepartmentService;
import com.topview.school.service.school.grade.GradeService;
import com.topview.school.service.school.subject.SubjectService;
import com.topview.school.service.user.parent.ParentService;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.User.enums.UserType;
import com.topview.school.vo.school.SchoolInfo;
import com.topview.school.vo.school.result.SchoolInfoResult;

@Controller
@RequestMapping(value = "/sch", produces = "text/html;charset=UTF-8")
public class SchoolController {

	@Resource
	private SchoolService schoolService;
	@Resource
	private ParentService parentService;
	@Resource
	private StudentService studentService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private GradeService gradeService;
	@Resource
	private DepartmentService departmentService;

	/**
	 * 初始化一个学校并为该学校建立一个相应的多媒体空间、一个用于上传安卓端轮播图片的相册、五个文章模块和20个学期  可选：同时创建指定默认学科、学科组、默认年级、默认年级组和注册多媒体空间
	 * 
	 * @param name
	 *            学校名称
	 * @param address
	 *            学校地址
	 * @param phone
	 *            学校电话
	 * @param postcode
	 *            学校邮编
	 * @param email
	 *            学校邮箱地址
	 * @param website
	 *            学校官网
	 * @return
	 */
	// TODO 好凌乱...只要school对象保存成功就返回true,不管后面年级学科是否成功; 创建年级与学科失败不能回滚
	@RequestMapping(value = "/schoolSave", method = RequestMethod.POST)
	@ResponseBody
	public String schoolSave(SchoolInfo info,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, String[] subjectsName, String[] gradesName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (file != null) {
			if (!file.isEmpty()) { // 如果有上传logo
				FileUploadVo vo = FileUploadUtil.uploadFile(file, "logo",
						request, true);
				info.setLogo(vo.getRelativePath());
			}
		}
		info.setId(UUIDUtil.getUUID());
		info.setType(0);
		SchoolInfoResult result = new SchoolInfoResult();
		try {
			result = schoolService.schoolSave(info);
		} catch (Exception e) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		if (result.isSuccess()) {
			if (subjectsName != null && subjectsName.length > 0) { // 如果有指定同时创建学科则进入
				createSubjectOptional(subjectsName, info.getId());
			}
			if(gradesName != null && gradesName.length > 0) { //如果指定同时创建年级则进入
				createGradeOptional(gradesName, info.getId());
			}
			resultMap.put("result", result.getResult().get(0));
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		String[] fileter = { "code" };
		return JSONUtil.toObject(resultMap, fileter).toString();
	}

	/**
	 * 如果指定了创建学科才调用
	 * @param subjectsName
	 * @param schoolId
	 */
	private void createSubjectOptional(String[] subjectsName, String schoolId) {
		for (int i = 0; i < subjectsName.length; i++) {
			Subject sub = new Subject();
			sub.setId(UUIDUtil.getUUID());
			sub.setName(subjectsName[i]);
			sub.setCreateTime(new Date());
			sub.settScSchoolId(schoolId);
			sub.setTrunk(true);
			sub = subjectService.createSubject(sub);
			if (sub != null) {
				// 创建部门
				Departments departments = new Departments();
				departments.setId(UUIDUtil.getUUID());
				departments.settScSubjectId(sub.getId());
				departments.settScSchoolId(schoolId);
				departments.setName(sub.getName() + "组");
				departments.setType("教学");
				departmentService.createDepartment(departments);
			}
		}
	}
	
	/**
	 * 如果指定了创建年级才调用
	 * @param gradesName
	 * @param schoolId
	 */
	private void createGradeOptional(String[] gradesName, String schoolId) {
		for(int i = 0; i < gradesName.length; i++) {
			Grade g = new Grade();
			g.setId(UUIDUtil.getUUID());
			g.setName(gradesName[i]);
			g.setGraduate(false);
			g.setYear(new Date()); //TODO 待解决
			g.settScSchoolId(schoolId);
			if(gradeService.createGrade(g)) {
				Departments d = new Departments();
				d.setId(g.getId()); 
				d.setName(g.getName() + "年级组");
				d.setTemplate(false);
				d.settScSchoolId(schoolId);
				d.setType("年级组");
				departmentService.createDepartment(d);
			}
		}
		
	}
	
	/**
	 * 分页查看所有学校
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllSchool")
	@ResponseBody
	public String getAllSchool(Pager pager) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<School> schools = schoolService.getAllSchool(pager);
		resultMap.put("schools", schools);
		resultMap.put("success", schools == null ? false : true);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除学校
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSchool")
	@ResponseBody
	public String delectSchool(HttpSession session, String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		try {
			if (schoolService.delectSchool(id)) {
				resultMap.put("success", true);
			}
		} catch (Exception e) {
			resultMap.put("msg", "该学校尚有其他信息关联无法删除");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	@RequestMapping("/updateSchool")
	@ResponseBody
	public String updateSchool(SchoolInfo schoolInfo,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (!file.isEmpty()) {
			FileUploadVo vo = FileUploadUtil.uploadFile(file, "logo", request,
					true);
			schoolInfo.setLogo(vo.getRelativePath());
		}
		// resultMap.put("schoolInfo", schoolInfo);
		resultMap.put("success", schoolService.updateSchool(schoolInfo));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 判断用户密码是否匹配，用于用户进行重要操作前的确认防止别人误操作
	 * 
	 * @param session
	 * @param password
	 * @return
	 */
	@RequestMapping("/judgePassword")
	@ResponseBody
	public String judgePassword(HttpSession session, String password) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo != null) {
			if (userInfo.getPassword().equals(password)) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
			}
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success", false);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改用户密码
	 * 
	 * @param session
	 * @param password
	 * @param newPassword
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public String updataPassword(HttpSession session, String password,
			String newPassword) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (!userInfo.getPassword().equals(password)) {
			resultMap.put("success", false);
			resultMap.put("msg", "旧密码不匹配");
			return JSONUtil.toArray(resultMap).toString();
		} else if ("".equals(newPassword) || newPassword == null) {
			resultMap.put("success", false);
			resultMap.put("msg", "新密码不能为空");
			return JSONUtil.toArray(resultMap).toString();
		} else {
			boolean flag = false;
			if (userInfo.getUser_type() == UserType.Parent) {
				Parent p = new Parent();
				p.setId(userInfo.getUser_id());
				p.setPassword(newPassword);
				flag = parentService.updatePassword(p);
			} else {
				Teacher t = new Teacher();
				t.setId(userInfo.getUser_id());
				t.setPassword(newPassword);
				flag = teacherService.updatePassword(t);
			}
			if (flag == true) {
				userInfo.setPassword(newPassword);
				session.setAttribute("currentUser", userInfo); // 更新session
				resultMap.put("success", true);
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 用于初始化app时通过学校名称或手机号获取学校id和学校名称
	 * 
	 * @param schoolName
	 * @param phone
	 * @return
	 */
	@RequestMapping("/findSchoolId")
	@ResponseBody
	public String findSchoolId(String name, String phone) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		String[] filter = { "address", "email", "postcode", "website", "phone",
				"type" };

		if (name != null && !"".equals(name)) { // 按学校名称查
			List<School> schools = schoolService.selectSchoolByNameLike(name);
			resultMap.put("schools", schools);
		} else if (phone != null && !"".equals(phone)) { // 按用户手机号查
			Teacher t = teacherService.findByPhone(phone);
			if (t != null) {
				School school = schoolService.selectByPrimaryKey(t
						.gettScSchoolId());
				List<School> schools = new ArrayList<School>();
				schools.add(school); // 统一返回格式方便前端解析
				resultMap.put("schools", schools);
			} else {
				resultMap.put("schools",
						schoolService.selectByParentPhone(phone));
			}
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap, filter).toString();
	}
}
