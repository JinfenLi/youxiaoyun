package com.topview.school.dao.exam.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.exam.ExamMapper;
import com.topview.school.po.Exam;

/**
 * 
 * 项目名称：school <br>
 * 类名称：ExamMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:43:21 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:43:21 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class ExamMapperImpl extends BaseDaoImpl<Exam>
		implements ExamMapper {


	@Override
	public List<Exam> findByMulti(Map<String, Object> params) {
		return template.selectList(getStatement("findByMulti"), params);
	}


	public List<Exam> selectMyExam(Map<String, Object> map) {

		return template.selectList(getStatement("getMyExamList"), map);
	}
	
	public List<Exam> getSubjectExamHistory(Map<String, Object> map) {

		return template.selectList(getStatement("getSubjectExamHistory"), map);
	}

	@Override
	public List<Exam> getExamList(Map<String, Object> map) {
		return template.selectList(getStatement("getExamList"), map);
	}


	@Override
	public List<Exam> getExams(Map<String, Object> map) {
		return template.selectList(getStatement("getExams"), map);
	}

	/**
	 * 查询考试记录
	 * @param map
	 * @return
	 */
	public List<Exam> selectExamRecord(Map<String, Object> map) {
		return template.selectList(getStatement("selectExamRecord"), map);
	}


	/**
	 * 查询记录条数
	 */
	@Override
	public int selectExamRecordCount(Map<String, Object> map) {
		return template.selectOne(getStatement("selectExamRecordCount"), map);
	}
	
	/**
	 * 根据多个课程id查询考试记录（按时间正序排序）
	 * @return
	 */
	public List<Exam> selectExamsByCurriculaVariableIds(List<String> curriculaVarriableIds) {
		return template.selectList(getStatement("selectExamsByCurriculaVariableIds"), curriculaVarriableIds);
	}


	@Override
	public List<Exam> selectByCurriculaVariableId(String curriculaVariableId) {
		return template.selectList(getStatement("selectByCurriculaVariableId"), curriculaVariableId);
	}


	@Override
	public int updateExamStatus(Map<String, Object> map) {
		return template.update("updateStatus",map);
	}


	@Override
	public List<Exam> selectAllTeacherExam(Map<String, Object> map) {
		return template.selectList(getStatement("selectAllTeacherRecord"), map);
	}


	@Override
	public int selectAllTeacherRecordCount(Map<String, Object> map) {
		return template.selectOne(getStatement("selectAllTeacherExamCount"), map);
	}
	
	@Override
	public List<Exam> getExamByCurriculaIdAndTermId(String curriculaId, String termId, String clazzId) {
		Map<String, Object> params = new HashMap<>();
		params.put("curriculaId", curriculaId);
		params.put("termId", termId);
		params.put("clazzId", clazzId);
		return template.selectList(getStatement("getExamByCurriculaIdAndTermId"), params);
	}


	@Override
	public String getSubjectByExamId(String examId) {
		return template.selectOne(getStatement("getSubjectByExamId"), examId);
	}
}
