package com.topview.school.service.school.semester;

import java.util.List;

import com.topview.school.vo.school.SemesterVo;

public interface SemesterService {

	/**
	 * 新建学期
	 * @param schoolId
	 * @return
	 */
	public boolean createSemester(SemesterVo vo);
	
	/**
	 * 选择一个学期作为当前学期
	 * @param vo
	 * @return
	 */
	public boolean setCurrentSemester(String semesterId);
	
	/**
	 * 根据学校id获取该学校的所有学期
	 * @param schoolId
	 * @return
	 */
	public List<SemesterVo> findAll(String schoolId);
	
	/**
	 * 根据学校id获取该学校当前学期
	 * @param schoolId
	 * @return
	 */
	public SemesterVo getCurrentSemester(String schoolId);
	
	/**
	 * 根据学校id获取当前和历史学期
	 * @param schoolId
	 * @return
	 */
	public List<SemesterVo> selectHistorySemester(String schoolId);
}
