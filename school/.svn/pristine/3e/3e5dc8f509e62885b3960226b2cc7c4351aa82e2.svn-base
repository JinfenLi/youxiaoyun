package com.topview.school.controller.authc;

import java.util.Iterator;
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
import com.topview.school.po.Role;
import com.topview.school.service.system.authc.ExtTreeNode;
import com.topview.school.service.system.authc.ModuleService;
import com.topview.school.service.system.authc.RoleModuleService;
import com.topview.school.service.system.authc.RoleService;
import com.topview.school.service.system.authc.RoleType;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.MapUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 上午3:39:38
 * @see TODO
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/module", produces = "text/html;charset=UTF-8")
@SessionAttributes("currentUser")
public class ModuleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	private RoleModuleService roleModuleService;

	private static final Logger logger = Logger
			.getLogger(SinglePrimaryKeyBaseMapper.class);

	/**
	 * 
	 * @Title: 查询所有的模块
	 * @Description: TODO
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/module_list")
	@ResponseBody
	public String getModuleList(Model model) {

		return JSONUtil.wrap("modules", moduleService.selectByParameters(null));
	}

	/**
	 * 
	 * @Title: 查询所有角色下的所有模块
	 * @Description: TODO
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/role_module_list")
	@ResponseBody
	public String getRoleModuleList(Model model, HttpSession session) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = null;
		if (userInfo != null) {

			schoolId = userInfo.getSchool_id();
		} else {

			return JSONUtil.wrap("modules", null);
		}
		return JSONUtil.wrap("modules",
				moduleService.selectAllRoleModule(schoolId));
	}

	/**
	 * 
	 * @Title: 查询当前用户的所有模块
	 * @Description: TODO
	 * @param @param session
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/user_module_list")
	@ResponseBody
	public String getUserModuleList(HttpSession session, Model model) {

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");

		String userId = null;
		if (userInfo != null) {

			userId = userInfo.getUser_id();
			if (userId != null) {

				return JSONUtil.wrap("items", restrict(userId));
			} else {
				return JSONUtil.wrap("items", "");
			}
		} else {
			return JSONUtil.wrap("items", "");
		}
	}

	/**
	 * 
	 * @Title: 区域管理 特殊限制
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @return List<ExtTreeNode>
	 * @throws
	 */
	private List<ExtTreeNode> restrict(String userId) {

		List<Role> roles = roleService.selectByUserId(userId);
		boolean flag = false;
		for (Role r : roles) {

			if (r.getId() != null
					&& r.getId().equals(RoleType.SUPER_MANAGER.value())) {
				flag = true;
			}
		}
		if (!flag) {

			return restrictModule(moduleService.selectUserModule(userId));// 去掉限制显示的模块
		} else {

			return moduleService.selectUserModule(userId);
		}
	}

	/**
	 * 
	 * @Title: 限制特殊模块
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @return List<ExtTreeNode>
	 * @throws
	 */
	private List<ExtTreeNode> restrictModule(List<ExtTreeNode> nodes) {

		Iterator<ExtTreeNode> iterator = nodes.iterator();
		while (iterator.hasNext()) {

			ExtTreeNode n = iterator.next();
			String id = n.getId();
			if (id != null
					&& (id.equals("0") || id.equals("areamgr") || id
							.equals("publishnews"))) {

				iterator.remove(); // 注意这个地方
			}
		}
		return nodes;
	}

	/**
	 * 
	 * @Title: 根据角色ID查询拥有的模块树
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/module_tree_by_role_id")
	@ResponseBody
	public String getModuleTreeByRoleId(
			@RequestParam(required = true) @NotNull String roleId, Model model) {

		return JSONUtil.wrap("items",
				restrictModule(moduleService.selectModuleTreeByRoleId(roleId)));
	}

	/**
	 * 
	 * @Title: 根据角色ID查询拥有的模块列表
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/module_list_by_role_id")
	@ResponseBody
	public String getModuleListByRoleId(
			@RequestParam(required = true) @NotNull String roleId, Model model) {

		return JSONUtil.wrap("items",
				moduleService.selectModuleListByRoleId(roleId));
	}

	/**
	 * 
	 * @Title: 根据角色ID查询拥有的模块列表
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/father_module_list_by_role_id")
	@ResponseBody
	public String getFatherModuleListByRoleId(
			@RequestParam(required = true) @NotNull String roleId, Model model) {

		return JSONUtil.wrap("items",
				moduleService.selectFatherModuleListByRoleId(roleId));
	}

	/**
	 * 
	 * @Title: getSonModuleListByFatherModuleId
	 * @Description: TODO
	 * @param @param fatherModuleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/son_module_list_by_father_module_id")
	@ResponseBody
	public String getSonModuleListByFatherModuleId(
			@RequestParam(required = true) @NotNull String roleId,
			@RequestParam(required = true) @NotNull String fatherModuleId,
			Model model) {

		Map<String, Object> map = MapUtil.warp("module_id", fatherModuleId);
		map.put("role_id", roleId);

		return JSONUtil.wrap("items",
				moduleService.selectSonModuleListByRoleAndFatherModuleId(map));
	}

	/**
	 * 
	 * @Title: getUnarrangedModuleListByRoleId
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	// @RequestMapping("/unarranged_module_list_by_role_id")
	// @ResponseBody
	// public String getUnarrangedModuleListByRoleId(
	// @RequestParam(required = true) String roleId, Model model) {
	//
	// return JSONUtil.wrap("modules",
	// moduleService.selectUnarrangeRoleByUserId(roleId));
	// }

	/**
	 * 
	 * @Title: 分配模块
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param modules
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/arrange_module")
	@ResponseBody
	public String arrangeModule(@RequestParam(required = true) String roleId,
			String[] modules, Model model) {

		try {

			roleModuleService.arrangeModule(roleId, modules);
		} catch (Exception e) {

			logger.error("there is an error in arranging module!");
			return JSONUtil.wrap("success", 0);
		}
		return JSONUtil.wrap("success", 1);
	}

}
