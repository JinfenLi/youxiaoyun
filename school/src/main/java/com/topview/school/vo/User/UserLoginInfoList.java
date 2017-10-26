package com.topview.school.vo.User;
/*
 * 给小红记录登陆未登录的类，暑假可以删除
 */
public class UserLoginInfoList {
	private String clazz;
	private Integer loginNumber = 0;
	private Integer notLoginNumber = 0;
	public String getClazz() {
		return clazz;
	}
	
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	public Integer getLoginNumber() {
		return loginNumber;
	}
	
	public void setLoginNumber(Integer loginNumber) {
		this.loginNumber = loginNumber;
	}
	
	public Integer getNotLoginNumber() {
		return notLoginNumber;
	}
	
	public void setNotLoginNumber(Integer notLoginNumber) {
		this.notLoginNumber = notLoginNumber;
	}
	
}
