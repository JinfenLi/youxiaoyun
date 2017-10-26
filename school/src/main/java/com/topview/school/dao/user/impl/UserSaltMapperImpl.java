package com.topview.school.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.UserSaltMapper;
import com.topview.school.po.UserSalt;


@Repository
public class UserSaltMapperImpl extends BaseDaoImpl<UserSalt> implements UserSaltMapper{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return template.delete(getStatement("deleteByPrimaryKey"),id);
	}

	@Override
	public UserSalt selectByPrimaryKey(Integer id) {
		return template.selectOne(getStatement("selectByPrimaryKey"),id);
	}
	
	public UserSalt getSaltByUserId(String userId){
		return template.selectOne(getStatement("selectSaltByUserId"),userId);
	}
	
	
	
	
}
