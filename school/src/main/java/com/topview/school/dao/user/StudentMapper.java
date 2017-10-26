package com.topview.school.dao.user;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Student;

public interface StudentMapper extends BaseDao<Student>{
	
	public List<Student> findByMulti(Map<String, Object> params);
	/**
	 * 根据班级id查询学生（按学号排序）
	 * @param id
	 * @return
	 */
	public List<Student> selectByClazzId(String clazzId);
	
	/**
	 * 
	* @Title: findByNameAndPassword 
	* @Description: 父母用学生账号登陆
	* @param @param params
	* @param @return   
	* @return Student   
	* @throws
	 */
//	public Student findByNameAndPassword(Map<String, Object> params);
	
	public List<Student> findByParentId(String parent_id);
	
	/**
	 * 根据学生姓名和学号查询学生
	 * @param name
	 * @param idCard
	 * @return
	 */
	public Student findByNameAndIdCard(String name, String idCard);
	
	/**
	 * 
	 */
	public List<Student> getAllStudentBySchool(String schoolId);
	
	/**
	 * @dateTime 2016年7月20日上午10:46:37
	 * @author zjd
	 * @description 根据年级id获取所有的学生
	 */
	public List<Student> getAllStudentByGradeId(String gradeId);
	
	/**
	 * 根据父母id和学生名字查询
	 * @param map
	 * @return
	 */
	public List<Student> findByParentIdAndName(Map<String, Object> map);
	
	/**
	 * 根据学生查找所在学校名称
	 * @param map
	 * @return
	 */
	public String findSchoolNameByStudent(Map<String, Object> map);
	
	/**
	 * 通过班级id来获取班级学生总数
	 * @param clazzId
	 * @return
	 */
	public int countByClazzId(String clazzId);
	
	/**
	 * 通过id来查询学生
	 * @param id
	 * @return
	 */
	public Student queryById(String id);
}