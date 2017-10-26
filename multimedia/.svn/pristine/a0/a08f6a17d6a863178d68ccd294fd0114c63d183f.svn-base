package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateTeacherMapper;
import com.topview.multimedia.po.EvaluationTemplateTeacher;

/** * @author  Rachel 
@date 创建时间：2016年10月16日 下午8:48:37 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateDeleteByTeacherProcess implements EvaluationProcess {
	private static final Logger logger = Logger.getLogger(TemplateDeleteByTeacherProcess.class);
	@Resource
	private EvaluationTemplateTeacherMapper ettm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			EvaluationTemplateTeacher ett = new EvaluationTemplateTeacher();
			ett.setUserId(context.getEti().getUploaderId());
			ett.setTemplateId(context.getEti().getId());
			ettm.deleteByTeacher(ett);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("delete template fail"+e.getMessage());
			e.printStackTrace();
			context.getEtir().setSuccess(false);
			return false;
		}	
	}

}
