package com.topview.school.dao.exam.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.exam.ExamTempletMapper;
import com.topview.school.po.ExamTemplet;
@Repository
public class ExamTempletMapperImpl extends BaseDaoImpl<ExamTemplet>
implements ExamTempletMapper{

	@Override
	public List<ExamTemplet> selectAll() {
		return template.selectList(getStatement("selectAll"));
	}

	
	
}
