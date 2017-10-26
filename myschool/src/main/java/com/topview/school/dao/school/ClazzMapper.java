package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Clazz;
import com.topview.school.vo.curricula.ClassCurriculaInfo;

public interface ClazzMapper extends BaseDao<Clazz>{
	
	public String findSchoolIdByStudentId(String id);
	
	public Clazz findByStudentId(String student_id);
	
	public List<Clazz> findByTeacherId(String teacher_id);
	
	public List<ClassCurriculaInfo> findCurriculaByClassId(String id, String semesterId);
	
	/**
	 * 根据年级id查询班级
	 * @param gradeId
	 * @return
	 */
	public List<Clazz> getClazzByGradeId(String gradeId);
	
	/**
	 * 根据学期id和教师id确定该教师本学期所带班级
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	public List<Clazz> selectTeacherClazzs(String teacherId, String semesterId);
}