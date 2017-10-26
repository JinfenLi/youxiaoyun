package com.topview.school.po;

import java.util.Date;

/**
 * 
* @ClassName: UserLogin
* @Description: 用户登录记录PO
* @author Catalyst
* @date 2016年3月23日
*
 */
public class UserLogin {
	//用户id
	private String userId;
	
	//用户类型（"0"为教师，"1"为家长）
	private String userType;
	
	//最后登录时间
	private Date lastlogin;

	public UserLogin() {
		super();
	}

	public UserLogin(String userId, String userType, Date lastlogin) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.lastlogin = lastlogin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
}