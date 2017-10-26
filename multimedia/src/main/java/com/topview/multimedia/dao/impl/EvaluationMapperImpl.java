package com.topview.multimedia.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.Evaluation;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午8:17:05 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Repository
public class EvaluationMapperImpl extends BaseDaoImpl<Evaluation> implements
		EvaluationMapper {

	

	@Override
	public Integer getStudentScore(Map<String,Object> map) {
		return template.selectOne(getFirstInterface() + ".getStudentScore", map);
	}
	@Override
	public List<Map<String,Object>> getSortedEvaluation(Map<String,Object> map) {
		return template.selectList(getFirstInterface() + ".getSortedEvaluation", map);
		
	}
	

}
