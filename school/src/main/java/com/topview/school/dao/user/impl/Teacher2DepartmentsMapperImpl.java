package com.topview.school.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.Teacher2DepartmentsMapper;
import com.topview.school.po.Teacher2DempartmentsKey;

@Repository
public class Teacher2DepartmentsMapperImpl extends BaseDaoImpl<Teacher2DempartmentsKey> implements
		Teacher2DepartmentsMapper {

	@Override
	public int delete(Teacher2DempartmentsKey t) {
		return template.delete(getStatement("delete"), t);
	}

}
