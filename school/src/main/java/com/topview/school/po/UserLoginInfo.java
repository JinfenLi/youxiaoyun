package com.topview.school.po;

import java.util.Date;

public class UserLoginInfo {
			private String IDCard;
			private String parentName;
			private String studentName;
			private String className;
			private Date lastLogin;
			public String getIDCard() {
				return IDCard;
			}
			public void setIDCard(String iDCard) {
				IDCard = iDCard;
			}
			public String getParentName() {
				return parentName;
			}
			public void setParentName(String parentName) {
				this.parentName = parentName;
			}
			public String getStudentName() {
				return studentName;
			}
			public void setStudentName(String studentName) {
				this.studentName = studentName;
			}
			public String getClassName() {
				return className;
			}
			public void setClassName(String className) {
				this.className = className;
			}
			public Date getLastLogin() {
				return lastLogin;
			}
			public void setLastLogin(Date lastLogin) {
				this.lastLogin = lastLogin;
			}
			
}
