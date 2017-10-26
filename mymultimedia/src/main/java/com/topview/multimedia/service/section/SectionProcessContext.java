package com.topview.multimedia.service.section;

import com.topview.multimedia.vo.SectionInfo;
import com.topview.multimedia.vo.result.SectionInfoResult;

public class SectionProcessContext {
	private SectionInfo info;
	private SectionInfoResult result;
	public SectionInfo getInfo() {
		return info;
	}
	public void setInfo(SectionInfo info) {
		this.info = info;
	}
	public SectionInfoResult getResult() {
		return result;
	}
	public void setResult(SectionInfoResult result) {
		this.result = result;
	}
	
}
