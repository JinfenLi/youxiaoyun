package com.topview.school.vo.school.result;

import java.util.List;

import com.topview.school.vo.school.ClazzInfo;

public class ClazzInfoResult {
	private int code;
	private boolean success;
	private List<ClazzInfo> result;
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
	public List<ClazzInfo> getResult() {
		return result;
	}
	public void setResult(List<ClazzInfo> result) {
		this.result = result;
	}
	
}
