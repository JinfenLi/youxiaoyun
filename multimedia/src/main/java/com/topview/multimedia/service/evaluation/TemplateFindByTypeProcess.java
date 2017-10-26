package com.topview.multimedia.service.evaluation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年10月20日 下午10:57:29 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateFindByTypeProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(TemplateFindByTypeProcess.class);
	@Resource
	private EvaluationTemplateMapper etm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			EvaluationTemplateInfo info = context.getEti();
			EvaluationTemplate template = EvaluationTemplateInfo.changeToPo(info);
			Map<String,Object> params = new HashMap<String,Object>();
			if(template.getType()!=null&&!"".equals(template.getType())){
				params.put("type", template.getType());
			}
			params.put("school_id", template.getSchoolId());
			Map<String,Object> param= new HashMap<String,Object>();
			param.put("params", params);
			List<EvaluationTemplate> list = etm.findByMulti(param);
			List<EvaluationTemplateInfo> eti = EvaluationTemplateInfo.changeToVo(list);
			context.getEtir().setInfoResult(eti);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("find template by type fail"+e.getMessage());
			e.printStackTrace();
			context.getEtir().setSuccess(false);
			return false;
		}
	}

}
