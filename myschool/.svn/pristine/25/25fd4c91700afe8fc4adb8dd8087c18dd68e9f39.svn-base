package com.topview.school.dao.school.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.po.Clazz;
import com.topview.school.vo.curricula.ClassCurriculaInfo;

/**
 * 
 * 项目名称：school <br>
 * 类名称：ClazzMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:24:45 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:24:45 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class ClazzMapperImpl extends BaseDaoImpl<Clazz> implements ClazzMapper {

	@Override
	public String findSchoolIdByStudentId(String id) {
		return template.selectOne(getStatement("findSchoolIdByStudentId"), id);
	}

	@Override
	public List<Clazz> findByTeacherId(String teacher_id){
		return template.selectList(getStatement("findByTeacherId"), teacher_id);
	}
	
	@Override
	public Clazz findByStudentId(String student_id){
		return template.selectOne(getStatement("findByStudentId"), student_id);
	}

	@Override
	public List<ClassCurriculaInfo> findCurriculaByClassId(String id, String semesterId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("semesterId", semesterId);
		return template.selectList(getStatement("findCurricula"), params);
	}

	@Override
	public List<Clazz> getClazzByGradeId(String gradeId) {
		return template.selectList(getStatement("getClazzByGradeId"), gradeId);
	}

	@Override
	public List<Clazz> selectTeacherClazzs(String teacherId, String semesterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacherId", teacherId);
		map.put("semesterId", semesterId);
		return template.selectList(getStatement("selectTeacherClazzs"), map);
	}

	
}
