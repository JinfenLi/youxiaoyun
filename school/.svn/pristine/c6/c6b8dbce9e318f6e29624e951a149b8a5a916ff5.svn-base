package com.topview.school.dao.school.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.SubjectMapper;
import com.topview.school.po.Subject;

/**
 * 
 * 项目名称：school <br>
 * 类名称：SubjectMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:49:20 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:49:20 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class SubjectMapperImpl extends BaseDaoImpl<Subject> implements
		SubjectMapper {

	/**
	 * 根据部门id获取学科
	 */
	@Override
	public Subject selectByDepartmentsId(String id) {
		return template.selectOne(getStatement("selectByDepartmentsId"), id);
	}

	/**
	 * 根据课程id获取学科
	 */
	@Override
	public Subject selectByCurriculaId(String id) {
		return template.selectOne(getStatement("selectByCurriculaId"), id);
	}

	/**
	 * @Title: getTeacherSubject
	 * @Description: 查找老师所教科目
	 * @param @param teacher_id
	 * @param @return
	 * @return Subject
	 * @throws
	 */
	public Subject getTeacherSubject(String teacher_id) {
		
		List<Subject> subjects = template.selectList(
				getStatement("getTeacherSubject"), teacher_id);
		if (subjects.size() > 0) {
			return subjects.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据学校id与学科名称获取学科
	 */
	@Override
	public Subject selectByNameAndSchoolId(String subjectName, String schoolId) {
		Subject s = new Subject();
		s.setName(subjectName);
		s.settScSchoolId(schoolId);
		return template.selectOne(getStatement("selectByNameAndSchoolId"), s);
	}

	/**
	 * 根据学校id获取学科
	 */
	@Override
	public List<Subject> selectBySchoolId(String schoolId) {
		return template.selectList(getStatement("selectBySchoolId"), schoolId);
	}

	
	@Override
	public List<Subject> getSubjectsForTeacher(String teacherId,
			String classId, String semesterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacherId", teacherId);
		map.put("classId", classId);
		map.put("semesterId", semesterId);
		return template.selectList(getStatement("getSubjectsForTeacher"), map);
	}

	@Override
	public List<Subject> isExist(String schoolId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schoolId", schoolId);
		map.put("name", name);
		return template.selectList(getStatement("isExist"), map);
	}

}
