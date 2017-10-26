/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月15日 下午12:40:02 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;

/** 
 * @ClassName: AppraiseDeleteProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月15日 下午12:40:02 
 *  
 */
@Service
public class AppraiseDeleteProcess implements AppraiseProcess{

	@Resource
	private AppraiseMapper appraiseMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseDeleteProcess.class);
	
	
	@Override
	public boolean doProcess(AppraiseProcessContext context) {
		try{
		String id =  context.getInfo().getId();
		appraiseMapper.deleteByPrimaryKey(id);
		context.getResult().setSuccess(true);
		return true;
		}catch(Exception e){
			logger.error("delete Appraise error");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
