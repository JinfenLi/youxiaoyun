package com.topview.school.service.clazz.curricula;

import java.util.List;

import com.topview.school.po.CurriculaVariable;
import com.topview.school.vo.curricula.CurriculaVariableInfo;
import com.topview.school.vo.curricula.CurriculaVariableInfo2;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;

public interface CurriculaVariableService {

	/**
	 * 保存班级选课信息
	 * @param curriculaVariable
	 * @return
	 */
	public boolean saveCurriculaVariable(CurriculaVariable curriculaVariable);
	
	/**
	 * 根据学期id和课程id查询选课信息
	 * @param semesterId
	 * @param CurricualId
	 * @return
	 */
	public List<CurriculaVariableInfo> selectBySemesterIdAndCurriculaId(String semesterId, String CurricualId);
	
	/**
	 * 根据学期id、年级id、学科id和教师id单条件或多条件查询选课结果
	 * @param subjectId
	 * @param gradeId
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	public List<CurriculaVariableInfo2> showCurriculaVariable(String subjectId, String gradeId, String teacherId, String semesterId, Integer start, Integer limit);
	
	/**
	 * 根据班级id和学期id获取选课信息(教师id可传可不传)
	 * @param clazzId
	 * @return
	 */
	public List<UploadCurriculaInfoVo> getSubjectBySemesterIdAndClazzId(String semesterId, String clazzId, String teacherId);
	
	/**
	 * 查询记录条数
	 * @param subjectId
	 * @param gradeId
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	public int selectCount(String subjectId, String gradeId, String teacherId, String semesterId);
	
	/**
	 * 删除选课记录
	 * @param curriculaVariableId
	 * @return
	 */
	public boolean deleteCurriculaVariable(String curriculaVariableId);
}