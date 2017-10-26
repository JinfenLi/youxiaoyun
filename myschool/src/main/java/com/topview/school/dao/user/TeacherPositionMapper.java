package com.topview.school.dao.user;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.TeacherPosition;

public interface TeacherPositionMapper extends BaseDao<TeacherPosition>{

	public List<TeacherPosition> getPositions(String teacher_id);
	
	public int getCount(String schoolId);
	
	public List<TeacherPosition> getAllBySchoolId(int offset, int limit, String schoolId);
}