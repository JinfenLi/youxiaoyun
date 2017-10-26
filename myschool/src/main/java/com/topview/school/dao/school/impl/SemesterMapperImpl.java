package com.topview.school.dao.school.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.po.Semester;
import com.topview.school.vo.school.SemesterVo;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：SemesterMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:47:59  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:47:59  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class SemesterMapperImpl extends BaseDaoImpl<Semester> implements SemesterMapper{

	@Override
	public Semester getSemesterBySchoolId(String school_id) {
		return template.selectOne("getSemesterBySchoolId", school_id);
	}

	@Override
	public List<SemesterVo> findAll(String schoolId) {
		return template.selectList(getStatement("findAll"), schoolId);
	}

	@Override
	public SemesterVo getCurrentSemester(String schoolId) {
		return template.selectOne(getStatement("getCurrentSemester"), schoolId);
	}

	@Override
	public int setCurrentSemesterDefaul(String schoolId) {
		return template.update(getStatement("setCurrentSemesterDefaul"), schoolId);
	}

	@Override
	public int setCurrentSemester(String semesterId) {
		return template.update(getStatement("setCurrentSemester"), semesterId);
	}

	@Override
	public int delectSemesterBySchoolId(String schoolId) {
		return template.delete(getStatement("delectSemesterBySchoolId"), schoolId);
	}

	@Override
	public List<SemesterVo> selectHistorySemester(String schoolId) {
		return template.selectList(getStatement("selectHistorySemester"), schoolId);
	}
}
