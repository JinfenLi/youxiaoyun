package com.topview.school.dao.system.authc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.SinglePrimaryKeyBaseDAOImpl;
import com.topview.school.dao.system.authc.PermissionMapper;
import com.topview.school.po.Permission;

/**
 * 
 * @author Sherlock-lee
 * @date 2015年9月29日 下午8:12:05
 * @see TODO
 * @since 1.0
 */
@Repository
public class PermissionMapperImpl extends
		SinglePrimaryKeyBaseDAOImpl<Permission> implements PermissionMapper {

	@Override
	public List<Permission> selectByRoleModuleId(Map<String, Object> warp) {

		return template.selectList(getNamespace("selectByRoleModuleId"), warp);
	}

	@Override
	public List<Permission> selectUnarrangePermissionByRoleModuleId(
			Map<String, Object> map) {

		return template.selectList(
				getNamespace("selectUnarrangePermissionByRoleModuleId"), map);
	}

	@Override
	public List<Permission> selectUserModulePermission(Map<String, Object> map) {

		return template.selectList(getNamespace("selectUserModulePermission"),
				map);
	}

	@Override
	public List<Permission> selectUserModulePermissionWithoutClassId(
			Map<String, Object> map) {

		return template.selectList(
				getNamespace("selectUserModulePermissionWithoutClassId"), map);
	}

}
