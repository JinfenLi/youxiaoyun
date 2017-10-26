package com.topview.school.dao.system.authc.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.SinglePrimaryKeyBaseDAOImpl;
import com.topview.school.dao.system.authc.RoleModuleMapper;
import com.topview.school.po.RoleModuleKey;

/**
 * 
 * @author Sherlock-lee
 * @date 2015年9月29日 下午8:12:23
 * @see TODO
 * @since 1.0
 */
@Repository
public class RoleModuleMapperImpl extends
		SinglePrimaryKeyBaseDAOImpl<RoleModuleKey> implements RoleModuleMapper {

	@Override
	public int deleteByRoleId(String roleId) {

		return template.delete(getNamespace("deleteByRoleId"), roleId);
	}

}
