package com.topview.school.controller.authc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.topview.school.dao.base.SinglePrimaryKeyBaseMapper;
import com.topview.school.po.Role;
import com.topview.school.po.RoleModuleKey;
import com.topview.school.service.system.authc.RoleModulePermissionService;
import com.topview.school.service.system.authc.RoleModuleService;
import com.topview.school.service.system.authc.RoleService;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.MapUtil;
import com.topview.school.util.MyBatisMapUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 上午2:13:38
 * @see TODO
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/role", produces = "text/html;charset=UTF-8")
@SessionAttributes("currentUser")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleModuleService roleModuleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleModulePermissionService roleModulePermissionService;

	private static final Logger logger = Logger
			.getLogger(SinglePrimaryKeyBaseMapper.class);

	/**
	 * 
	 * @Title: 查询所有的角色
	 * @Description: TODO
	 * @param @param userId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/role_list")
	@ResponseBody
	public String getLineRoleList(Model model, HttpSession session) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String school_id = null;
		if (userInfo != null) {
			
			school_id = userInfo.getSchool_id();
			if (school_id != null) {
				
				return JSONUtil.wrap("roles", roleService
						.selectByParameters(MyBatisMapUtil.warp("school_id",
								school_id)));
			} else {
				return JSONUtil.wrap("roles", "");
			}
		} else {
			return JSONUtil.wrap("roles", "");
		}
	}

	/**
	 * 
	 * @Title: 查询所有用户的所有角色
	 * @Description: TODO
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/user_role_list")
	@ResponseBody
	public String getUserRoleList(Model model, HttpSession session) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = null;
		if (userInfo != null) {

			schoolId = userInfo.getSchool_id();
		} else {

			return JSONUtil.wrap("user_roles", null);
		}
		return JSONUtil.wrap("user_roles",
				roleService.selectAllUserRole(schoolId));
	}

	/**
	 * 
	 * @Title: 根据用户ID查询用有的角色
	 * @Description: TODO
	 * @param @param userId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/role_list_by_user_id")
	@ResponseBody
	public String getLineRoleListByLineId(
			@RequestParam(required = true) String userId, Model model) {

		return JSONUtil.wrap("roles", roleService.selectByUserId(userId));
	}

	/**
	 * 
	 * @Title: 查询某用户未分配的角色
	 * @Description: TODO
	 * @param @param userId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/unarranged_role_list_by_user_id")
	@ResponseBody
	public String getUnarrangedRoleListByUserId(
			@RequestParam(required = true) String userId, HttpSession session,
			Model model) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = null;
		if (userInfo != null) {

			schoolId = userInfo.getSchool_id();
		} else {

			return JSONUtil.wrap("roles", null);
		}
		Map<String, Object> map = MapUtil.warp("school_id", schoolId);
		map.put("user_id", userId);
		return JSONUtil.wrap("roles",
				roleService.selectUnarrangeRoleByUserId(map));
	}

	/**
	 * 
	 * @Title: 添加角色
	 * @Description: TODO
	 * @param @param role
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/add_role")
	@ResponseBody
	public String addRole(Role role, Model model, HttpSession session) {

		// @RequestParam(required = true)
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = null;
		if (userInfo != null) {

			schoolId = userInfo.getSchool_id();
		} else {

			return JSONUtil.wrap("success", 0);// 插入失败
		}
		role.setId(UUIDUtil.getUUID());
		role.setSchoolId(schoolId);
		role.setAvailable(true);

		if (roleService.insert(role) == 0) {// 这个数为被该操作影响的行数

			return JSONUtil.wrap("success", 0);// 插入失败
		} else {

			return JSONUtil.wrap("success", 1);// 插入成功
		}
	}

	/**
	 * 
	 * @Title: 删除角色
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/del_role")
	@ResponseBody
	@Transactional
	public String delRole(@RequestParam(required = true) String roleId,
			Model model) {

		userRoleService.deleteByRoleId(roleId);

		List<RoleModuleKey> keys = roleModuleService
				.selectByParameters(MyBatisMapUtil.warp("role_id", roleId));

		if (keys != null) {

			for (RoleModuleKey k : keys) {
				if (k.getId() != null)
					roleModulePermissionService.deleteByRoleModuleId(k.getId());
			}
		}
		roleModuleService.deleteByRoleId(roleId);
		if (roleService.deleteByPrimaryKey(roleId) == 0) {// 这个数为被该操作影响的行数

			return JSONUtil.wrap("success", 0);// 删除失败
		} else {

			return JSONUtil.wrap("success", 1);// 删除成功
		}
	}

	/**
	 * 
	 * @Title: 修改角色
	 * @Description: TODO
	 * @param @param role
	 * @param @param model
	 * @param @param redirectAttributes
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/edit_role")
	@ResponseBody
	public String editRole(Role role, Model model,
			RedirectAttributes redirectAttributes,HttpSession session) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = null;
		if (userInfo != null) {

			schoolId = userInfo.getSchool_id();
		} else {

			return JSONUtil.wrap("success", 0);// 更新失败
		}
		role.setSchoolId(schoolId);
		role.setAvailable(true);
		
		if (roleService.updateByPrimaryKey(role) == 0) {// 这个数为被该操作影响的行数

			return JSONUtil.wrap("success", 0);// 更新失败
		} else {

			return JSONUtil.wrap("success", 1);// 更新成功
		}
	}

	/**
	 * 
	 * @Title: 分配角色
	 * @Description: TODO
	 * @param @param userId
	 * @param @param roles
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/arrange_role")
	@ResponseBody
	public String arrangeRole(@RequestParam(required = true) String userId,
			String[] roles, Model model) {

		try {

			userRoleService.arrangeRole(userId, roles);
		} catch (Exception e) {

			logger.error("there is an error in arranging role!");
			return JSONUtil.wrap("success", 0);
		}
		return JSONUtil.wrap("success", 1);
	}

}
