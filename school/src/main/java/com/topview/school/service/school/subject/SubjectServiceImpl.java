package com.topview.school.service.school.subject;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.SubjectMapper;
import com.topview.school.po.Subject;
import com.topview.school.vo.school.SubjectVo;

/**
 * @Description 学科service实现层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月29日 上午10:16:18
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SubjectServiceImpl implements SubjectService {

	@Resource
	private SubjectMapper subjectMapper;

	@Override
	public Subject selectBydeparmentsId(String id) {
		return subjectMapper.selectByDepartmentsId(id);
	}

	@Override
	public Subject selectByCurriculaId(String id) {
		return subjectMapper.selectByCurriculaId(id);
	}

	@Override
	public Subject getTeacherSubject(String id) {
		return subjectMapper.getTeacherSubject(id);
	}

	@Override
	public Subject selectByNameAndSchoolId(String subjectName, String schoolId) {
		return subjectMapper.selectByNameAndSchoolId(subjectName, schoolId);
	}

	@Override
	public List<Subject> selectBySchoolId(String schoolId) {
		return subjectMapper.selectBySchoolId(schoolId);
	}

	@Override
	public Subject createSubject(Subject subject) {
		return subjectMapper.insertSelective(subject) > 0 ? subject : null;
	}

	@Override
	public boolean delectSubject(String subjectId) {
		return subjectMapper.deleteByPrimaryKey(subjectId) > 0 ? true : false;
	}

	@Override
	public boolean updateSubject(SubjectVo vo) {
		Subject subject = SubjectVo.changeToPo(vo);
		return subjectMapper.updateByPrimaryKeySelective(subject) > 0 ? true : false;
	}

	@Override
	public Subject selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return subjectMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public List<Subject> getSubjectsForTeacher(String teacherId,
			String classId, String semesterId) {
		
		return subjectMapper.getSubjectsForTeacher(teacherId, classId, semesterId);
	}

	@Override
	public List<Subject> isExist(String schoolId, String name) {
		return subjectMapper.isExist(schoolId, name);
	}

}
