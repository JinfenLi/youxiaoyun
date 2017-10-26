package com.topview.school.dao.leave;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.LeaveAccessory;

public interface LeaveAccessoryMapper extends BaseDao<LeaveAccessory>{
   //根据请假条id获取附件列表
	public List<LeaveAccessory> selectByLeaveId(String id);
	//根据请假条id删除附件列表
	public int deleteByLeaveId(String leaveId);
}