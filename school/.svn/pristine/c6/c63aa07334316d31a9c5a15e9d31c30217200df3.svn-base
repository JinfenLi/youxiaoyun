package com.topview.school.controller.school.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.util.UUIDUtil;
import com.topview.school.po.Departments;
import com.topview.school.po.Teacher;
import com.topview.school.po.ValidationResult;
import com.topview.school.service.school.department.DepartmentService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.ValidationUtil;
import com.topview.school.vo.User.TeacherVo;
import com.topview.school.vo.school.DepartmentsVo;

/**
 * @Description 部门controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:55:22
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "department", produces = "text/html;charset=UTF-8")
public class DepartmentController {

	@Resource
	private DepartmentService departmentService;
	@Resource
	private TeacherService teacherService;

	/**
	 * 新建部门
	 * 
	 * @param departments
	 * @return
	 */
	@RequestMapping("/createDepartment")
	@ResponseBody
	public String createDepartment(Departments departments) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(departments);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		departments.setId(UUIDUtil.getUUID());
		resultMap.put("success",
				departmentService.createDepartment(departments));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDepartment")
	@ResponseBody
	public String deleteDepartments(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (departmentService.deleteDepartment(id)) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "该部门下尚有其他关联数据，无法删除");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改部门信息
	 * 
	 * @param departments
	 * @return
	 */
	@RequestMapping("/updateDepartment")
	@ResponseBody
	public String updateDepartment(Departments departments) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(departments);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap.put("success",
				departmentService.updateDepartment(departments));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据学科id或学校id分页或部门类型查询部门
	 * 
	 * @param subjectId
	 * @param schoolId
	 * @param type
	 * @param pager
	 * @return
	 */
	@RequestMapping("selectDepartments")
	@ResponseBody
	public String selectDepartments(String subjectId, String schoolId,
			String type, Integer start, Integer limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (start != null && limit != null && !"".equals(start)
				&& !"".equals(limit)) {
			map.put("offset", start);
			map.put("limit", limit);
		}
		if (subjectId != null && !"".equals(subjectId)) {
			map.put("subjectId", subjectId);
		} else if (schoolId != null && !"".equals(schoolId)) {
			map.put("schoolId", schoolId);
			resultMap
					.put("totalCount", departmentService.selectCount(schoolId));
		} else if (type != null && !"".equals(type)) {
			map.put("schoolId", schoolId);
			map.put("type", type);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "查询条件不足");
			return JSONUtil.toObject(resultMap).toString();
		}
		List<Departments> departments = departmentService
				.selectDepartments(map);
		List<DepartmentsVo> vos = DepartmentsVo.changeToVo(departments);
		for (int i = 0; i < vos.size(); i++) {
			// 查询部门下的所有老师
			List<Teacher> teachers = teacherService
					.selectTeacherByDepartmentId(vos.get(i).getId());
			vos.get(i).setTeachers(TeacherVo.changeToVo(teachers));

			// 查询部门负责人
			Teacher t = teacherService.selectTeacherById(vos.get(i)
					.getTeacherId());
			if (t != null) {
				vos.get(i).setLeadingTeacher(t.getName());
			}
		}
		resultMap.put("departments", vos);
		resultMap.put("success", true);
		String[] filter = { "password", "template", "templateName", "birthday",
				"education", "email", "picUrl", "createTime" };
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 分配老师到具体的部门或调离部门
	 * 
	 * @param teacherId
	 * @param departmentId
	 * @param option
	 *            1为添加进部门，2为调离开部门
	 * @return
	 */
	@RequestMapping("/assignTeacherToDepartment")
	@ResponseBody
	public String assignTeacherToDepartment(String teacherId,
			String departmentId, int option) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", departmentService.assignTeacherToDepartment(
				departmentId, teacherId, option));
		return JSONUtil.toObject(resultMap).toString();
	}
}
