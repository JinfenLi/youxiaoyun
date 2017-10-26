/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午10:05:29 
 * @version V1.0
 */
package com.topview.school.service.appraise.appraiseSubjectTemplate;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseSubjectTemplateMapper;
import com.topview.school.po.AppraiseSubjectTemplate;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.appraise.AppraiseSubjectInfo;

/** 
 * @ClassName: AppraiseSubjectSaveProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午10:05:29 
 *  
 */

@Service
public class AppraiseSubjectSaveProcess  implements AppraiseSubjectProcess{

	@Resource
	private AppraiseSubjectTemplateMapper appraiseSubjectMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseSubjectSaveProcess.class);
	
	@Override
	public boolean doProcess(AppraiseSubjectProcessContext context) {
	
		try{
			AppraiseSubjectTemplate appraiseSubject = AppraiseSubjectInfo.changeToPo(context.getInfo());
			String id = UUIDUtil.getUUID();
			appraiseSubject.setId(id);
			appraiseSubjectMapper.insert(appraiseSubject);
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e) {
			logger.error("AppraiseSubjectTemplate save process fail");
			context.getResult().setSuccess(false);	
			return false;
		}
		
	}

}
