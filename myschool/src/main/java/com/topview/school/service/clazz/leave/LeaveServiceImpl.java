package com.topview.school.service.clazz.leave;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.topview.multimedia.bean.Pager;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

public class LeaveServiceImpl implements LeaveService {

	private List<LeaveProcess> saveLeaveProcesses;
	private List<LeaveProcess> getLeaveProcesses;
	private List<LeaveProcess> updateLeaveProcesses;
	private List<LeaveProcess> deleteLeaveProcesses;
	private List<LeaveProcess> getOneLeaveProcesses;

	// 家长提交请假申请
	@Override
	public LeaveInfoResult saveLeave(HttpSession session, LeaveInfo leaveInfo) {

		LeaveProcessContext context = new LeaveProcessContext();
		LeaveInfoResult result = new LeaveInfoResult();
		context.setInfo(leaveInfo);
		context.setSession(session);
		context.setResult(result);

		for (LeaveProcess process : saveLeaveProcesses) {
			if (!process.doProcess(context))
				break;
		}
		return context.getResult();
	}

	// 家长或老师查看请假条
	@Override
	public LeaveInfoResult getLeave(HttpSession session, Pager pager) {
		LeaveProcessContext context = new LeaveProcessContext();
		LeaveInfoResult result = new LeaveInfoResult();
		context.setResult(result);
		context.setSession(session);
		context.setPager(pager);

		for (LeaveProcess process : getLeaveProcesses) {
			if (!process.doProcess(context))
				break;
		}
		return context.getResult();
	}

	// 保存老师审批意见
	@Override
	public LeaveInfoResult updateLeave(HttpSession session, LeaveInfo leaveInfo) {

		LeaveProcessContext context = new LeaveProcessContext();
		LeaveInfoResult result = new LeaveInfoResult();
		context.setResult(result);
		context.setInfo(leaveInfo);
		context.setSession(session);

		for (LeaveProcess process : updateLeaveProcesses) {
			if (!process.doProcess(context))
				break;
		}
		return context.getResult();
	}

	// 删除未审核请假条
	@Override
	public LeaveInfoResult deleteNoCheckLeave(HttpSession session,
			String leaveId) {
		LeaveProcessContext context = new LeaveProcessContext();
		LeaveInfoResult result = new LeaveInfoResult();
		LeaveInfo info = new LeaveInfo();
		info.setId(leaveId);
		context.setInfo(info);
		context.setResult(result);
		context.setSession(session);

		for (LeaveProcess process : deleteLeaveProcesses) {
			if (!process.doProcess(context))
				break;
		}
		return context.getResult();
	}

	// 根据请假条id获取请假申请
	public LeaveInfoResult getLeaveByLeaveId(HttpSession session, String leaveId) {
		LeaveProcessContext context = new LeaveProcessContext();
		LeaveInfo info = new LeaveInfo();
		info.setId(leaveId);
		context.setInfo(info);
		context.setResult(new LeaveInfoResult());
		context.setSession(session);

		for (LeaveProcess process : getOneLeaveProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public List<LeaveProcess> getSaveLeaveProcesses() {
		return saveLeaveProcesses;
	}

	public void setSaveLeaveProcesses(List<LeaveProcess> saveLeaveProcesses) {
		this.saveLeaveProcesses = saveLeaveProcesses;
	}

	public List<LeaveProcess> getGetLeaveProcesses() {
		return getLeaveProcesses;
	}

	public void setGetLeaveProcesses(List<LeaveProcess> getLeaveProcesses) {
		this.getLeaveProcesses = getLeaveProcesses;
	}

	public List<LeaveProcess> getUpdateLeaveProcesses() {
		return updateLeaveProcesses;
	}

	public void setUpdateLeaveProcesses(List<LeaveProcess> updateLeaveProcesses) {
		this.updateLeaveProcesses = updateLeaveProcesses;
	}

	public List<LeaveProcess> getDeleteLeaveProcesses() {
		return deleteLeaveProcesses;
	}

	public void setDeleteLeaveProcesses(List<LeaveProcess> deleteLeaveProcesses) {
		this.deleteLeaveProcesses = deleteLeaveProcesses;
	}

	public List<LeaveProcess> getGetOneLeaveProcesses() {
		return getOneLeaveProcesses;
	}

	public void setGetOneLeaveProcesses(List<LeaveProcess> getOneLeaveProcesses) {
		this.getOneLeaveProcesses = getOneLeaveProcesses;
	}

}
