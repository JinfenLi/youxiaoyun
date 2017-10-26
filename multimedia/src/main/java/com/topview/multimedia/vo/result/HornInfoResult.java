package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.HornInfo;

public class HornInfoResult {
	private int code;
	private boolean success;
	private List<HornInfo> result;

	public int getCode() {
		return code;
	}

	public void setCode(int b) {
		this.code = b;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<HornInfo> getResult() {
		return result;
	}
	
	public void setResult(List<HornInfo> result) {
		this.result = result;
	}
	
}
