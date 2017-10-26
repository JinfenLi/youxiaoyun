package com.topview.school.dao.school.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.DepartmentsMapper;
import com.topview.school.po.Departments;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：DepartmentsMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:42:46  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:42:46  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class DepartmentsMapperImpl extends BaseDaoImpl<Departments> implements DepartmentsMapper{

	@Override
	public List<Departments> selectDepartmentsByTeacherId(String teacherId) {
		return template.selectList(getStatement("selectDepartmentsByTeacherId"), teacherId);
	}

	@Override
	public List<Departments> selectAllBySchoolId(String schoolId) {
		return template.selectList(getStatement("selectAllBySchoolId"), schoolId);
	}

	@Override
	public List<Departments> selectDepartments(Map<String, Object> map) {
		return template.selectList(getStatement("selectDepartments"), map);
	}

	@Override
	public int selectCount(String schoolId) {
		return template.selectOne(getStatement("selectCount"), schoolId);
	}

}
