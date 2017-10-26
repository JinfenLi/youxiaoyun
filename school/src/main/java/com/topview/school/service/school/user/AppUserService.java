package com.topview.school.service.school.user;

import com.topview.school.controller.user.bean.LoginRequest;

public interface AppUserService {

	/**
	 * 用户
	 * 
	 * @Title: login
	 * @Description: TODO
	 * @param @param loginRequest
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean login(LoginRequest loginRequest);

}
