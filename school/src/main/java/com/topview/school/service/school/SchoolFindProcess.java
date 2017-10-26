package com.topview.school.service.school;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.school.SchoolMapper;
import com.topview.school.po.School;
import com.topview.school.vo.school.SchoolInfo;

@Service
public class SchoolFindProcess implements SchoolProcess{

	private static final Logger logger = Logger
			.getLogger(SchoolFindProcess.class);
	@Autowired
	private SchoolMapper schoolMapper;
	@Override
	public boolean doProcess(SchoolProcessContext context) {
		try{
			School school = schoolMapper.selectByPrimaryKey(context.getInfo().getId());
			List<SchoolInfo> result = new ArrayList<SchoolInfo>();
			result.add(SchoolInfo.changeToVo(school));
			context.getResult().setResult(result);
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e){
			logger.error("find school error");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
