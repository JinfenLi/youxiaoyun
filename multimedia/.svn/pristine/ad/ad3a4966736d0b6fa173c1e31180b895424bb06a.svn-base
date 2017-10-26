package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;

/** * @author  Rachel 
@date 创建时间：2016年11月8日 下午9:15:11 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateRemoveProcess implements EvaluationProcess {
	private static final Logger logger = Logger.getLogger(TemplateRemoveProcess.class);
	@Resource
	private EvaluationTemplateMapper etm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			etm.deleteByPrimaryKey(context.getEti().getId());
			context.getEtir().setSuccess(true);
			return true;
		
		} catch (Exception e) {
			logger.error("remove evaluation fail"+e.getMessage());
			e.printStackTrace();
			context.getEtir().setSuccess(false);
			return false;
		}
	}

}
