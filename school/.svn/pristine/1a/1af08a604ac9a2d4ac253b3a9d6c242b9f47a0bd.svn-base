package com.topview.school.service.clazz;

import java.util.List;

import com.topview.school.po.Clazz;

/**
 * @author hcdn
 *
 */
/**
 * @author hcdn
 *
 */
public interface ClazzService {
	
	/**
	 * 根据班级id获取班级信息
	 * @param id
	 * @return
	 */
	public Clazz clazzFind(String id);
	
	/**
	 * 创建一个班级
	 * @param clazz
	 * @return
	 */
	public boolean createClazz(Clazz clazz);
	
	/**
	 * 根据主键删除班级
	 * @param id
	 * @return
	 */
	public boolean deleteClazz(String id);
	
	/**
	 * 根据年级id获取年级下的所有班级
	 * @param gradeId
	 * @return
	 */
	public List<Clazz> getClazzByGradeId(String gradeId);
	
	/**
	 * 修改班级资料
	 * @param clazz
	 * @return
	 */
	public boolean updateClazz(Clazz clazz);
	
	/**
	 * 根据教师id和学期id查询老师所教的所有班级
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	public List<Clazz> selectTeacherClazzs(String teacherId, String semesterId);
	
	
	/**
	 * @dateTime 2016年7月13日上午11:30:52
	 * @author zjd
	 * @description 根据年级id和班级名字判断此班级是否存在
	 */
	public List<Clazz> isExist(String grade_id, String name);
}
