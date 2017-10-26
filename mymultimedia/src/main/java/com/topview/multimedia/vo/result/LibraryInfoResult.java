package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.LibraryInfo;

public class LibraryInfoResult {
	private int code;
	private boolean success;
	private List<LibraryInfo> result;

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

	public List<LibraryInfo> getResult() {
		return result;
	}

	public void setResult(List<LibraryInfo> result) {
		this.result = result;
	}


}
