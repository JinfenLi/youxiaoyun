package com.topview.multimedia.service.folder;

import com.topview.multimedia.vo.FolderInfo;
import com.topview.multimedia.vo.result.FolderInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午5:51:34 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class FolderProcessContext {
	
	private FolderInfo info;
	private FolderInfoResult result;
	public FolderInfo getInfo() {
		return info;
	}
	public void setInfo(FolderInfo info) {
		this.info = info;
	}
	public FolderInfoResult getResult() {
		return result;
	}
	public void setResult(FolderInfoResult result) {
		this.result = result;
	}
	
	

}
