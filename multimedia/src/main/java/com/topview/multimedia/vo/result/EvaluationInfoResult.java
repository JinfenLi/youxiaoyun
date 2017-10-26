package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.EvaluationInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午9:12:00 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class EvaluationInfoResult {
	private int code;
	private boolean success;
	private List<EvaluationInfo> infoResult;
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
	public List<EvaluationInfo> getInfoResult() {
		return infoResult;
	}
	public void setInfoResult(List<EvaluationInfo> infoResult) {
		this.infoResult = infoResult;
	}
}
