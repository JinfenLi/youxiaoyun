package com.topview.school.vo.User;

import java.util.ArrayList;
import java.util.List;

public class TeacherLoginVo extends TeacherVo{
	
    private String loginStatus;

	public String getLoginStatus() {
		return loginStatus;
	}
	
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	
	public static TeacherLoginVo chengeToVO(TeacherVo teacher) {
		TeacherLoginVo info = new TeacherLoginVo();
		info.setEmail(teacher.getEmail());
		info.setId(teacher.getId());
		info.setName(teacher.getName());
		info.setPassword(teacher.getPassword());
		info.setPhone(teacher.getPhone());
		info.setSex(teacher.getSex());
		info.settScClassId(teacher.gettScClassId());
		// info.settScDepartmentsId(teacher.gettScDepartmentsId());
		info.settScGradeId(teacher.gettScGradeId());
		info.settScSchoolId(teacher.gettScSchoolId());
		info.setBirthday(teacher.getBirthday());
		info.setCreateTime(teacher.getCreateTime());
		info.setPicUrl(teacher.getPicUrl());
		info.setEducation(teacher.getEducation());
		info.setIdcard(teacher.getIdcard());
		return info;
	}
	
	public static List<TeacherLoginVo> changeToVO(List<TeacherVo> list) {
		List<TeacherLoginVo> infos = new ArrayList<TeacherLoginVo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(chengeToVO(list.get(i)));
			}
		}
		return infos;
	}
}
