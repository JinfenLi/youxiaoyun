package com.topview.multimedia.service.account;

import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.result.AccountInfoResult;

/**
 * 
 * 项目名称:com.topview.multimedia.service.user.register<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Mar 24, 2015<br>
 */
public class AccountProcessContext {
	private AccountInfo info;
	private AccountInfoResult result;

	public AccountInfo getInfo() {
		return info;
	}

	public void setInfo(AccountInfo info) {
		this.info = info;
	}

	public AccountInfoResult getResult() {
		return result;
	}

	public void setResult(AccountInfoResult result) {
		this.result = result;
	}

	
}
