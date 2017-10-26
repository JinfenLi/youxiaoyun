package com.topview.school.dao.leave.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.po.Leave;
import com.topview.school.vo.leave.LeaveInfo;

@Repository
public class LeaveMapperImpl extends BaseDaoImpl<Leave> implements LeaveMapper {
	
	/**
	 * 根据班级id查询请假记录条数
	 */
	@Override
	public int selectCountByClazzIdAndStatus(Map<String,Object> param) {
		return template.selectOne(getStatement("selectCountByClazzId"), param);
	}

	/**
	 * 根据班级id分页查询请假申请
	 */
	@Override
	public List<LeaveInfo> findByClazzId(Map<String, Object> map) {
		return template.selectList(getStatement("findByClazzId"), map);
	}

	@Override
	public List<LeaveInfo> findByStudentId(Map<String, Object> map) {
		return template.selectList(getStatement("findByStudentId"), map);
	}

	@Override
	public int selectCountByStudentIdAndStatus(Map<String,Object> param) {
		return template.selectOne(getStatement("selectCountByStudentId"), param);
	}

	@Override
	public LeaveInfo findByLeaveId(String leaveId) {
		return template.selectOne(getStatement("findByLeaveId"), leaveId);
	}

	@Override
	public boolean deleteLeaveByStudentId(String studentId) {
		return template.delete(getStatement("deleteLeaveByStudentId"),studentId)>0;
	}
	
//	@Override
//	public List<Leave> selectByParentId(String id) {
//		return template.selectList(getStatement("selectByParentId"), id);
//	}
//
//	@Override
//	public List<Leave> selectByClazzId(String clazzId) {
//		return template.selectList(getStatement("selectByClazzId"), clazzId);
//	}
//
//	@Override
//	public List<Leave> selectByClazzId(Map<String, Object> map) {
//		return template.selectList(getStatement("findByClazzId"), map);
//	}
//
//	@Override
//	public List<Leave> selectByParentId(Map<String, Object> map) {
//		return template.selectList(getStatement("findByParentId"), map);
//	}
//
//	@Override
//	public int selectCountByParentId(Map<String, Object> map) {
//		return template.selectOne(getStatement("selectCountByParentId"), map);
//	}
	
}