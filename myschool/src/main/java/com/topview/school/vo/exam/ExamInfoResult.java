package com.topview.school.vo.exam;

import java.util.List;

public class ExamInfoResult {

	private int code;
	private boolean success;
	private List<ExamInfo> result;
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
	public List<ExamInfo> getResult() {
		return result;
	}
	public void setResult(List<ExamInfo> result) {
		this.result = result;
	}
	
}
