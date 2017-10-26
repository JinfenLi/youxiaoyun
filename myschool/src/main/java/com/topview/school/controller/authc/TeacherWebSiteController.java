package com.topview.school.controller.authc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.topview.school.controller.user.bean.LoginRequest;
import com.topview.school.po.School;
import com.topview.school.service.school.SchoolService;
import com.topview.school.service.user.TeacherWebSiteService;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.User.UserInfo;

@Controller
@RequestMapping(value = "/teacher_website", produces = "text/html;charset=UTF-8")
@SessionAttributes("currentUser")
public class TeacherWebSiteController {

	@Autowired
	private TeacherWebSiteService service;
	@Autowired
	private SchoolService schoolService;


	/**
	 * 
	 * @Title: teacherWebSiteLogin
	 * @Description: 提供给web端的登陆入口， 为了防止家长登陆管理系统的发生
	 * @param @param loginRequest
	 * @param @param model
	 * @param @param session
	 * @param @param student_id
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value = "/login")
	public @ResponseBody Object teacherWebSiteLogin(
			@Valid LoginRequest loginRequest, Model model, HttpSession session) {

		UserInfo userInfo = service.login(loginRequest);
		if (userInfo != null) {
			model.addAttribute("currentUser", userInfo);
			School s = schoolService.selectByPrimaryKey(userInfo.getSchool_id());
			loginRequest.getResult().put("schoolName", s.getName());
			loginRequest.getResult().put("logo", s.getLogo());
		}
		String[] filter = { "createTime", "lastupdate", "password", "kids",
				"tag", "grade_id", "info", "sortName", "comment",
				"teaching_classes" };
		return JSONUtil.toObject(loginRequest.getResult(), filter).toString();
	}


}
