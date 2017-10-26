package com.topview.school.dao.exam;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.ExamTemplet;

public interface ExamTempletMapper extends BaseDao<ExamTemplet>{
   
	public List<ExamTemplet> selectAll();
}