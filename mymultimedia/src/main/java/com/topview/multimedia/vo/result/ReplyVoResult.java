package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.ReplyVo;

public class ReplyVoResult {

	private int code;
	private boolean success;
	private List<ReplyVo> result;

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

	public List<ReplyVo> getResult() {
		return result;
	}

	public void setResult(List<ReplyVo> result) {
		this.result = result;
	}

}
