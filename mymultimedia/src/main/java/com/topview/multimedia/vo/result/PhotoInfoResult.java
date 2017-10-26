package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.PhotoInfo;

public class PhotoInfoResult {
	private int code;
	private boolean success;
	private List<PhotoInfo> infoResult;
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
	public List<PhotoInfo> getInfoResult() {
		return infoResult;
	}
	public void setInfoResult(List<PhotoInfo> infoResult) {
		this.infoResult = infoResult;
	}
	
}
