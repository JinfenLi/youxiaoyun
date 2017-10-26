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
import com.topview.school.util.NotEmptyString;
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
	@RequestMapping(value = "/createSemester", method = RequestMethod.POST)
	@ResponseBody
	public String createSemester(HttpSession session, SemesterVo vo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!NotEmptyString.isNotNullString(vo.gettScSchoolId())) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			vo.settScSchoolId(userInfo.getSchool_id());
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vo.setCreateTime(df.format(new Date()));
		vo.setId(UUIDUtil.getUUID());
		if (semesterService.createSemester(vo)) {
			if (vo.getCurrentSemester() == 1) {
				resultMap.put("success", semesterService.setCurrentSemester(vo.getId()));
			} else {
				resultMap.put("success", true);
			}
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
	public String getSemesterBySchoolId(HttpSession session, String schoolId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!NotEmptyString.isNotNullString(schoolId)) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			schoolId = userInfo.getSchool_id();
		}
		List<SemesterVo> semesterVos = semesterService.findAll(schoolId);
		if (semesterVos != null) {
			resultMap.put("success", true);
			resultMap.put("semesters", semesterVos);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
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
		if (!NotEmptyString.isNotNullString(schoolId)) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			schoolId = userInfo.getSchool_id();
		}
		List<SemesterVo> semesterVos = semesterService.selectHistorySemester(schoolId);
		if (semesterVos != null) {
			resultMap.put("success", true);
			resultMap.put("semesters", semesterVos);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: updateSemesterById
	* @Description: 根据id更新学期
	* @param @param vo
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/updateSemesterById")
	@ResponseBody
	public String updateSemesterById(SemesterVo vo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", semesterService.updateSemesterById(vo));
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: setCurrentSemester
	* @Description: 设为当前学期
	* @param @param semesterId
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/setCurrentSemester")
	@ResponseBody
	public String setCurrentSemester(String semesterId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", semesterService.setCurrentSemester(semesterId));
		return JSONUtil.toObject(resultMap).toString();
	}
}
