package com.topview.school.dao.leave.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.leave.LeaveAccessoryMapper;
import com.topview.school.po.LeaveAccessory;

@Repository
public class LeaveAccessoryMapperImpl  extends BaseDaoImpl<LeaveAccessory> implements LeaveAccessoryMapper {

	@Override
	public List<LeaveAccessory> selectByLeaveId(String id) {
		return template.selectList(getStatement("selectByLeaveId"), id);
	}

	@Override
	public int deleteByLeaveId(String leaveId) {
		return template.delete(getStatement("deleteByLeaveId"), leaveId);
	}

}
