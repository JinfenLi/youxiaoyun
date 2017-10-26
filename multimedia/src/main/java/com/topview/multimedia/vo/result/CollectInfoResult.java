/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 下午3:21:28 
 * @version V1.0
 */
package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.CollectInfo;

/** 
 * @ClassName: CollectInfoResult 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 下午3:21:28 
 *  
 */
public class CollectInfoResult {

	private int code;
	private boolean success;
	private List<CollectInfo> inforesult;
	private String collectid;
	
	
	public String getCollectid() {
		return collectid;
	}
	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}
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
	public List<CollectInfo> getInforesult() {
		return inforesult;
	}
	public void setInforesult(List<CollectInfo> inforesult) {
		this.inforesult = inforesult;
	}

	
}
