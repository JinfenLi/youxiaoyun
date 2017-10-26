package com.topview.school.service.school.user;

import java.util.List;

import com.topview.school.controller.user.bean.LoginRequest;
import com.topview.school.service.base.BaseServiceImpl;
import com.topview.school.service.base.Process;

public class AppUserServiceImpl extends BaseServiceImpl implements
		AppUserService {

	private List<Process> userFindProcesses;

	/**
	 * 用户登陆
	 */
	public boolean login(LoginRequest loginRequest) {
		return excute(loginRequest, userFindProcesses);
	}

	public List<Process> getUserFindProcesses() {
		return userFindProcesses;
	}

	public void setUserFindProcesses(List<Process> userFindProcesses) {
		this.userFindProcesses = userFindProcesses;
	}

}
