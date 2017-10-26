package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Grade;

public interface GradeMapper extends BaseDao<Grade>{

	/**
	 * 根据学校id获取所有年级
	 * @param schoolId
	 * @return
	 */
	public List<Grade> getAllGrade(String schoolId);
	
}