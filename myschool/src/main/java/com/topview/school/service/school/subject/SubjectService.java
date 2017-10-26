package com.topview.school.service.school.subject;

import java.util.List;

import com.topview.school.po.Subject;
import com.topview.school.vo.school.SubjectVo;

public interface SubjectService {

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return Subject
	 * @throws
	 */
	public Subject selectByPrimaryKey(String id);

	/**
	 * 创建一门学科
	 * 
	 * @param vo
	 * @return
	 */
	public Subject createSubject(Subject subject);

	/**
	 * 根据学科id删除学科
	 * 
	 * @param subjectId
	 * @return
	 */
	public boolean delectSubject(String subjectId);

	/**
	 * 根据部门id获取学科
	 * 
	 * @param id
	 * @return
	 */
	public Subject selectBydeparmentsId(String id);

	/**
	 * 根据课程id获取学科
	 * 
	 * @param id
	 * @return
	 */
	public Subject selectByCurriculaId(String id);

	/**
	 * 根据老师id获取学科
	 * 
	 * @param id
	 * @return
	 */
	public Subject getTeacherSubject(String id);

	/**
	 * 根据学科名称和学校id获取学科
	 * 
	 * @param subjectName
	 * @param schoolId
	 * @return
	 */
	public Subject selectByNameAndSchoolId(String subjectName, String schoolId);

	/**
	 * 根据学校id获取所有学科
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Subject> selectBySchoolId(String schoolId);

	/**
	 * 修改学科信息
	 * 
	 * @param vo
	 * @return
	 */
	public boolean updateSubject(SubjectVo vo);
	
	/**
	 * 根据选课表获取该老师所教的科目
	 * @param teacherId
	 * @param classId
	 * @param semesterId
	 * @return
	 */
	public List<Subject> getSubjectsForTeacher(String teacherId,String classId,String semesterId);

}
