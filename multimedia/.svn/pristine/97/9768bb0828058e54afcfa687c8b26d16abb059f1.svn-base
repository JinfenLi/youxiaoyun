package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年10月16日 下午10:07:03 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateFindByIdProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(TemplateFindByIdProcess.class);
	@Resource
	private EvaluationTemplateMapper etm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		
		
		try {
			EvaluationTemplate etl = etm.selectByPrimaryKey(context.getEti().getId());
			EvaluationTemplateInfo eti = EvaluationTemplateInfo.changeToVo(etl);
			List<EvaluationTemplateInfo> list = new ArrayList<EvaluationTemplateInfo>();
			list.add(eti);
			context.getEtir().setInfoResult(list);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("save template's users fail"+e.getMessage());
			e.printStackTrace();
			context.getEtir().setSuccess(false);
			return false;
		}
	}

}
