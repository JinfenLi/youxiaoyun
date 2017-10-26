package com.topview.school.vo.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.topview.school.po.Teacher;

public class TeacherVo {

	private String id;
	// private String tScDepartmentsId;
	private String tScClassId;
	private String tScGradeId;
	private String tScSchoolId;
	@Size(max=25)
	private String name;
	private String sex;
	private String email;
	@Size(max=16)
	private String phone;
	private String createTime;
	@Size(max=32)
	private String password;
	private String picUrl;
	private String subject; // 所教的学科
	private String education; // 学历
	@Size(max=18)
	private String idcard; // 工号
	private String birthday; // 生日
	private String isAuthc;
	private String course; 
	private String depart;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// public String gettScDepartmentsId() {
	// return tScDepartmentsId;
	// }
	// public void settScDepartmentsId(String tScDepartmentsId) {
	// this.tScDepartmentsId = tScDepartmentsId;
	// }
	public String gettScClassId() {
		return tScClassId;
	}

	public void settScClassId(String tScClassId) {
		this.tScClassId = tScClassId;
	}

	public String gettScGradeId() {
		return tScGradeId;
	}

	public void settScGradeId(String tScGradeId) {
		this.tScGradeId = tScGradeId;
	}

	public String gettScSchoolId() {
		return tScSchoolId;
	}

	public void settScSchoolId(String tScSchoolId) {
		this.tScSchoolId = tScSchoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public static TeacherVo changeToVo(Teacher teacher) {
		TeacherVo info = new TeacherVo();
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
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (teacher.getBirthday() != null) {
			info.setBirthday(df.format(teacher.getBirthday()));
		}
		info.setCreateTime(df.format(teacher.getCreateTime()));
		info.setPicUrl(teacher.getPicUrl());
		info.setEducation(teacher.getEducation());
		info.setIdcard(teacher.getIdcard());
		return info;
	}

	public static Teacher changeToPo(TeacherVo info) {
		Teacher t = new Teacher();
		t.setEmail(info.getEmail());
		t.setId(info.getId());
		t.setName(info.getName());
		t.setPassword(info.getPassword());
		t.setPhone(info.getPhone());
		t.setSex(info.getSex());
		t.settScClassId(info.gettScClassId());
		// info.settScDepartmentsId(teacher.gettScDepartmentsId());
		t.settScGradeId(info.gettScGradeId());
		t.settScSchoolId(info.gettScSchoolId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(info.getCreateTime()!=null){
				t.setCreateTime(df.parse(info.getCreateTime()));
			}
			if(info.getBirthday() != null && !info.getBirthday().equals("")){
				t.setBirthday(df.parse(info.getBirthday()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		t.setPicUrl(info.getPicUrl());
		t.setEducation(info.getEducation());
		t.setIdcard(info.getIdcard());
		return t;
	}

	public static List<Teacher> changeToPo(List<TeacherVo> infos) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		for (int i = 0; i < infos.size(); i++) {
			teachers.add(changeToPo(infos.get(i)));
		}
		return teachers;
	}

	public static List<TeacherVo> changeToVo(List<Teacher> list) {
		List<TeacherVo> infos = new ArrayList<TeacherVo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIsAuthc() {
		return isAuthc;
	}

	public void setIsAuthc(String isAuthc) {
		this.isAuthc = isAuthc;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	
	
}
