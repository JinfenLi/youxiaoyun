package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.StudentGroup;

public interface StudentGroupMapper extends BaseDao<StudentGroup>{
	
	public int deleteByPo(StudentGroup sg);
   
}