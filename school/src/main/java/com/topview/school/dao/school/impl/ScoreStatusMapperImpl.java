package com.topview.school.dao.school.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.ScoreStatusMapper;
import com.topview.school.po.ScoreStatus;

@Repository
public class ScoreStatusMapperImpl  extends BaseDaoImpl<ScoreStatus> implements ScoreStatusMapper{

	@Override
	public int updateBySchoolIdSelective(ScoreStatus record) {
		return template.update(getStatement("updateBySchoolIdSelective"),record);
	}

	@Override
	public List<ScoreStatus> getStatusBySchoolId(String schoolId) {
		return template.selectList(getStatement("getStatusBySchoolId"),schoolId);
	}

}
