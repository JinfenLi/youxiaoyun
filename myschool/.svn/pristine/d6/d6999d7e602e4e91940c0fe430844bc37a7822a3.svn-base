package com.topview.school.dao.school;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Departments;

public interface DepartmentsMapper extends BaseDao<Departments>{
	
	/**
	 * 根据老师id查询部门
	 * @param teacherId
	 * @return
	 */
   public List<Departments> selectDepartmentsByTeacherId(String teacherId);
   
   /**
    * 获取指定学校的所有部门
    * @param schoolId
    * @return
    */
   public List<Departments> selectAllBySchoolId(String schoolId);
   
   /**
    * 根据学科id、学校id或者部门类型查询部门
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
}