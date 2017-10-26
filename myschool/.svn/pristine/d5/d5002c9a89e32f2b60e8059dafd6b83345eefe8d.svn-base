package com.topview.school.dao.curricula.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.curricula.CurriculaAdditionMapper;
import com.topview.school.po.CurriculaAddition;

@Repository
public class CurriculaAdditionMapperImpl extends BaseDaoImpl<CurriculaAddition>
		implements CurriculaAdditionMapper {

	@Override
	public CurriculaAddition selectBySemesterId(String semesterId) {
		return template.selectOne(getStatement("selectBySemesterId"),
				semesterId);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		return template.delete(getStatement("deleteByPrimaryKey"), id);
	}

}
