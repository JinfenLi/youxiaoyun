package com.topview.school.vo.school.result;

import java.util.List;

import com.topview.school.vo.school.SchoolInfo;

public class SchoolInfoResult {
	private int code;
	private boolean success;
	private List<SchoolInfo> result;
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
	public List<SchoolInfo> getResult() {
		return result;
	}
	public void setResult(List<SchoolInfo> result) {
		this.result = result;
	}
	
}
