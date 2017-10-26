package com.topview.multimedia.service.account;

import org.springframework.stereotype.Service;

import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.result.AccountInfoResult;

@Service
public interface AccountService {
	public AccountInfoResult register(AccountInfo info);
	public AccountInfoResult update(AccountInfo info);
}
