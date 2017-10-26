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
}