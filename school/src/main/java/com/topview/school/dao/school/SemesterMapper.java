package com.topview.school.dao.school;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Semester;
import com.topview.school.vo.school.SemesterVo;

public interface SemesterMapper extends BaseDao<Semester>{

	public Semester getSemesterBySchoolId(String school_id);
	
	/**
	 * 获取指定学校的所有学期 
	 * @param schoolId
	 * @return
	 */
	public List<SemesterVo> findAll(String schoolId);
	
	/**
	 * 获取指定学校的当前学期
	 * @param schoolId
	 * @return
	 */
	public SemesterVo getCurrentSemester(String schoolId);
	
	/**
	 * 置该学校所有学期为非当前学期
	 * @return
	 */
	public int setCurrentSemesterDefaul(String schoolId);
	
	/**
	 * 根据学校id删除学期
	 * @param schoolId
	 * @return
	 */
	public int delectSemesterBySchoolId(String schoolId);
	
	/**
	 * 
	* @Title: selectHistorySemester
	* @Description: 查询当前和历史学期
	* @param @param schoolId
	* @param @return    参数
	* @return List<SemesterVo>    返回类型
	* @throws
	 */
	public List<SemesterVo> selectHistorySemester(String schoolId);
	
	/**
	 * 
	* @Title: setCurrentSemester
	* @Description: 设为当前学期
	* @param @param map
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public int setCurrentSemester(Map<String, Object> map);
	
	/**
	 * 
	* @Title: selectByName
	* @Description: 根据学期名查找
	* @param @param name
	* @param @return    参数
	* @return Semester    返回类型
	* @throws
	 */
	public Semester selectByName(String name);
}