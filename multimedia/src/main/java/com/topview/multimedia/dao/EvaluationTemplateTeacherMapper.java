package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.EvaluationTemplateTeacher;


public interface EvaluationTemplateTeacherMapper extends BaseDao<EvaluationTemplateTeacher>{
	
   public int deleteByTeacher(EvaluationTemplateTeacher ett);
}