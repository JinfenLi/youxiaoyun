package com.topview.multimedia.service.folder.file;

import com.topview.multimedia.vo.FileInfo;
import com.topview.multimedia.vo.result.FileInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午5:53:22 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class FileProcessContext {
	
	private FileInfo info;
	private FileInfoResult result;
	public FileInfo getInfo() {
		return info;
	}
	public void setInfo(FileInfo info) {
		this.info = info;
	}
	public FileInfoResult getResult() {
		return result;
	}
	public void setResult(FileInfoResult result) {
		this.result = result;
	}
	
	

}
