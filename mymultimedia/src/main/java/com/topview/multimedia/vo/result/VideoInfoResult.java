package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.VideoInfo;

public class VideoInfoResult {
	private int code;
	private boolean success;
	private List<VideoInfo> result;

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

	public List<VideoInfo> getResult() {
		return result;
	}

	public void setResult(List<VideoInfo> result) {
		this.result = result;
	}

}
