package com.topview.school.controller.contact;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.school.po.Clazz;
import com.topview.school.po.Departments;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.clazz.contacts.ContactsService;
import com.topview.school.service.school.department.DepartmentService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.SortChineseName;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.contacts.ContactsInfo;
import com.topview.school.vo.contacts.ParentContact;
import com.topview.school.vo.school.SemesterVo;

/**
 * @Description 通讯录与用户头像controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:47:42
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/contact", produces = "text/html;charset=UTF-8")
public class ContactController {

	@Resource
	private ContactsService contactsService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private SemesterService semesterService;
	@Resource
	private DepartmentService departmentService;
	/**
	 * 获取用户通讯录
	 * 
	 * @param session
	 * @param clazzId
	 * @param studentId
	 * @return
	 */
	@RequestMapping("/getUserContact")
	@ResponseBody
	public String getUserContact(HttpSession session, String clazzId) {
		String[] filter = { "code", "teacherContacts", "studentResult" };
		return JSONUtil.toObject(
				contactsService.getUserContacts(session, clazzId), filter)
				.toString();
	}

	/**
	 * web端获取家长通讯录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("getParentContactForWeb")
	@ResponseBody
	public String getWebContact(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo == null) {
			resultMap.put("msg", "尚未登录，请重新登陆");
			return JSONUtil.toObject(resultMap).toString();
		}
		String schoolId = userInfo.getSchool_id();
		SemesterVo currentSemster = semesterService
				.getCurrentSemester(schoolId); // 获取当前学期
		List<Clazz> clazzs = clazzService.selectTeacherClazzs(
				userInfo.getUser_id(), currentSemster.getId()); // 获取当前学期所带的班级
		Collections.sort(clazzs, new SortChineseName<Clazz>("getName"));
		// 获取各个班级的家长通讯录
		for (Clazz c : clazzs) {
			List<ParentContact> pContacts = contactsService.getParentContacts(c
					.getId());
			Iterator<ParentContact> iterator = pContacts.iterator();
			while (iterator.hasNext()) {
				if ("2".equals(iterator.next().getParentType())) {
					iterator.remove();
				}
			}
			resultMap.put(c.getName(), pContacts);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * web端获取教师通讯录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("getTeacherContactForWeb")
	@ResponseBody
	public String webGetTeacherContact(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo == null) {
			resultMap.put("msg", "尚未登录，请重新登陆");
			return JSONUtil.toObject(resultMap).toString();
		}
		List<Departments> departments = departmentService
				.selectAllBySchoolId(userInfo.getSchool_id());
		for (Departments d : departments) {
			List<ContactsInfo> contacts = contactsService
					.getDepartmentContact(d.getId());
			if (contacts.size() > 0) {
				resultMap.put(d.getName(), contacts);
			}
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 更新通讯录头像
	 * 
	 * @param pic
	 * @param session
	 * @param userId
	 *            （学生的id）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePic", method = RequestMethod.POST)
	@ResponseBody
	public String updatePic(@RequestParam("pic") MultipartFile pic,
			HttpSession session, String userId, HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(); 
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = userInfo.getSchool_id();
		FileUploadVo vo = FileUploadUtil.uploadFile(pic, schoolId + "/userPic", request, true);
		String picUrl = vo.getRelativePath();
		String[] filter = { "teacherResult", "studentResult", "code" };
		map.put("updateResult",
				contactsService.updatePic(session, picUrl, userId));
		map.put("picUrl", picUrl);
		return JSONUtil.toObject(map, filter).toString();
	}

}
