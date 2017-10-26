package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;

/** * @author  Rachel 
@date 创建时间：2016年10月25日 下午6:05:25 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateCountByUserProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(TemplateCountByUserProcess.class);
	@Resource
	private EvaluationTemplateMapper etm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		context.getEtir().setSuccess(false);
		try {
			int count = etm.selectCount(context.getEti().getUploaderId());
			if(count>=4){
				context.getEtir().setCode(400);
				return false;
			}else{
				context.getEtir().setSuccess(true);
				return true;
			}
		} catch (Exception e) {
			logger.error("select template count fail"+e.getMessage());
			e.printStackTrace();
			context.getEtir().setCode(500);
			return false;
		}
	}

}
