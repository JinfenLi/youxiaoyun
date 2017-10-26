package com.topview.school.vo.post.result;

import com.topview.school.vo.post.NewPostVo;

public class NewPostVoResult {
	private NewPostVo result;
	private int code;
	private boolean success;
	public NewPostVo getResult() {
		return result;
	}
	public void setResult(NewPostVo result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
}
