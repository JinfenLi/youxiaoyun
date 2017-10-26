package com.topview.school.po;

import java.util.Date;

public class Student {

	private String id;
	private String tScClassId; // 所属班级id
	private String name; //学生姓名
	private String sex; //学生性别
	private String idcard; // 学号
	private String address; // 地址
	private String phone; // 家庭电话
	private Date createTime; // 创建时间
	private String password; // 密码
	private String picurl; // 头像地址
	private Date lastupdate; // 头像最后更新时间
	private String emergencyPhone; //紧急电话
	private Date birthday; //出生年月日

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}