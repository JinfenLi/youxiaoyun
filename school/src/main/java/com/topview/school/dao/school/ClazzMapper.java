package com.topview.school.dao.school;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * @dateTime 2016年7月13日上午11:36:54
	 * @author zjd
	 * @description 根据年级id和班级名字判断是否班级是否唯一
	 */
	public List<Clazz> isExist(String grade_id, String name);
	
	/**
	 * 
	* @Title: levelUpClazzesByGradeId
	* @Description: 根据年级id升级班级
	* @param @param map
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public int levelUpClazzesByGradeId(Map<String, Object> map);
}