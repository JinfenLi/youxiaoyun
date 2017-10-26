package com.topview.school.service.system.authc.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.system.authc.RoleMapper;
import com.topview.school.po.Role;
import com.topview.school.po.Teacher;
import com.topview.school.service.system.authc.RoleService;
import com.topview.school.service.system.base.BaseServiceImpl;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.MapUtil;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 上午2:34:39
 * @see TODO
 * @since 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Resource
	private RoleMapper roleMapper;
	@Autowired
	private TeacherService teacherService;

	@Override
	public List<Role> selectByUserId(String userId) {

		return roleMapper.selectByUserId(userId);
	}

	@Resource
	public void setBaseDao(RoleMapper roleMapper) {

		super.setBaseDao(roleMapper);
	}

	@Override
	public List<Map<String, Object>> selectAllUserRole(String schoolId) {

		List<Teacher> teachers = teacherService.getTeacher(MapUtil.warp(
				"schoolId", schoolId));

		List<Map<String, Object>> maps = new LinkedList<>();
		for (Teacher t : teachers) {

			if(t!= null){
				
				Map<String, Object> map = MapUtil.warp("id", t.getId());
				map.put("name", t.getName());
				map.put("roles", roleMapper.selectByUserId(t.getId()));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public List<Object> selectUnarrangeRoleByUserId(Map<String, Object> map) {
		
		return roleMapper.selectUnarrangeRoleByUserId(map);
	}

}
