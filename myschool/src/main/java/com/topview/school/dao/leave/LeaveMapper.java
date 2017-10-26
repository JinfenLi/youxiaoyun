package com.topview.school.dao.leave;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Leave;
import com.topview.school.vo.leave.LeaveInfo;

public interface LeaveMapper extends BaseDao<Leave> {

	/**
	 * 根据班级id查询请假记录数
	 * 
	 * @param clazzId
	 * @return
	 */
	public int selectCountByClazzId(String clazzId);

	/**
	 * 根据学生id查询请假记录数
	 * 
	 * @param studentId
	 * @return
	 */
	public int selectCountByStudentId(String studentId);

	/**
	 * 老师根据班级id分页查询请假申请
	 * 
	 * @param map
	 *            :clazzId、limit、offset
	 * @return
	 */
	public List<LeaveInfo> findByClazzId(Map<String, Object> map);

	/**
	 * 家长根据学生id分页查询请假申请
	 * 
	 * @param map
	 * @return
	 */
	public List<LeaveInfo> findByStudentId(Map<String, Object> map);

	/**
	 * 根据请假条id查询请假信息
	 * @param leaveId
	 * @return
	 */
	public LeaveInfo findByLeaveId(String leaveId);
	// /**
	// * 根据家长id查看请假条
	// * @param id
	// * @return
	// */
	// public List<Leave> selectByParentId(String id);

	// /**
	// * 根据家长id和学生姓名分页查看请假条
	// * @param map
	// * map中必须传入的参数为parentId, studentName
	// * 分页参数为offset、limit
	// * @return
	// */
	// public List<Leave> selectByParentId(Map<String, Object> map);

	// /**
	// * 老师查看本班请假条(过时)
	// * @param clazzId
	// * @return
	// */
	// public List<Leave> selectByClazzId(String clazzId);

	// /**
	// * 老师分页查看本班请假条(过时)
	// * @param map
	// * @return
	// */
	// public List<Leave> selectByClazzId(Map<String, Object> map);

	// /**
	// * 根据家长id和学生姓名获取请假次数
	// * @param map
	// * map 中需要传入的参数是"studentName"和"parentId"
	// * @return
	// */
	// public int selectCountByParentId(Map<String, Object> map);

}