package com.topview.school.service.user;

import javax.annotation.Resource;

import com.topview.school.po.Parent;
import com.topview.school.po.Teacher;
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
	
}
