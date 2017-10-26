package com.topview.school.vo.User;

import com.topview.school.po.UserLoginInfo;
import com.topview.school.util.DateFormatUtil;

public class UserLoginInfoVo {
			private String IDCard;
			private String parentName;
			private String className;
			private String studentName;
			private String lastLogin;
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
			public String getClassName() {
				return className;
			}
			public void setClassName(String className) {
				this.className = className;
			}
			public String getStudentName() {
				return studentName;
			}
			public void setStudentName(String studentName) {
				this.studentName = studentName;
			}

			
			public String getLastLogin() {
				return lastLogin;
			}
			public void setLastLogin(String lastLogin) {
				this.lastLogin = lastLogin;
			}
			public UserLoginInfoVo changeToVo(UserLoginInfo loginInfo){
				UserLoginInfoVo info =new UserLoginInfoVo();
				info.setIDCard(loginInfo.getIDCard());
				info.setClassName(loginInfo.getClassName());
				info.setParentName(loginInfo.getParentName());
				info.setStudentName(loginInfo.getStudentName());
				if(loginInfo.getLastLogin()!=null){
					info.setLastLogin(DateFormatUtil.formatDateToSeconds(loginInfo.getLastLogin()));
				}
				else{
					info.setLastLogin("暂未登录");
				}
				return info;
			}
			
}
