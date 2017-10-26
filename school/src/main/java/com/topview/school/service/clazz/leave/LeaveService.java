package com.topview.school.service.clazz.leave;

import javax.servlet.http.HttpSession;

import com.topview.multimedia.bean.Pager;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

public interface LeaveService {

	/**
	 * 保存请假申请
	 * @param session
	 * @param leaveInfo
	 * @return
	 */
	public LeaveInfoResult saveLeave(HttpSession session, LeaveInfo leaveInfo);
	
	/**
	 * 老师与家长查看已提交的请假申请
	 * @param session
	 * @param pager
	 * @return
	 */
	public LeaveInfoResult getLeave(HttpSession session, Pager pager);
	
	
	/**
	 * 教师提交审核意见
	 * @param session
	 * @param leaveInfo
	 * @return
	 */
	public LeaveInfoResult updateLeave(HttpSession session, LeaveInfo leaveInfo);
	
	/**
	 * 家长端删除未审核请假条
	 * @param session
	 * @param leaveId
	 * @return
	 */
	public LeaveInfoResult deleteNoCheckLeave(HttpSession session, String leaveId);
	
	/**
	 * 根据请假条id获取请假申请详情
	 * @param leaveId
	 * @return
	 */
	public LeaveInfoResult getLeaveByLeaveId(HttpSession session, String leaveId);
	
	/**
	 * 根据教师的id来获取请假条的数量
	 * @param leaveInfo
	 * @param semester
	 * @return
	 */
	public LeaveInfoResult getCountByTeacherId(LeaveInfo leaveInfo,String semesterId);
	
}
