package com.topview.school.service.system.authc.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.service.system.authc.ScoreStatusService;
import com.topview.school.dao.school.ScoreStatusMapper;
import com.topview.school.po.ScoreStatus;
import com.topview.school.service.system.base.BaseServiceImpl;


@Service
public class ScoreStatusServiceImpl extends BaseServiceImpl<ScoreStatus> implements ScoreStatusService{

	@Resource
	private ScoreStatusMapper ScoreStatusMapper;
	
	
	@Override
	public int updateBySchoolIdSelective(ScoreStatus scoreStatus) {
		return this.ScoreStatusMapper.updateBySchoolIdSelective(scoreStatus);
	}


	@Override
	public List<ScoreStatus> getStatusBySchoolId(String schoolId) {
		return this.ScoreStatusMapper.getStatusBySchoolId(schoolId);
	}

}
