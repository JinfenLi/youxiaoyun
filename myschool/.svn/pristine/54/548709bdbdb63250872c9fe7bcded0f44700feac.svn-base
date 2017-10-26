/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月4日 下午10:29:52 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.po.Appraise;
import com.topview.school.vo.appraise.AppraiseInfo;

/** 
 * @ClassName: AppraiseUpdateProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月4日 下午10:29:52 
 *  
 */
@Service
public class AppraiseUpdateProcess implements AppraiseProcess{

	@Autowired
	private AppraiseMapper appraiseMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseUpdateProcess.class);
	
	
	@Override
	public boolean doProcess(AppraiseProcessContext context) {
		try{
			Appraise appraise = AppraiseInfo.changeToPo(context.getInfo());
		    appraiseMapper.updateByPrimaryKeySelective(appraise);
		    context.getResult().setSuccess(true);
		}catch(Exception e) {
			logger.error("photo update process fail");
			context.getResult().setSuccess(false);	
			return false;
		}
		
		
		return false;
	}

}
