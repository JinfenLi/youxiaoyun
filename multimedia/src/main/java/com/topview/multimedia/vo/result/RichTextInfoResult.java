package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.RichTextInfo;

public class RichTextInfoResult {
	private int code;
	private boolean success;
	private List<RichTextInfo> infoResult;
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
	public List<RichTextInfo> getInfoResult() {
		return infoResult;
	}
	public void setInfoResult(List<RichTextInfo> infoResult) {
		this.infoResult = infoResult;
	}
	
}
