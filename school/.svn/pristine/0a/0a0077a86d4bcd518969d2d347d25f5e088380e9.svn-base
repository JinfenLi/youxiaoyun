package com.topview.school.controller.user.bean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.topview.school.service.base.Request;
import com.topview.school.vo.User.UserInfo;

/**   
 * @Title: LoginRequest.java 
 * @Package com.topview.school.controller.user.bean 
 * @Description:
 * @author Sherlock-lee   
 * @date 2015年4月21日 下午2:50:04 
 * @version V1.0   
 */
@Service
public class LoginRequest extends Request{
	/** 
	 * @ClassName: LoginRequest 
	 * @Description:
	 * @author Sherlock-lee
	 * @date 2015年4月21日 下午2:50:04 
	 */
	@NotNull
	private String account;//账号
	@NotNull
	private String password;//密码
	private HttpSession session;
	private Model model;
	private String student_id; //学生id
	
	private UserInfo userInfo = null;
	private String tag = null;
	private Map<String, Object> result = new HashMap<String, Object>();
	private String userType; //用户类别，用于切换用户身份时使用,0为教师账号，1为家长账号
	
	public LoginRequest(){
	}
	
	public LoginRequest(String account, String password, HttpSession session) {
		this.account = account;
		this.password = password;
		this.session = session;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
