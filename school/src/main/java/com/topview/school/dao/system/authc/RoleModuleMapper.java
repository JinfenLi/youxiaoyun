package com.topview.school.dao.system.authc;

import com.topview.school.dao.base.SinglePrimaryKeyBaseMapper;
import com.topview.school.po.RoleModuleKey;

public interface RoleModuleMapper extends
		SinglePrimaryKeyBaseMapper<RoleModuleKey> {

	/**
	 * 
	 * @Title: deleteByRoleId
	 * @Description: TODO
	 * @param @param roleId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int deleteByRoleId(String roleId);


}