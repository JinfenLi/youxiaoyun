package com.topview.school.service.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.controller.user.bean.LoginRequest;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Clazz;
import com.topview.school.po.Teacher;
import com.topview.school.util.SortChineseName;
import com.topview.school.vo.User.TeacherInfo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.User.enums.UserType;
import com.topview.school.vo.school.SemesterVo;

/**
 * @Title: UserServiceImpl.java
 * @Package com.topview.school.service.user
 * @Description: TODO
 * @author Sherlock-lee
 * @date 2015年5月31日 下午7:31:31
 * @version V1.0
 */
@Service
public class TeacherWebSiteServiceImpl implements TeacherWebSiteService {
	/**
	 * @ClassName: UserServiceImpl
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年5月31日 下午7:31:31
	 */
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private SemesterMapper semesterMapper;
	@Autowired
	private org.apache.shiro.mgt.SecurityManager manager;

	@Override
	public UserInfo login(LoginRequest loginRequest) {

		UserInfo userInfo = new UserInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		String phone = loginRequest.getAccount();
		String password = loginRequest.getPassword();

		params.put("phone", phone);
		params.put("password", password);
		param.put("params", params);
		Teacher teacher = teacherMapper.findByNameAndPassword(param);
		if (teacher != null) {
			userInfo.setAccount(phone);
			userInfo.setPassword(password);
			userInfo.setPicUrl(teacher.getPicUrl());

			userInfo.setUser_id(teacher.getId());
			userInfo.setUser_name(teacher.getName());

			TeacherInfo teacherInfo = new TeacherInfo();
			if (teacher.gettScClassId() != null) {
				teacherInfo.setHeadTeacher(true);
				teacherInfo.setLeading_class(teacher.gettScClassId());
			} else {
				teacherInfo.setHeadTeacher(false);
			}
			// 求老师教的班级 
			SemesterVo vo = semesterMapper.getCurrentSemester(teacher.gettScSchoolId());
			List<Clazz> classes = clazzMapper.selectTeacherClazzs(teacher.getId(), vo.getId());
			//剔除属性值重复的对象
			for(int i = 0; i < classes.size() - 1; i++) {
				for(int j = classes.size() - 1; j > i ; j--) {
					if(classes.get(j).getId().equals(classes.get(i).getId())) {
						classes.remove(j);
					}
				}
			}
			Collections.sort(classes, new SortChineseName<Clazz>("getName"));
			teacherInfo.setClasses(classes);
			if (classes.size() > 0) {
				userInfo.setClass_id(classes.get(0).getId());
				userInfo.setClass_name(classes.get(0).getName());
			}
			
			userInfo.setSchool_id(teacher.gettScSchoolId());

			userInfo.setUser_type(UserType.Teacher);// ordinal为1的时候代表登陆用户是老师
			userInfo.setTeacher_info(teacherInfo);
			loginRequest.setUserInfo(userInfo);
			
			Map<String, Object> result = loginRequest.getResult();
			result.put("success", true);
			result.put("userInfo", userInfo);
			System.out.println(userInfo.getUser_id() + userInfo.getUser_name()
					+ "登陆成功");

			// 注册shiro session
			SecurityUtils.setSecurityManager(manager);
			org.apache.shiro.subject.Subject currentUser = SecurityUtils
					.getSubject();

			UsernamePasswordToken token = new UsernamePasswordToken(
					loginRequest.getAccount(), loginRequest.getPassword());
			token.setRememberMe(true);
			currentUser.login(token);

			return userInfo;
		} else {
			loginRequest.getResult().put("success", false);
			return null;
		}
	}

}
