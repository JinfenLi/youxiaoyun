package com.topview.school.vo.curricula.result;

import java.util.List;

import com.topview.school.vo.curricula.CurriculaVariableInfo;

public class CurriculaVariableInfoResult {
	private int code;
	private boolean success;
	private List<CurriculaVariableInfo> result;
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
	public List<CurriculaVariableInfo> getResult() {
		return result;
	}
	public void setResult(List<CurriculaVariableInfo> result) {
		this.result = result;
	}
	
}
