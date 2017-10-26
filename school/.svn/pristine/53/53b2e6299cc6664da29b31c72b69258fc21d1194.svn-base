package com.topview.school.service.appraise.appraiseSubjectTemplate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.service.base.Process;
import com.topview.school.dao.appraise.AppraiseSubjectTemplateMapper;
import com.topview.school.service.base.Context;


@Service
public class AppraiseSubjectDeleteProcess extends Process{
	@Autowired
	private AppraiseSubjectTemplateMapper appraiseSubjectMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseSubjectDeleteProcess.class);
	
	
	@Override
	public boolean doProcess(Context context) {
		AppraiseSubjectRequest asrequest = (AppraiseSubjectRequest) context
				.getRequest();
		try{
			appraiseSubjectMapper.deleteByPrimaryKey(asrequest.getId());
			asrequest.setSuccess(true);
			return true;
		}catch(Exception e){
			logger.error("delet appraiseSubjectTemplate fail");
			asrequest.setSuccess(false);
			return false;
		}
	}

}
