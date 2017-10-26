package com.topview.school.dao.curricula;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.vo.curricula.ChooseCurriculaInfo;
import com.topview.school.vo.curricula.CurriculaVariableInfo2;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;

public interface CurriculaVariableMapper extends BaseDao<CurriculaVariable>{
	
	
	
	/**
	 * 根据教师id查询其所教课程
	 * @param teacherId
	 * @return
	 */
	public List<CurriculaVariable> selectByTeacherId(String teacherId);
	
	public CurriculaVariable selectByCurriculaIdAndClassId(String curriculaId, String classId);
	
	/**
	 * 根据学期id和课程id查询选课信息列表
	 * @param map
	 * @return
	 */
	public List<CurriculaVariable> selectBySemesterIdAndCurricualId(Map<String, Object> map);
	
	/**
	 * 根据学期id、年级id、学科id和教师id单条件或多条件查询选课结果
	 * @param map
	 * @return
	 */
	public List<CurriculaVariableInfo2> showCurriculaVariable(Map<String, Object> map);
	
	/**
	 * 查询记录条数
	 * @param map
	 * @return
	 */
	public int selectCount(Map<String, Object> map);
	
	/**
	 * 根据学期id和班级id查询课程
	 * @param map
	 * @return
	 */
	public List<UploadCurriculaInfoVo> selectBySemesterIdAndClazzId(Map<String, Object> map);
	
	
	public ChooseCurriculaInfo getClazzCurriculaInfo(Map<String , Object> params);
	
	
	public List<CurriculaVariable> getCurriculaByTeacherAndSemesterId(Map<String,Object> map);
}