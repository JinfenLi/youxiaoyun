package com.topview.school.controller.school.clazz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.Clazz;
import com.topview.school.po.Teacher;
import com.topview.school.po.UserRoleKey;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.system.authc.RoleType;
import com.topview.school.service.system.authc.UserRoleService;
import com.topview.school.service.user.teacher.TeacherService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.MyBatisMapUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.school.ClazzInfo;

/**
 * @Description 班级controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:54:20
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/clazz", produces = "text/html;charset=UTF-8")
public class ClazzController {

	@Resource
	private ClazzService clazzService;
	@Resource
	private TeacherService teacherService;
	@Autowired
	private UserRoleService userRoleService;
	private static final String roleId = RoleType.MASTER_TEACHER.value();

	/**
	 * 新建班级并为之建立一个多媒体空间、班级相册<br/>
	 * 直接所属班级的有班级相册、班级视频和家长圈<br/>
	 * 
	 * @param session
	 * @param clazz
	 * @return
	 */
	@RequestMapping("/createClazz")
	@ResponseBody
	public String createClazz(HttpSession session, Clazz clazz,
			String teacherId, Integer begin, Integer end, String level) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String gradeId = clazz.gettScGradeId();
		if (begin != null && !"".equals(begin) && end != null && !"".equals(end)) {
			if (begin != 0 && end != 0) { // 快捷方式批量创建班级
				for (int i = begin; i <= end; i++) {
					Clazz c = new Clazz();
					c.setId(UUIDUtil.getUUID());
					c.setName(level.substring(0,2) + "（" + i + "）班");
					c.settScGradeId(gradeId);
					clazzService.createClazz(c);
					resultMap.put("success", true);
				}
			}
		} else { // 单独创建一个班级
			clazz.setId(UUIDUtil.getUUID());
			if (clazzService.createClazz(clazz)) {
				if (teacherId != null && !"".equals(teacherId)) { // 如果同时指定了班主任则进入

					Teacher teacher = new Teacher();
					teacher.setId(teacherId);
					teacher.settScClassId(clazz.getId());
					teacherService.updateTeacherInfo(teacher);

					UserRoleKey key = new UserRoleKey(teacherId, roleId);
					key.setClassId(clazz.getId());
					userRoleService.insertSelective(key);// 添加班主任角色绑定
				}
				resultMap.put("success", true);
				resultMap.put("clazz", clazz);
			} else {
				resultMap.put("success", false);
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除班级(不删除多媒体空间)
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteClazz")
	@ResponseBody
	public String deleteClazz(HttpSession session, String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Teacher t = teacherService.findHeadTeacher(id);
			if (t != null && t.getId() != null) {

				userRoleService.deleteByUserId(t.getId());// 删除班主任角色分配
			}

			resultMap.put("success", clazzService.deleteClazz(id));
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "该班级尚关联其他重要数据，无法删除");
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据年级id获取班级
	 * 
	 * @param session
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/getClazzByGradeId")
	@ResponseBody
	public String getClazzByGradeId(HttpSession session, String gradeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Clazz> clazzs = clazzService.getClazzByGradeId(gradeId);
		if (clazzs.size() == 0) {
			resultMap.put("success", true);
			resultMap.put("clazzs", clazzs);
		} else {
			List<ClazzInfo> infos = ClazzInfo.changeToVo(clazzs);
			for (int i = 0; i < infos.size(); i++) {
				Teacher teacher = teacherService.findHeadTeacher(infos.get(i)
						.getId()); // 查询班主任
				if (teacher != null) {
					infos.get(i).setHeadTeacher(teacher.getName());
				}
			}
			resultMap.put("success", true);
			resultMap.put("clazzs", infos);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改班级资料
	 * 
	 * @param session
	 * @param clazz
	 * @return
	 */
	@RequestMapping("/updateClazz")
	@ResponseBody
	@Transactional
	public String updateClazz(HttpSession session, Clazz clazz, String teacherId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (teacherId != null && !"".equals(teacherId)) { // 如果同时指定了班主任则进入

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("clazzId", clazz.getId());
			teacherService.cancelPosition(map); // 取消已有的班主任任命

			Teacher teacher = new Teacher();
			teacher.setId(teacherId);
			teacher.settScClassId(clazz.getId());
			teacherService.updateTeacherInfo(teacher); // 任命新的班主任

			List<UserRoleKey> keys = userRoleService
					.selectByParameters(MyBatisMapUtil.warp("class_id",
							clazz.getId())); // 查询权限表记录

			if (keys != null && keys.size() == 1 && keys.get(0) != null) {

				UserRoleKey key = keys.get(0);
				userRoleService.deleteByPrimaryKey(key);

				key.setUserId(teacherId);
				userRoleService.insertSelective(key);// 更新班主任
			} else {

				UserRoleKey key = new UserRoleKey();
				key.setUserId(teacherId);
				key.setClassId(clazz.getId());
				key.setRoleId(roleId);
				userRoleService.insertSelective(key);
			}
		}
		resultMap.put("success", clazzService.updateClazz(clazz)); // TODO 事务控制
		return JSONUtil.toObject(resultMap).toString();
	}
}
