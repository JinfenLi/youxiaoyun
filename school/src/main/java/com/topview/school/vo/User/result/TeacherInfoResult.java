package com.topview.school.vo.User.result;

import java.util.List;

import com.topview.school.vo.User.TeacherVo;

public class TeacherInfoResult {
	private int code;
	private boolean success;
	private List<TeacherVo> result;
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
	public List<TeacherVo> getResult() {
		return result;
	}
	public void setResult(List<TeacherVo> result) {
		this.result = result;
	}
	
}
