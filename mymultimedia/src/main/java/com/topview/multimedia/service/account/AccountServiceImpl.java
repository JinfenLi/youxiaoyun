package com.topview.multimedia.service.account;

import java.util.List;

import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.result.AccountInfoResult;

public class AccountServiceImpl implements AccountService {

	private List<AccountProcess> registerProcesses;
	private List<AccountProcess> updateProcesses;

	public AccountInfoResult register(AccountInfo info) {
		return doProcess(info, registerProcesses);
	}

	public AccountInfoResult update(AccountInfo info) {
		return doProcess(info, updateProcesses);
	}

	public List<AccountProcess> getUpdateProcesses() {
		return updateProcesses;
	}

	public void setUpdateProcesses(List<AccountProcess> updateProcesses) {
		this.updateProcesses = updateProcesses;
	}

	public List<AccountProcess> getRegisterProcesses() {
		return registerProcesses;
	}

	public void setRegisterProcesses(List<AccountProcess> registerProcesses) {
		this.registerProcesses = registerProcesses;
	}

	public AccountInfoResult doProcess(AccountInfo info,
			List<AccountProcess> processes) {
		AccountProcessContext context = new AccountProcessContext();
		AccountInfoResult result = new AccountInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (AccountProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

}
