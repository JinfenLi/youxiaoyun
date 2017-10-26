package com.topview.school.dao.exam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.exam.ScoreMapper;
import com.topview.school.po.Score;
import com.topview.school.vo.exam.ScoreInfo;

/**
 * 
 * 项目名称：school <br>
 * 类名称：ScoreMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:46:42 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:46:42 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class ScoreMapperImpl extends BaseDaoImpl<Score> implements ScoreMapper {

	@Override
	public List<Score> findByMulti(Map<String, Object> params) {
		return template.selectList(getStatement("findByMulti"), params);

	}

	
	public Score getExamDetail(Map<String, Object> paramMap) {

		return template.selectOne(getStatement("getExamDetail"), paramMap);
	}
	
	
	public Score getSubjectExamHistory(Map<String, Object> paramMap) {

		return template.selectOne(getStatement("getSubjectExamHistory"), paramMap);
	}


	@Override
	public List<ScoreInfo> getScores(Map<String, Object> map) {
		return template.selectList(getStatement("getScores"), map);
	}

	public List<ScoreInfo> selectScoreByExamId(Map<String, Object> map) {
		return template.selectList(getStatement("selectScoreByExamId"), map);
	}


	@Override
	public String maxScore(String examId) {
		return template.selectOne(getStatement("maxScore"), examId);
	}


	@Override
	public String minScore(String examId) {
		return template.selectOne(getStatement("minScore"), examId);
	}


	@Override
	public String avgScore(String examId) {
		return template.selectOne(getStatement("avgScore"), examId);
	}


	@Override
	public Score selectByStudentIdAndExamId(String studentId, String examId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentId);
		map.put("examId", examId);
		return template.selectOne(getStatement("selectByStudentIdAndExamId"), map);
	}


	@Override
	public int deleteByExamId(String examId) {
		return template.delete(getStatement("deleteByExamId"), examId);
	}
	
	@Override
	public List<ScoreInfo> getScoreByExamIdAndGradeId(Map<String, Object> param) {
		return template.selectList(getStatement("getScoreByExamIdAndGradeId"), param);
	}


	@Override
	public boolean deleteScoreByStudentId(String studentId) {
		return template.delete(getStatement("deleteScoreByStudent"),studentId)>0;
	}
	

	@Override
	public List<Score> getScoreByClazzIdAndCurriculaIdAndTermId(String clazzId, String curriculaId, String termId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clazzId", clazzId);
		params.put("curriculaId", curriculaId);
		params.put("termId", termId);
		return template.selectList(getStatement("getScoreByClazzIdAndCurriculaIdAndTermId"),params);
	}


	@Override
	public String getExamNameByExamId(String examId) {
		return template.selectOne(getStatement("getExamNameByExamId"),examId);
	}
}
