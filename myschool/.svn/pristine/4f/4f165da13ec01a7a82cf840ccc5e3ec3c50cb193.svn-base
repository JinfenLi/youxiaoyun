package com.topview.school.service.school.user;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.controller.user.bean.LoginRequest;
import com.topview.school.service.base.Context;
import com.topview.school.service.base.Process;
import com.topview.school.vo.User.UserInfo;

/**
 * @Title: LoginProcess.java
 * @Package com.topview.school.service.school.user
 * @Description: TODO
 * @author Sherlock-lee
 * @date 2015年4月21日 下午3:15:34
 * @version V1.0
 */
@Service
public class LoginProcess extends Process {

	@Resource
	private AppUserServiceImpl appUserServiceImpl;

	public boolean doProcess(Context context) {

		LoginRequest loginRequest = (LoginRequest) context.getRequest();
		Map<String, Object> result = loginRequest.getResult();
		try {
			UserInfo userInfo = loginRequest.getUserInfo();
			loginRequest.getModel().addAttribute("currentUser", userInfo);

			result.put("success", true);
			result.put("userInfo", userInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return true;
	}

}
