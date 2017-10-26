package com.topview.school.dao.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.UserLoginMapper;
import com.topview.school.po.UserLogin;
import com.topview.school.po.UserLoginInfo;

/**
 * 
* @ClassName: UserLoginMapperImpl
* @Description: 用户登录记录DAO实现类
* @author Catalyst
* @date 2016年3月23日
*
 */
@Repository
public class UserLoginMapperImpl extends BaseDaoImpl<UserLogin> implements UserLoginMapper {

	@Override
	public List<UserLoginInfo> getUserLoginInfo(String classId) {
		return template.selectList("selectUserLoginInfo",classId);
	}

	@Override
	public String getLoginCountForSchool(String schoolId) {
			return template.selectOne("getTotalCountOfSchoolUser",schoolId);
	}

	@Override
	public String getNotLoginCountForSchool(String schoolId) {
			return template.selectOne("getCountOfHaveNotLoginUser",schoolId);
	}

	@Override
	public String getLoginCountForSchoolTeacher(String schoolId) {
			return template.selectOne("getLoginCountForSchoolTeacher",schoolId);
	}

	@Override
	public String getNotLoginCountForSchoolTeacher(String schoolId) {
			return template.selectOne("getNotLoginCountForSchoolTeacher",schoolId);
	}

	@Override
	public String getLoginCountOfTeacher(String schoolId) {
		return template.selectOne("getLoginCountOfTeacher", schoolId);
	}

	@Override
	public String getLoginCountOfParent(String schoolId) {
		return template.selectOne("getLoginCountOfParent", schoolId);
	}

	@Override
	public String getLoginMan(String classId) {
		return template.selectOne("getLoginMan", classId);
	}

	@Override
	public String getNotLoginMan(String classId) {
		return template.selectOne("getNotLoginMan", classId);
	}

	@Override
	public String getAllMan(String classId) {
		return template.selectOne("getAllMan", classId);
	}

	@Override
	public Date getLoginStatusByUserId(String user_id) {
		return template.selectOne("getLoginStatusByUserId",user_id);
	}
}
