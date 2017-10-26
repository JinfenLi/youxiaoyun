package com.topview.school.service.user.teacher;

import com.topview.school.vo.User.TeacherVo;
import com.topview.school.vo.User.result.TeacherInfoResult;

public class TeacherProcessContext {
	private TeacherVo info;
	private TeacherInfoResult result;
	public TeacherVo getInfo() {
		return info;
	}

	public void setInfo(TeacherVo info) {
		this.info = info;
	}

	public TeacherInfoResult getResult() {
		return result;
	}

	public void setResult(TeacherInfoResult result) {
		this.result = result;
	}
	
}
