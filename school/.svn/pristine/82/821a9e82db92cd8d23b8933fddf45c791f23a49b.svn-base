package com.topview.school.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Teacher;
import com.topview.school.vo.contacts.ContactsInfo;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：TeacherMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:49:51  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:49:51  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class TeacherMapperImpl extends BaseDaoImpl<Teacher> implements TeacherMapper{

	@Override
	public List<ContactsInfo> selectByClazzId(String class_id) {
		return template.selectList(getStatement("selectByClazzId"),class_id);
	}

	@Override
	public Teacher findByNameAndPassword(Map<String, Object> params) {
		return template.selectOne(getStatement("findByNameAndPassword"), params);

	}
	
	@Override
	public List<ContactsInfo> selectAllBySchoolId(String schoolId) {
		return template.selectList(getStatement("selectAllBySchoolId"), schoolId);
	}

	@Override
	public Teacher findHeadTeacher(String classId) {
		return template.selectOne(getStatement("selectHeadTeacher"), classId);
	}

	@Override
	public List<ContactsInfo> selectByDepartmentId(String departmentId) {
		return template.selectList(getStatement("selectByDepartmentId"), departmentId);
	}

	@Override
	public List<String> selectClassNameByTeacherId(String teacherId) {
		return template.selectList(getStatement("selectClassNameByTeacherId"), teacherId);
	}

	@Override
	public Teacher selectByNameAndSchoolId(String teacherName, String schoolId) {
		Teacher t = new Teacher();
		t.setName(teacherName);
		t.settScSchoolId(schoolId);
		return template.selectOne(getStatement("selectByNameAndSchoolId"), t);
	}

	@Override
	public Teacher findByPhone(String phone) {
		return template.selectOne(getStatement("findByPhone"), phone);
	}

	@Override
	public List<Teacher> getTeacher(Map<String, Object> map) {
		return template.selectList(getStatement("getTeacher"), map);
	}

	/**
	 * 根据部门id查询老师
	 */
	@Override
	public List<Teacher> selectTeacherByDepartmentId(String departmentId) {
		return template.selectList(getStatement("selectTeacherByDepartmentId"), departmentId);
	}

	/**
	 * 根据学校id获取教师数量
	 */
	@Override
	public int selectCount(String schoolId) {
		return template.selectOne(getStatement("selectCount"), schoolId);
	}

	@Override
	public int cancelPosition(Map<String, Object> map) {
		return template.update(getStatement("cancelPosition"), map);
	}

	@Override
	public int addPosition(Map<String, Object> params) {
		return template.insert(getStatement("addPosition"), params);
	}

	@Override
	public int deletePosition(Map<String, Object> params) {
		return template.delete(getStatement("deletePosition"), params);
	}

	@Override
	public List<Teacher> selectTeacherByPositionId(Map<String, Object> map) {
		return template.selectList(getStatement("selectTeacherByPositionId"), map);
	}

	@Override
	public int getCountByPositionId(String positionId) {
		return template.selectOne(getStatement("getCountByPositionId"), positionId);
	}

	@Override
	public List<Teacher> findLike(Map<String, Object> param) {
		return template.selectList(getStatement("findLike"), param);
	}

	@Override
	public List<Teacher> getAllTeacher() {
		return template.selectList(getStatement("getAllTeacher"));
	}

	@Override
	public List<Teacher> getTeacherBySchoolId(String schoolId) {
		return template.selectList(getStatement("getTeacherBySchoolId"),schoolId);
	}

	@Override
	public List<Teacher> findLikeByName(Map<String, Object> map) {
		return template.selectList(getStatement("findLikeByName"),map);
	}

	@Override
	public int countFindLike(Map<String, Object> map) {
		return template.selectOne(getStatement("countFindLike"),map);
	}

	@Override
	public List<Teacher> selectTeacherByschoolIdAndLikeName(String schoolId, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
