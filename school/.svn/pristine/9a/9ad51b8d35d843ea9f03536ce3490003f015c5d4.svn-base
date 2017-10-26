package com.topview.school.dao.user;

import java.util.Date;
import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.UserLogin;
import com.topview.school.po.UserLoginInfo;

/**
 * 
* @ClassName: UserLoginMapper
* @Description: 用户登录记录DAO
* @author Catalyst
* @date 2016年3月23日
*
 */
public interface UserLoginMapper extends BaseDao<UserLogin> {
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