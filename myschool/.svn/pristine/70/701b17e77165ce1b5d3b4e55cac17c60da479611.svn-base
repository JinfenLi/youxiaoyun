package com.topview.school.controller.school.subject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.Departments;
import com.topview.school.po.Subject;
import com.topview.school.po.Teacher;
import com.topview.school.po.UserRoleKey;
import com.topview.school.service.school.department.DepartmentService;
import com.topview.school.service.school.subject.SubjectService;
import com.topview.school.service.system.authc.RoleType;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.school.SubjectVo;

/**
 * @Description 学科controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:57:46
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "subject", produces = "text/html;charset=UTF-8")
public class SubjectController {

	@Resource
	private SubjectService subjectService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private TeacherService teacherService;
	@Autowired
	private UserRoleService roleService;
	private static final String roleId = RoleType.SUBJECT_MANAGER.value();

	/**
	 * 创建学科，同时建立一个对应的部门
	 * 
	 * @param vo
	 * @param sesssion
	 * @return
	 */
	@RequestMapping("/createSubject")
	@ResponseBody
	public String createSubject(Subject subject, HttpSession sesssion) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		subject.setId(UUIDUtil.getUUID());
		subject.setCreateTime(new Date());
		subject = subjectService.createSubject(subject);

		if (subject != null) {
			// 创建部门
			Departments departments = new Departments();
			departments.setId(UUIDUtil.getUUID());
			departments.settScSubjectId(subject.getId());
			departments.settScSchoolId(subject.gettScSchoolId());
			departments.setName(subject.getName() + "组");
			departments.setType("教学");
			departments.setTeacherId(subject.gettScTeacherId());
			departmentService.createDepartment(departments);

			if (subject.gettScTeacherId() != null
					&& !"".equals(subject.gettScTeacherId())) {
				addRole(subject.gettScTeacherId());// 添加学科负责人
			}
			resultMap.put("subject", SubjectVo.changeToVo(subject));
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除学科
	 * 
	 * @param subjectId
	 * @param session
	 * @return
	 */
	@RequestMapping("/delectSubject")
	@ResponseBody
	public String delectSubject(String subjectId, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// TODO事务控制这烂代码都没眼看了
		// 删除跟学科关联的部门
		Map<String, Object> map = new HashMap<String, Object>();
		Departments d = new Departments();
		map.put("subjectId", subjectId);
		List<Departments> ds = departmentService.selectDepartments(map);
		if (ds.size() > 0) {
			d = ds.get(0);
			departmentService.deleteDepartment(d.getId());
		}

		Subject s = subjectService.selectByPrimaryKey(subjectId);
		if (s != null) {
			if (s.gettScTeacherId() != null) {
				delRole(s.gettScTeacherId());// 删除学科负责人角色
			}
			try {
				resultMap.put("success", subjectService.delectSubject(subjectId));// 课程的外键关联，报错
			} catch (Exception e) {
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据学校id查询所有学科
	 * 
	 * @param schoolId
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAllSubject")
	@ResponseBody
	public String getAllSubject(String schoolId, HttpSession session) {
		Map<String, Object> resulMap = new HashMap<String, Object>();
		if (schoolId == null || "".equals(schoolId)) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			schoolId = userInfo.getSchool_id();
		}
		List<Subject> subjects = subjectService.selectBySchoolId(schoolId);
		if (subjects != null) {
			List<SubjectVo> subjectVos = SubjectVo.changeToVo(subjects);
			for (int i = 0; i < subjectVos.size(); i++) {
				Teacher teacher = teacherService.selectTeacherById(subjectVos
						.get(i).gettScTeacherId());
				if (teacher != null) {
					subjectVos.get(i).setTeacherName(teacher.getName()); // 添加学科负责人
				}
			}
			resulMap.put("success", true);
			resulMap.put("subjects", subjectVos);
		} else {
			resulMap.put("success", false);
			resulMap.put("msg", "尚无任何科目信息");
		}
		return JSONUtil.toObject(resulMap).toString();
	}

	/**
	 * 修改学科信息
	 * 
	 * @param subjectVo
	 * @return
	 */
	@RequestMapping("updateSubject")
	@ResponseBody
	public String updateSubject(SubjectVo subjectVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String t = subjectVo.getId();
		String tId = subjectVo.gettScTeacherId();// 新学科负责人角色
		if (subjectVo != null && t != null) {
			// 更新部门表的负责人字段
			Map<String, Object> map = new HashMap<String, Object>();
			Departments d = new Departments();
			map.put("subjectId", t);
			List<Departments> ds = departmentService.selectDepartments(map); // 根据学科id查询所属部门
			if (ds.size() > 0) {
				d = ds.get(0);
			}
			d.setTeacherId(tId);
			departmentService.updateDepartment(d);

			Subject subject = subjectService.selectByPrimaryKey(t);
			String otId = null;
			if (subject != null && (otId = subject.gettScTeacherId()) != null
					&& !tId.equals(otId)) {

				delRole(otId);// 删除旧的负责人
			}
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success", subjectService.updateSubject(subjectVo));

		if (tId != null && !"".equals(tId)) {
			addRole(tId);// 添加新的负责人
		}
		return JSONUtil.toObject(resultMap).toString();
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
			roleService.insert(key);// 添加学科负责人
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
