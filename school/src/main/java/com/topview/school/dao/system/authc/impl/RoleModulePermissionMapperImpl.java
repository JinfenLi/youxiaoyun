package com.topview.school.dao.system.authc.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.SinglePrimaryKeyBaseDAOImpl;
import com.topview.school.dao.system.authc.RoleModulePermissionMapper;
import com.topview.school.po.RoleModulePermissionKey;

/**
 * 
 * @author Sherlock-lee
 * @date 2015年9月29日 下午8:12:23
 * @see TODO
 * @since 1.0
 */
@Repository
public class RoleModulePermissionMapperImpl extends
		SinglePrimaryKeyBaseDAOImpl<RoleModulePermissionKey> implements
		RoleModulePermissionMapper {

	@Override
	public int deleteByRoleModuleId(String roleModuleId) {
		
		return template.delete(getNamespace("deleteByRoleModuleId"), roleModuleId);
	}

}
