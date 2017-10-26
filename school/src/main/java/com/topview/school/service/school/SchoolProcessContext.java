package com.topview.school.service.school;

import com.topview.school.vo.school.SchoolInfo;
import com.topview.school.vo.school.result.SchoolInfoResult;

public class SchoolProcessContext {

	private SchoolInfo info;
	private SchoolInfoResult result;
	public SchoolInfo getInfo() {
		return info;
	}
	public void setInfo(SchoolInfo info) {
		this.info = info;
	}
	public SchoolInfoResult getResult() {
		return result;
	}
	public void setResult(SchoolInfoResult result) {
		this.result = result;
	}
	
}
