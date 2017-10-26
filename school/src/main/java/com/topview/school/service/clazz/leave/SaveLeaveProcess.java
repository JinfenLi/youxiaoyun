package com.topview.school.service.clazz.leave;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.po.Leave;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

@Service
public class SaveLeaveProcess implements LeaveProcess {

	private static final Logger logger = Logger
			.getLogger(SaveLeaveProcess.class);
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public boolean doProcess(LeaveProcessContext context) {
		
		LeaveInfoResult finalResult = context.getResult();
		UserInfo userInfo = (UserInfo) context.getSession().getAttribute("currentUser");
		LeaveInfo info = context.getInfo();
		
		Leave leave = new Leave();
		leave.setId(info.getId());
		leave.setReason(info.getReason());
		leave.setStatus(info.getStatus()); //未审核
		leave.setType(info.getType());
		leave.settScParentId(userInfo.getUser_id());
		leave.setStudentId(info.getStudentId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date applyTime = df.parse(info.getApplyTime());
			leave.setApplyTime(applyTime);
			if(leaveMapper.insert(leave) > 0) { //保存请假申请
				finalResult.setSuccess(true);
				return true;
			}
		} catch (ParseException e) {
			logger.error("save leave faile", e);
			finalResult.setSuccess(false);
			finalResult.setCode(-1);
			return false;
		}
		return false;
	}
}
