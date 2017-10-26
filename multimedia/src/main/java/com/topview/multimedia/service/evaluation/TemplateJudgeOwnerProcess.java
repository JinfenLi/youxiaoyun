package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.dao.EvaluationTemplateTeacherMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.po.EvaluationTemplateTeacher;
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
	@Resource
	private EvaluationTemplateTeacherMapper ettm;
	private static final Logger logger = Logger
			.getLogger(TemplateJudgeOwnerProcess.class);
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		context.getEtir().setSuccess(false);
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("user_id", context.getEti().getTeacherId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<EvaluationTemplateTeacher> ett = ettm.findByMulti(param);
			Map<String,Object> params1 = new HashMap<String,Object>();
			params1.put("privacy", "public");
			params1.put("school_id", context.getEti().getSchoolId());
			Map<String, Object> param1 = new HashMap<String, Object>();
			param1.put("global", "global");
			param1.put("params", params1);
			List<EvaluationTemplate> templateList = etm.findByMulti(param1);
			Map<String,Object> params3 = new HashMap<String,Object>();
			params3.put("uploader_id", context.getEti().getTeacherId());
			params3.put("type", "pass");
			Map<String,Object> param3 = new HashMap<String,Object>();
			param3.put("params", params3);
			List<EvaluationTemplate> list3 = etm.findByMulti(param3);
			for(EvaluationTemplateTeacher t:ett){
				EvaluationTemplate e = etm.selectByPrimaryKey(t.getTemplateId());
				templateList.add(e);
			}
			templateList.addAll(list3);
			List<EvaluationTemplateInfo> list = EvaluationTemplateInfo.changeToVo(templateList);
			if(list.size()>0){
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
