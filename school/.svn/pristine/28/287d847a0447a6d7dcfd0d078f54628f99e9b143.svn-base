package com.topview.school.dao.exam;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Score;
import com.topview.school.vo.exam.ScoreInfo;

public interface ScoreMapper extends BaseDao<Score>{

	List<Score> findByMulti(Map<String, Object> param);
	
	/**
	 * 
	* @Title: getExamDetail 
	* @Description: 
	* @param @param paramMap
	* @param @return   
	* @return Score   
	* @throws
	 */
	public Score getExamDetail(Map<String, Object> paramMap);
	
	/**
	 * 
	* @Title: getSubjectExamHistory 
	* @Description: 该科目历史考试成绩
	* @param @param paramMap
	* @param @return   
	* @return Score   
	* @throws
	 */
	public Score getSubjectExamHistory(Map<String, Object> paramMap);
	
	//根据班级id和examId查询chengji
	public List<ScoreInfo> getScores(Map<String, Object> map);
	
	/**
	 * 根据考试id或考试id+学生id查询所有学生成绩
	 * @param examId
	 * @return
	 */
	public List<ScoreInfo> selectScoreByExamId(Map<String, Object> map);
	
	/**
	 * 查询某次考试的最高分
	 * @param examId
	 * @return
	 */
	public String maxScore(String examId);
	
	/**
	 * 查询某次考试的最低分
	 * @param examId
	 * @return
	 */
	public String minScore(String examId);
	
	/**
	 * 查询某次考试的平均分
	 * @param examId
	 * @return
	 */
	public String avgScore(String examId);
	
	public Score selectByStudentIdAndExamId(String studentId, String examId);

	/**
	 * 根据考试id删除所有成绩
	 * @param examId
	 * @return
	 */
	public int deleteByExamId(String examId);
	
	/**
	 * 
	* @Title: getScoreByExamIdAndGradeId
	* @Description: 根据考试id和年级id查询所有成绩
	* @param @param param
	* @param @return    参数
	* @return List<ScoreInfo>    返回类型
	* @throws
	 */
	public List<ScoreInfo> getScoreByExamIdAndGradeId(Map<String, Object> param);
	
	public boolean deleteScoreByStudentId(String studentId);
	
	/**
	 * @dateTime 2016年8月18日下午4:29:51
	 * @author zjd
	 * @description 根据班级id，课程id获取到一个班下该课程的所有成绩
	 */
	public List<Score> getScoreByClazzIdAndCurriculaIdAndTermId(String clazzId, String curriculaId, String termId);
	
	public String getExamNameByExamId(String examId);
}