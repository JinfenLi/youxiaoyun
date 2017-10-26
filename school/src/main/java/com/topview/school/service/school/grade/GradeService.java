package com.topview.school.service.school.grade;

import java.util.List;

import com.topview.school.po.Grade;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.school.GradeVo;

public interface GradeService {

	/**
	 * 新建年级
	 * 
	 * @param grade
	 * @return
	 */
	public boolean createGrade(Grade grade);

	/**
	 * 删除年级
	 * 
	 * @param gradeId
	 * @return
	 */
	public boolean delectGrade(String gradeId);

	/**
	 * 根据学校id获取所有年级
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Grade> getAllGrade(String schoolId);

	/**
	 * 修改年级信息
	 * 
	 * @param gradeVo
	 * @return
	 */
	public boolean updateGrade(GradeVo gradeVo);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return Grade
	 * @throws
	 */
	public Grade selectByPrimaryKey(String id);

	/**
	 * 获取当前用户所属年级
	 * @param userInfo
	 * @return
	 */
	public List<Grade> getMyGrade(UserInfo userInfo);
	
	/**
	 * @dateTime 2016年7月13日下午12:56:58
	 * @author zjd
	 * @description 根据年级名字,学校id判断是否存在
	 */
	public List<Grade> isExist(String schoolId, String name);
	
	/**
	 * 
	* @Title: levelUpGrade
	* @Description: 年级升级
	* @param @param gradeId
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean levelUpGrade(String gradeId);
}