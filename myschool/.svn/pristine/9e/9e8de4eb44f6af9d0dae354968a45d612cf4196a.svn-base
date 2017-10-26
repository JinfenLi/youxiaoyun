package com.topview.school.dao.school;

import java.util.List;

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
	 * 指定某个学期作为当前学期
	 * @param semesterId
	 * @return
	 */
	public int setCurrentSemester(String semesterId);
	
	/**
	 * 根据学校id删除学期
	 * @param schoolId
	 * @return
	 */
	public int delectSemesterBySchoolId(String schoolId);
	
	public List<SemesterVo> selectHistorySemester(String schoolId);
}