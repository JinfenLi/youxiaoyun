package com.topview.school.controller.school.semester;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.school.SemesterVo;

/**
 * 学期controller
 * 
 * @Description
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:56:58
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/semester", produces = "text/html;charset=UTF-8")
public class SemesterController {

	@Resource
	private SemesterService semesterService;

	/**
	 * 创建一个学期
	 * 
	 * @param session
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param week
	 *            持续的周数
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/createSemester", method = RequestMethod.POST)
	@ResponseBody
	public String createSemester(HttpSession session, SemesterVo vo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (vo.gettScSchoolId() == null || "".equals(vo.gettScSchoolId())) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			vo.settScSchoolId(userInfo.getSchool_id());
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		vo.setCreateTime(df.format(new Date()));
		vo.setId(UUIDUtil.getUUID());
		if (semesterService.createSemester(vo)) {
			resultMap.put("semesterVo", vo);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据学校id获取学期
	 * 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("getSemesterBySchoolId")
	@ResponseBody
	public String getSemesterBySchoolId(String schoolId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SemesterVo> semesterVos = semesterService.findAll(schoolId);
		if (semesterVos.size() > 0) {
			resultMap.put("success", true);
			resultMap.put("semesters", semesterVos);
		} else {
			resultMap.put("success", false);
		}
		String[] filter = { "createTime", "startTime", "endTime", "week" };
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 获取历史和当前学期
	 * 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("selectHistorySemester")
	@ResponseBody
	public String selectHistorySemester(HttpSession session, String schoolId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (schoolId == null || "".equals(schoolId)) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			schoolId = userInfo.getSchool_id();
		}
		resultMap.put("success", true);
		resultMap.put("semesters",
				semesterService.selectHistorySemester(schoolId));
		String[] filter = { "createTime", "endTime", "startTime", "week",
				"tScSchoolId" };
		return JSONUtil.toObject(resultMap, filter).toString();
	}
}
