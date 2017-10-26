package com.topview.school.vo.User.enums;

/**   
 * @Title: UserType.java 
 * @Package com.topview.school.vo.User.enums 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年4月21日 下午2:25:50 
 * @version V1.0   
 */
public enum UserType {

	Illegal_Account(-1, "非法账号"), Teacher(0, "教师账号"), Parent(1, "家长账号");
	
	private int code;
	private String description;
	
	private UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	* @Title: valueOf 
	* @Description: TODO
	* @param @param i
	* @param @return   
	* @return Enum<UserType>   
	* @throws
	 */
	public static Enum<UserType> valueOf(int i) {

		switch (i) {
		case -1:
			return Illegal_Account;
		case 0:
			return Teacher;
		case 1:
			return Parent;
		}
		return null;
	}
	
}
