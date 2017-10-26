package com.topview.school.service.user.parent;

import java.util.List;

import com.topview.school.po.Parent;
//TODO update与updateByPrimaryKeySelective是两个不同的方法命名要区分开来
public interface ParentService {
	
	/**
	 * 根据手机号查询家长
	 * @param moble
	 * @return
	 */
	public Parent findByMoble(String moble);
	public boolean updatePassword(Parent parent);
	
	/**
	 * 更新家长个人资料
	 * @param parentInfo
	 * @return
	 */
	public boolean updateParent(Parent parent);
	
	/**
	 * 根据主键查询家长
	 * @param id
	 * @return
	 */
	public Parent selectByPrimaryKey(String id);
	
	/**
	 * 根据学生id查询家长(一个学生可以有多个家长)
	 * @param studentId
	 * @return
	 */
	public List<Parent> selectByStudentId(String studentId);
	
	/**
	 * 根据学生id查询主家长
	 * @param studentId
	 * @return
	 */
	public Parent selectMainParent(String studentId);
}
