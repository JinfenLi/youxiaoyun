/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午9:55:32 
 * @version V1.0
 */
package com.topview.school.service.appraise.appraiseSubjectTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseSubjectTemplateMapper;
import com.topview.school.po.AppraiseSubjectTemplate;
import com.topview.school.vo.appraise.AppraiseSubjectInfo;

/** 
 * @ClassName: AppraiseSubjectFindProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午9:55:32 
 *  
 */
@Service
public class AppraiseSubjectFindProcess implements AppraiseSubjectProcess {

	@Resource
	private AppraiseSubjectTemplateMapper appraiseSubjectMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseSubjectFindProcess.class);
	

	@Override
	public boolean doProcess(AppraiseSubjectProcessContext context) {
	
		try {
     		Map<String, Object> params = new HashMap<String, Object>();
     		params.put("sign",context.getInfo().getSign());
     		if(!context.getInfo().getSign().equals("123")){
     			params.put("subject", context.getInfo().getSubject());
     		}
     		//params.put("type",context.getInfo().getType());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);	
			List<AppraiseSubjectTemplate>  list = appraiseSubjectMapper.findByMulti(param);
     		context.getResult().setSuccess(true);
			if(list!=null) {
			List<AppraiseSubjectInfo> infos = AppraiseSubjectInfo.changeToVo(list);
			context.getResult().setInforesult(infos);
			}
			return true;
		} catch (Exception e) {
			logger.error("find AppraiseSubjectTemplate error");
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
