package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月21日 下午9:13:52 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class EvaluationTemplateInfoResult {
	
	private int code;
	private boolean success;
	private List<EvaluationTemplateInfo> infoResult;
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
	public List<EvaluationTemplateInfo> getInfoResult() {
		return infoResult;
	}
	public void setInfoResult(List<EvaluationTemplateInfo> infoResult) {
		this.infoResult = infoResult;
	}
	

}
