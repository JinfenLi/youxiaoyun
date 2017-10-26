package com.topview.school.vo.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.Student;

public class StudentVo {
	private String id;
	private String tScClassId;
	private String name;
	private String sex;
	private String idcard;
	private String address;
	private String phone;
	private String createTime;
	private String password;
	private String picurl;
	private String birthday; // 出生年月日
	private String emergencyPhone; // 紧急电话

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettScClassId() {
		return tScClassId;
	}

	public void settScClassId(String tScClassId) {
		this.tScClassId = tScClassId;
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public static Student changeToPo(StudentVo info) {
		Student student = new Student();
		student.setAddress(info.getAddress());
		student.setIdcard(info.getIdcard());
		student.setName(info.getName());
		student.setPassword(info.getPassword());
		student.setPhone(info.getPhone());
		student.settScClassId(info.gettScClassId());
		student.setPicurl(info.getPicurl());
		student.setEmergencyPhone(info.getEmergencyPhone());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			student.setBirthday(df.parse(info.getBirthday()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return student;
	}

	public static List<Student> changeToPo(List<StudentVo> infos) {
		List<Student> list = new ArrayList<Student>();
		if (infos.size() > 0) {
			int size = infos.size();
			for (int i = 0; i < size; i++) {
				list.add(changeToPo(infos.get(i)));
			}
		}
		return list;
	}

	public static StudentVo changeToVo(Student student) {
		StudentVo info = new StudentVo();
		info.setAddress(student.getAddress());
		info.setIdcard(student.getIdcard());
		info.setSex(student.getSex());
		info.setName(student.getName());
		info.setPassword(student.getPassword());
		info.setPhone(student.getPhone());
		info.settScClassId(student.gettScClassId());
		info.setId(student.getId());
		info.setPicurl(student.getPicurl());
		info.setEmergencyPhone(student.getEmergencyPhone());
		if (student.getBirthday() != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			info.setBirthday(df.format(student.getBirthday()));
		}
		return info;
	}

	public static List<StudentVo> changeToVo(List<Student> students) {
		List<StudentVo> list = new ArrayList<StudentVo>();
		if (students.size() > 0) {
			int size = students.size();
			for (int i = 0; i < size; i++) {
				list.add(changeToVo(students.get(i)));
			}
		}
		return list;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

}
