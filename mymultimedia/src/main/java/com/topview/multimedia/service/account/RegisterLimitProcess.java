package com.topview.multimedia.service.account;

import org.springframework.stereotype.Service;

/**
 * 注册限制
 * 项目名称:com.topview.multimedia.service.user.register<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Mar 24, 2015<br>
 */
@Service
public class RegisterLimitProcess implements AccountProcess {

	public boolean doProcess(AccountProcessContext context) {
		String comment = context.getInfo().getComment();
		if (comment == null) {
			context.getResult().setSuccess(false);
			//TODO
			return false;
		}
		return true;
	}

}
