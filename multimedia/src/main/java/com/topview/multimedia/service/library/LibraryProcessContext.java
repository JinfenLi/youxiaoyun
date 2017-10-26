package com.topview.multimedia.service.library;

import com.topview.multimedia.vo.LibraryInfo;
import com.topview.multimedia.vo.result.LibraryInfoResult;

public class LibraryProcessContext {
	private LibraryInfo info;
	private LibraryInfoResult result;
	public LibraryInfo getInfo() {
		return info;
	}
	public void setInfo(LibraryInfo info) {
		this.info = info;
	}
	public LibraryInfoResult getResult() {
		return result;
	}
	public void setResult(LibraryInfoResult result) {
		this.result = result;
	}
	
}
