package com.topview.school.controller.school.position;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.TeacherPosition;
import com.topview.school.service.user.teacher.TeacherPositionService;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * 教师职位的controller
* @ClassName: TeacherPositionController 
* @author lxd  <836696016@qq.com>
* @date 2015年8月14日 下午8:53:16 
* @version V1.0
 */
@Controller
@RequestMapping(value = "/teacher_position", produces = "text/html;charset=UTF-8")
public class TeacherPositionController {

	@Autowired
	private TeacherPositionService teacherPositionService;
	
	@RequestMapping(value = "/addPosition" , method = RequestMethod.POST)
	@ResponseBody
	public String addPosition(TeacherPosition position){
		Map<String,Object> result = new HashMap<String, Object>();
		if(position == null || "".equals(position)){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if(position.getName() == null || "".equals(position.getName())){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if(position.getSchoolId() == null || "".equals(position.getSchoolId())){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", teacherPositionService.add(position));
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping("/deletePosition")
	@ResponseBody
	public String deletePosition(String positionId){
		Map<String,Object> result = new HashMap<String, Object>();
		if(positionId == null || "".equals(positionId)){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", teacherPositionService.delete(positionId));
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping(value = "/updatePosition" , method = RequestMethod.POST)
	@ResponseBody
	public String updatePosition(TeacherPosition position){
		Map<String,Object> result = new HashMap<String, Object>();
		if(position == null || "".equals(position)){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if(position.getId() == null || "".equals(position.getId())){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", teacherPositionService.update(position));
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping("/getPositionsByTeacher")
	@ResponseBody
	public String getPositionsByTeacher(String teacherId){
		Map<String,Object> result = new HashMap<String, Object>();
		if(teacherId == null || "".equals(teacherId)){
			result.put("result", null);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", teacherPositionService.getPositions(teacherId));
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping("/getAllPositions")
	@ResponseBody
	public String getAllPositions(Integer start, Integer limit, HttpSession session, Pager pager){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("success", false);
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if(userInfo == null) {
			return JSONUtil.toObject(result).toString();
		}
		if (start != null && !"".equals(start) && limit != null
				&& !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		result.put("success", true);
		result.put("result", teacherPositionService.getAllPositions(pager, userInfo.getSchool_id()));
		result.put("totalCount", teacherPositionService.getCount(userInfo.getSchool_id()));
		return JSONUtil.toObject(result).toString();
	}
}
