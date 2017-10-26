package com.topview.multimedia.dao;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.Evaluation;

public interface EvaluationMapper extends BaseDao<Evaluation>{
	
	
	public Integer getStudentScore(Map<String,Object> map);
	
	public List<Map<String,Object>> getSortedEvaluation(Map<String,Object> map);
   
}