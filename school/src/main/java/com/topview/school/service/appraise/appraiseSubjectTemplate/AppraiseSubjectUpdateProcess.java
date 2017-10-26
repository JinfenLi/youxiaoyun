/**
 * 
 */
package com.topview.school.service.appraise.appraiseSubjectTemplate;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseSubjectTemplateMapper;
import com.topview.school.po.AppraiseSubjectTemplate;

/**
 * 
 * 
 * @author chenzufeng <1023284613@qq.com>
 * @date 2016年1月23日
 * @CopyRight  2016 Topview Inc
 * @version V1.0
 */
@Service
public class AppraiseSubjectUpdateProcess implements AppraiseSubjectProcess{

	@Resource
	private AppraiseSubjectTemplateMapper appraiseSubjectMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseSubjectUpdateProcess.class);
	
	
	
	
	@Override
	public boolean doProcess(AppraiseSubjectProcessContext context) {
		try{
			AppraiseSubjectTemplate info = new AppraiseSubjectTemplate();
			info.setId(context.getInfo().getId());
			info.setSign(context.getInfo().getSign());
			appraiseSubjectMapper.updateByPrimaryKeySelective(info);
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e){
			logger.error("update AppraiseSubjectTemplate error");
			context.getResult().setSuccess(false);
			return false;
		}
	
	}

	
	
	
	
}
