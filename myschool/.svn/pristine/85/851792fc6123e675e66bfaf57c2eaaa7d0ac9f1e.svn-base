package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Subject;

public interface SubjectMapper extends BaseDao<Subject>{
	
	/**
	 * 根据部门id获取学科
	 * @param id
	 * @return
	 */
	public Subject selectByDepartmentsId(String id);
	
	/**
	 * 根据课程id获取该课程所属学科
	 * @param id
	 * @return
	 */
	public Subject selectByCurriculaId(String id);
	
	/**
	 * 根据老师id查询老师所教学科
	 * @param teacher_id
	 * @return
	 */
	
	public Subject getTeacherSubject(String teacher_id);
	
	/**
	 * 根据科目名称及学校id获取学科
	 * @param subjectName
	 * @param schoolId
	 * @return
	 */
	public Subject selectByNameAndSchoolId(String subjectName, String schoolId);
	
	/**
	 * 根据学校id获取该学校所有科目
	 * @param schoolId
	 * @return
	 */
	public List<Subject> selectBySchoolId(String schoolId);
	
	/**
	 * 根据教师id、学期id、班级id查找该教师对该班担任的科目
	 * @param teacherId
	 * @param classId
	 * @param semesterId
	 * @return
	 */
	public List<Subject> getSubjectsForTeacher(String teacherId,
			String classId, String semesterId);
	
	
}