package com.topview.school.service.school.department;

import java.util.List;
import java.util.Map;

import com.topview.school.po.Departments;

public interface DepartmentService {

	/**
	 * 新建部门
	 * 
	 * @param departments
	 * @return
	 */
	public boolean createDepartment(Departments departments);

	/**
	 * 删除部门
	 * 
	 * @param departmentsId
	 * @return
	 */
	public boolean deleteDepartment(String departmentsId);

	/**
	 * 修改部门信息
	 * 
	 * @param departments
	 * @return
	 */
	public boolean updateDepartment(Departments departments);

	/**
	 * 根据学科id（key为subjectId）或学校id(key为schoolId)或部门类型(key为type)查询部门
	 * 分页参数：key为offset和limit
	 * 
	 * @param map
	 * @return
	 */
	public List<Departments> selectDepartments(Map<String, Object> map);
	
	/**
	 * 根据学校id查询部门个数
	 * @param schoolId
	 * @return
	 */
	public int selectCount(String schoolId);
	
	/**
	 * option = 1时分配老师到具体的部门,option = 2时解除关联
	 * @param departmentsId
	 * @param teacherId
	 * @param option
	 * @return
	 */
	public boolean assignTeacherToDepartment(String departmentsId, String teacherId, int option);
	
	/**
	 * 根据学校id获取所有部门
	 * @param schoolId
	 * @return
	 */
	public List<Departments> selectAllBySchoolId(String schoolId);
	
}
