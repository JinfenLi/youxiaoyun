package com.topview.school.controller.authc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.topview.school.dao.base.SinglePrimaryKeyBaseMapper;
import com.topview.school.po.Permission;
import com.topview.school.service.system.authc.PermissionService;
import com.topview.school.service.system.authc.RoleModulePermissionService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.MapUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 下午12:15:02
 * @see TODO
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/permission", produces = "text/html;charset=UTF-8")
@SessionAttributes("currentUser")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleModulePermissionService roleModulePermissionService;

	private static final Logger logger = Logger
			.getLogger(SinglePrimaryKeyBaseMapper.class);

	/**
	 * 
	 * @Title: 查询所有按钮级权限
	 * @Description: TODO
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/permission_list")
	@ResponseBody
	public String getpermissionList(Model model) {

		return JSONUtil.wrap("permissions",
				permissionService.selectByParameters(null));
	}

	/**
	 * 
	 * @Title: 查询所有模块下的按钮权限
	 * @Description: TODO
	 * @param @param session
	 * @param @param module_id
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/user_module_permission_list")
	@ResponseBody
	public String getUserModulePermissionList(HttpSession session,
			String moduleId, String classId, Model model) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String userId = null;
		if (userInfo != null) {

			userId = userInfo.getUser_id();
			if (userId != null) {
				return JSONUtil.wrap("permissions", permissionService
						.selectUserModulePermission(userId, moduleId, classId));
			} else {
				return JSONUtil.wrap("permissions", "");
			}
		} else {
			return JSONUtil.wrap("permissions", "");
		}

	}

	/**
	 * 
	 * @Title: 根据角色ID 和 模块ID 查询拥有的权限
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/permission_list_by_role_module_id")
	@ResponseBody
	public String getModuleListByRoleId(
			@RequestParam(required = true) @NotNull String roleId,
			@RequestParam(required = true) @NotNull String moduleId, Model model) {

		Map<String, Object> map = MapUtil.warp("role_id", roleId);
		map.put("module_id", moduleId);

		List<Permission> rmps = permissionService.selectByRoleModuleId(map);
		return JSONUtil.wrap("permissions", rmps);
	}

	/**
	 * 
	 * @Title: 根据角色Id 和 模块Id 查询所有未分配的权限
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param moduleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/unarranged_permission_list_by_role_module_id")
	@ResponseBody
	public String getUnarrangedPermissionListByRoleModuleId(
			@RequestParam(required = true) @NotNull String roleId,
			@RequestParam(required = true) @NotNull String moduleId, Model model) {

		Map<String, Object> map = MapUtil.warp("role_id", roleId);
		map.put("module_id", moduleId);

		List<Permission> rmps = permissionService
				.selectUnarrangePermissionByRoleModuleId(map);

		return JSONUtil.wrap("permissions", rmps);
	}

	/**
	 * 
	 * @Title: 分配权限
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param moduleId
	 * @param @param permissions
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/arrange_permission")
	@ResponseBody
	public String arrangePermission(
			@RequestParam(required = true) @NotNull String roleId,
			@RequestParam(required = true) @NotNull String moduleId,
			String[] permissions, Model model) {

		try {

			roleModulePermissionService.arrangePermission(roleId, moduleId,
					permissions);
		} catch (Exception e) {

			logger.error("there is an error in arranging permission!");
			return JSONUtil.wrap("success", 0);
		}
		return JSONUtil.wrap("success", 1);
	}

}
