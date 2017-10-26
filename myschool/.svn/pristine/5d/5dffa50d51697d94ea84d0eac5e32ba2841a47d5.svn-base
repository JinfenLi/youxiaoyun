package com.topview.school.service.system.authc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.school.dao.system.authc.RoleModulePermissionMapper;
import com.topview.school.po.RoleModuleKey;
import com.topview.school.po.RoleModulePermissionKey;
import com.topview.school.service.system.authc.RoleModulePermissionService;
import com.topview.school.service.system.authc.RoleModuleService;
import com.topview.school.service.system.base.BaseServiceImpl;
import com.topview.school.util.MapUtil;
import com.topview.school.util.MyBatisMapUtil;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 下午12:27:41
 * @see TODO
 * @since 1.0
 */
@Service
public class RoleModulePermissionServiceImpl extends
		BaseServiceImpl<RoleModulePermissionKey> implements
		RoleModulePermissionService {

	@Autowired
	private RoleModuleService roleModuleService;

	@Resource
	private RoleModulePermissionMapper roleModulePermissionMapper;

	@Resource
	public void setBaseDao(RoleModulePermissionMapper roleModulePermissionMapper) {
		super.setBaseDao(roleModulePermissionMapper);
	}

	@Override
	@Transactional
	public int arrangePermission(String roleId, String moduleId,
			String[] permissions) {

		Map<String, Object> map = MapUtil.warp("role_id", roleId);
		map.put("module_id", moduleId);
		List<RoleModuleKey> rms = roleModuleService
				.selectByParameters(MyBatisMapUtil.warp(map));

		String id = null;
		if (rms != null && rms.size() == 1) {

			id = rms.get(0).getId();// 找出角色-模块关系的Id
		}
		roleModulePermissionMapper.deleteByRoleModuleId(id);// 删除原有的权限分配

		if (permissions != null) {

			for (String p : permissions) {

				RoleModulePermissionKey key = new RoleModulePermissionKey(id, p);
				int sign = roleModulePermissionMapper.insert(key);
				if (sign == 0) {

					throw new RuntimeException();
				}
			}
		}

		return 1;
	}

	@Override
	public int deleteByRoleModuleId(String id) {
		
		return roleModulePermissionMapper.deleteByRoleModuleId(id);
	}
}
