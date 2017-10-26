package com.topview.multimedia.service.evaluation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.service.file.FileJudgeOwnerProcess;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月22日 上午10:58:37 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateJudgeOwnerProcess implements EvaluationProcess {

	@Resource
	private EvaluationTemplateMapper etm;
	private static final Logger logger = Logger
			.getLogger(TemplateJudgeOwnerProcess.class);
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		context.getEtir().setSuccess(false);
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("uploader_id", context.getEti().getUploaderId());
			Map<String, Object> param = new HashMap<String, Object>();
			Integer[] array = {0,1,3};
			param.put("params", params);
			param.put("array", array);
			List<EvaluationTemplate> et = etm.findByMulti(param);
			List<EvaluationTemplateInfo> list = EvaluationTemplateInfo.changeToVo(et);
			if(et.size()>0){
				context.getEtir().setInfoResult(list);
				context.getEtir().setSuccess(true);
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			logger.error("not the owner of the template"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
