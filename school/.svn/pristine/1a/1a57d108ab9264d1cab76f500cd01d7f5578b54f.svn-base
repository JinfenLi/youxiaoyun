package com.topview.school.service.evaluation;

import java.util.List;

import javax.annotation.Resource;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.vo.EvaluationTemplateInfo;
import com.topview.multimedia.vo.result.EvaluationTemplateInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年9月23日 上午11:52:02 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class SchoolEvaluationServiceImpl implements SchoolEvaluationService {
	
	List<SchoolEvaluationProcess> templateSaveProcesses;
	
	@Resource
	private EvaluationTemplateMapper etm;

	public List<SchoolEvaluationProcess> getTemplateSaveProcesses() {
		return templateSaveProcesses;
	}

	public void setTemplateSaveProcesses(
			List<SchoolEvaluationProcess> templateSaveProcesses) {
		this.templateSaveProcesses = templateSaveProcesses;
	}

	@Override
	public EvaluationTemplateInfoResult templateSave(
			EvaluationTemplateInfo info) {
		return doTemplateProcess(info,templateSaveProcesses);
	}
	
	public EvaluationTemplateInfoResult doTemplateProcess(EvaluationTemplateInfo et,
			List<SchoolEvaluationProcess> processes) {
		SchoolEvaluationProcessContext context = new SchoolEvaluationProcessContext();
		EvaluationTemplateInfoResult result = new EvaluationTemplateInfoResult();
		context.setEtir(result);
		context.setEti(et);
		for (SchoolEvaluationProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getEtir();
	}

}
