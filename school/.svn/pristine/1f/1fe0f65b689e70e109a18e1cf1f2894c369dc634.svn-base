package com.topview.school.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.dao.EvaluationTemplateTeacherMapper;
import com.topview.multimedia.po.EvaluationTemplateTeacher;
import com.topview.school.dao.user.TeacherMapper;
//import com.topview.school.dao.user.TeacherMapper;
//import com.topview.school.po.Teacher;

/** * @author  Rachel 
@date 创建时间：2016年9月21日 下午9:22:19 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class TemplateSaveProcess implements SchoolEvaluationProcess {

	@Resource
	private EvaluationTemplateMapper etm;
	@Resource
	private EvaluationTemplateTeacherMapper ettm;
	@Resource
	private TeacherMapper tm;
	
	private static final Logger logger = Logger.getLogger(TemplateSaveProcess.class);
	@Override
	public boolean doProcess(SchoolEvaluationProcessContext context) {
		try {
			EvaluationTemplateTeacher ett = new EvaluationTemplateTeacher();
			ett.setTemplateId(context.getEti().getId());
			ett.setUserId(context.getEti().getTeacherId());
			ettm.insert(ett);
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
