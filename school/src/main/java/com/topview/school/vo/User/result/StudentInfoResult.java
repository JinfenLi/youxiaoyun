package com.topview.school.vo.User.result;

import java.util.List;

import com.topview.school.vo.User.StudentVo;

public class StudentInfoResult {
	private int code;
	private boolean success;
	private List<StudentVo> result;
	private String path;
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

	public List<StudentVo> getResult() {
		return result;
	}

	public void setResult(List<StudentVo> result) {
		this.result = result;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
