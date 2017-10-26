package com.topview.school.service.clazz.leave;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.po.Leave;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

@Service
public class UpdateLeaveProcess implements LeaveProcess {

	private static final Logger logger = Logger
			.getLogger(UpdateLeaveProcess.class);

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private LeaveService leaveService;

	@Override
	public boolean doProcess(LeaveProcessContext context) {

		LeaveInfoResult finalResult = context.getResult();
		try {
			LeaveInfo info = context.getInfo();
			UserInfo userInfo = (UserInfo) context.getSession().getAttribute(
					"currentUser");
			Leave leave = leaveMapper.selectByPrimaryKey(info.getId());
			leave.setStatus(info.getStatus());
			leave.settScTeacherId(userInfo.getUser_id());
			leave.setRejectReason(info.getRejectReason());
			leaveMapper.updateByPrimaryKeySelective(leave);
		} catch (Exception e) {
			logger.error("update leave faile", e);
			e.printStackTrace();
			finalResult.setSuccess(false);
			return false;
		}
		finalResult.setSuccess(true);
		return true;
	}

}
