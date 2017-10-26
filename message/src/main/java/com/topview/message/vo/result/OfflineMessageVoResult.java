package com.topview.message.vo.result;

import java.util.List;

import com.topview.message.vo.HistoryMessageVo;
import com.topview.message.vo.OfflineMessageVo;


public class OfflineMessageVoResult {

	private List<OfflineMessageVo> result;
	private List<HistoryMessageVo> historyMessageVos;
	private boolean success;
	private int code;

	public List<OfflineMessageVo> getResult() {
		return result;
	}

	public void setResult(List<OfflineMessageVo> result) {
		this.result = result;
	}

	public List<HistoryMessageVo> getHistoryMessageVos() {
		return historyMessageVos;
	}

	public void setHistoryMessageVos(List<HistoryMessageVo> historyMessageVos) {
		this.historyMessageVos = historyMessageVos;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
