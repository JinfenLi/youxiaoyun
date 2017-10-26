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
@date 创建时间：2016年10月26日 下午5:20:34 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateAvailableProcess implements EvaluationProcess {
	private static final Logger logger = Logger.getLogger(TemplateAvailableProcess.class);
	@Resource
	private EvaluationTemplateTeacherMapper ettm;
	@Resource
	private EvaluationTemplateMapper etm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			EvaluationTemplateInfo info = context.getEti();
			EvaluationTemplate template = EvaluationTemplateInfo.changeToPo(info);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("school_id", template.getSchoolId());
			params.put("type", template.getType());
			params.put("privacy", template.getPrivacy());
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("params", params);
			List<EvaluationTemplate> list1 = etm.findByMulti(param);
			Map<String,Object> params1 = new HashMap<String,Object>();
			params1.put("user_id", info.getTeacherId());
			Map<String,Object> param2 = new HashMap<String,Object>();
			param2.put("params", params1);
			List<EvaluationTemplateTeacher> list2 = ettm.findByMulti(param2);
			Map<String,Object> params3 = new HashMap<String,Object>();
			params3.put("uploader_id", info.getTeacherId());
			params3.put("type", "pass");
			Map<String,Object> param3 = new HashMap<String,Object>();
			param3.put("params", params3);
			List<EvaluationTemplate> list3 = etm.findByMulti(param3);
			for(EvaluationTemplateTeacher e : list2){
				EvaluationTemplate t = etm.selectByPrimaryKey(e.getTemplateId());
				if(list1.contains(t)){
					list1.remove(t);
				}
			}
			list1.removeAll(list3);
			List<EvaluationTemplateInfo> templateListInfo = EvaluationTemplateInfo.changeToVo(list1);
			context.getEtir().setInfoResult(templateListInfo);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("get available template fail"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
