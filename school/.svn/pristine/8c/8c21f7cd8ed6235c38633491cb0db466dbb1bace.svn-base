package com.topview.school.service.user;

import java.util.Date;
import java.util.List;

import com.topview.school.po.UserLoginInfo;
import com.topview.school.vo.User.UserInfo;


/**   
 * @Title: UserService.java 
 * @Package com.topview.school.service.user 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年5月31日 下午7:31:54 
 * @version V1.0   
 */
public interface UserService {
			public boolean updateUserPassword(UserInfo user);
			
			public List<UserLoginInfo> getUserLoginInfo(String classId);
			
			public String getLoginCountForSchool(String schoolId);
			
			public String getNotLoginCountForSchool(String schoolId);
			
			public String getLoginCountForSchoolTeacher(String schoolId);
			
			public String getNotLoginCountForSchoolTeacher(String schoolId); 
			
			public String getLoginCountOfTeacher(String schoolId);
			
			public String getLoginCountOfParent(String schoolId);
			
			public String getLoginMan(String classId);
			
			public String getNotLoginMan(String classId);
			
			public String getAllMan(String classId);
			
			public Date getLoginStatusByUserId(String user_id);
}
