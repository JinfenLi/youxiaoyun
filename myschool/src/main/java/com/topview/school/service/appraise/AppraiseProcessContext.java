/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:19:06 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import com.topview.school.vo.appraise.AppraiseInfo;
import com.topview.school.vo.appraise.result.AppraiseInfoResult;

/** 
 * @ClassName: AppraiseProcessContext 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:19:06 
 *  
 */
public class AppraiseProcessContext {

	private AppraiseInfo info;
	private AppraiseInfoResult result;
	
	
	public AppraiseInfo getInfo() {
		return info;
	}
	public void setInfo(AppraiseInfo info) {
		this.info = info;
	}
	public AppraiseInfoResult getResult() {
		return result;
	}
	public void setResult(AppraiseInfoResult result) {
		this.result = result;
	}
}
