package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.service.file.FileUpdateProcess;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月22日 下午5:46:43 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateUpdateProcess implements EvaluationProcess {
	
	@Resource
	private EvaluationTemplateMapper etm;
	private static final Logger logger = Logger
			.getLogger(TemplateUpdateProcess.class);

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		context.getEtir().setSuccess(false);
		try {
			EvaluationTemplate et = EvaluationTemplateInfo.changeToPo(context.getEti());
			etm.updateByPrimaryKeySelective(et);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("update template fail" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
