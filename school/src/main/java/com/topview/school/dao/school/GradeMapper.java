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
	
	/**
	 * @dateTime 2016年7月13日下午12:59:34
	 * @author zjd
	 * @description 根据年级name和学校id判断年级是否存在
	 */
	public List<Grade> isExist(String schoolId, String name);
	
}