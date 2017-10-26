package com.topview.school.dao.curricula;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.CurriculaTime;

public interface CurriculaTimeMapper extends BaseDao<CurriculaTime>{
	public List<CurriculaTime> selectByTscCurriculaVariableId(String id);
	
	/**
	 * 根据选课id删除所有上课时间记录
	 * @param curriculaVariableId
	 * @return
	 */
	public int deleteByCurriculaVariableId(String curriculaVariableId);
}