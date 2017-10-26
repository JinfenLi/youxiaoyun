package com.topview.school.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.topview.school.dao.user.UserLoginMapper;
import com.topview.school.po.Parent;
import com.topview.school.po.Teacher;
import com.topview.school.po.UserLoginInfo;
import com.topview.school.service.user.parent.ParentService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.vo.User.UserInfo;

/**   
 * @Title: UserServiceImpl.java 
 * @Package com.topview.school.service.user 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年5月31日 下午7:31:31 
 * @version V1.0   
 */
public class UserServiceImpl implements UserService{
	/** 
	 * @ClassName: UserServiceImpl 
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年5月31日 下午7:31:31 
	 */
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private UserLoginMapper userLoginMapper;
	
	@Override
	public boolean updateUserPassword(UserInfo user) {
		if("".equals(user.getAccount())||"".equals(user.getPassword())){
			return false;
		}
		String mobile=user.getAccount();
		String password=user.getPassword();
		System.out.println(mobile+password);
		if(null==this.teacherService.findByPhone(mobile)){
				if(null==this.parentService.findByMoble(mobile)){
					return false;
				}
				Parent parent=this.parentService.findByMoble(mobile);
				parent.setPassword(password);
				return this.parentService.updatePassword(parent);
		}
		else{
			Teacher teacher=this.teacherService.findByPhone(mobile);
			teacher.setPassword(password);
			return this.teacherService.updatePassword(teacher);
		}
	}

	@Override
	public List<UserLoginInfo> getUserLoginInfo(String classId) {
			return this.userLoginMapper.getUserLoginInfo(classId);
	}

	@Override
	public String getLoginCountForSchool(String schoolId) {
		return this.userLoginMapper.getLoginCountForSchool(schoolId);
	}

	@Override
	public String getNotLoginCountForSchool(String schoolId) {
		return this.userLoginMapper.getNotLoginCountForSchool(schoolId);
	}

	@Override
	public String getLoginCountForSchoolTeacher(String schoolId) {
		return this.userLoginMapper.getLoginCountForSchoolTeacher(schoolId);
	}

	@Override
	public String getNotLoginCountForSchoolTeacher(String schoolId) {
		return this.userLoginMapper.getNotLoginCountForSchoolTeacher(schoolId);
	}

	@Override
	public String getLoginCountOfTeacher(String schoolId) {
		return this.userLoginMapper.getLoginCountOfTeacher(schoolId);
	}

	@Override
	public String getLoginCountOfParent(String schoolId) {
		return this.userLoginMapper.getLoginCountOfParent(schoolId);
	}

	@Override
	public String getLoginMan(String classId) {
		return this.userLoginMapper.getLoginMan(classId);
	}

	@Override
	public String getNotLoginMan(String classId) {
		return this.userLoginMapper.getNotLoginMan(classId);
	}

	@Override
	public String getAllMan(String classId) {
		return this.userLoginMapper.getAllMan(classId);
	}

	@Override
	public Date getLoginStatusByUserId(String user_id) {
		return this.userLoginMapper.getLoginStatusByUserId(user_id);
	}	
	
}
