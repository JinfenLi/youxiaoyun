/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月29日 上午10:55:03 
 * @version V1.0
 */
package com.topview.school.service.collect;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.collect.CollectService;
import com.topview.multimedia.vo.result.CollectInfoResult;

/** 
 * @ClassName: PersonCollectFindAllProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月29日 上午10:55:03 
 *  
 */
@Service
public class PersonCollectFindAllProcess implements PersonCollectProcess{

	
	private static final Logger logger = Logger
			.getLogger(PersonCollectFindAllProcess.class);
	
	@Autowired
	private CollectService collectService;
	
	@Override
	public boolean doProcess(PersonCollectProcessContext context) {
		try{
			System.out.println(context.getInfo().getUserid()+"##");
		CollectInfoResult result = collectService.collectFindAll(context.getInfo());
		context.getResult().setInforesult(result.getInforesult());
		context.getResult().setSuccess(true);
	   return true;
		}catch(Exception e) {
			logger.error("person find all collect fail");
			context.getResult().setSuccess(false);
			return false;
		}
		
	
	}

}
