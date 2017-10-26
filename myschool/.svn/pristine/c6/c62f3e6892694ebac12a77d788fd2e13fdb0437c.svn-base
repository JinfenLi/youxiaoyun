package com.topview.school.service.clazz.score;

import java.util.List;

import com.topview.school.po.Score;
import com.topview.school.vo.exam.ScoreInfoResult;
import com.topview.school.vo.exam.ScoreVoForStudent;

public interface ScoreService {

	/**
	 * 教师端获取学生成绩
	 * @param examId
	 * @param studentId
	 * @return
	 */
	public ScoreInfoResult getScores(String examId, String studentId);

	/**
	 * 解析并保存成绩excel
	 * 
	 * @param fileName
	 * @param realPath
	 * @param examId
	 * @return
	 */
	public boolean saveScore(String fileName, String realPath, String examId);

	/**
	 * 根据学期id和学生id查询学生所有考试及成绩
	 * 
	 * @param semesterId
	 * @param studentId
	 * @return
	 */
	public List<ScoreVoForStudent> getScoreForStudent(String semesterId,
			String studentId, String clazzId);

	/**
	 * 根据学生id和考试id查询学生成绩
	 * @param studentId
	 * @param examId
	 * @return
	 */
	public Score selectByStudentIdAndExamId(String studentId, String examId);

	/**
	 * 根据考试id删除该考试所有成绩
	 * @param examId
	 * @return
	 */
	public boolean deleteByExamId(String examId);
	
	/**
	 * 
	* @Title: getScoreByExamIdAndClazzId
	* @Description: 根据考试id和班级id查询所有成绩
	* @param @param examId
	* @param @param clazzId
	* @param @return    参数
	* @return ScoreInfoResult    返回类型
	* @throws
	 */
	public ScoreInfoResult getScoreByExamIdAndClazzId(String examId, String clazzId);
	
	/**
	 * 
	* @Title: getScoreByExamIdAndGradeId
	* @Description: 根据考试id和年级id查询所有成绩
	* @param @param examId
	* @param @param gradeId
	* @param @return    参数
	* @return ScoreInfoResult    返回类型
	* @throws
	 */
	public ScoreInfoResult getScoreByExamIdAndGradeId(String examId, String gradeId);
}
