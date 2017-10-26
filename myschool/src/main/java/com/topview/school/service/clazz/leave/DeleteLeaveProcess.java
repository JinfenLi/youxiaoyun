package com.topview.school.service.clazz.leave;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveAccessoryMapper;
import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.po.Leave;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.User.enums.UserType;
import com.topview.school.vo.leave.result.LeaveInfoResult;

@Service
public class DeleteLeaveProcess implements LeaveProcess {

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private LeaveAccessoryMapper leaveAccessoryMapper;

	@Override
	public boolean doProcess(LeaveProcessContext context) {

		LeaveInfoResult finalResult = context.getResult();
		UserInfo userInfo = (UserInfo) context.getSession().getAttribute(
				"currentUser");
		String leaveId = context.getInfo().getId();

		// 分步检查防止安卓刷新不及时报空指针异常
		Leave leave = leaveMapper.selectByPrimaryKey(leaveId);
		if (leave != null) {
			// 身份必须是家长并且请假申请状态必须是未审核
			if (userInfo.getUser_type() == UserType.Parent
					&& leave.getStatus() == 1) {
			
				leaveAccessoryMapper.deleteByLeaveId(leaveId);	// 先删除附件
				leaveMapper.deleteByPrimaryKey(leaveId); // 删除请假条

				finalResult.setSuccess(true);
				return true;
			}
		}
		finalResult.setSuccess(false);
		return false;
	}

}
