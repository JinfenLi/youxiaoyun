package com.topview.school.controller.school.area;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.Area;
import com.topview.school.service.school.SchoolService;
import com.topview.school.service.school.area.AreaService;
import com.topview.school.service.user.UserService;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.school.SchoolInfo;

/**
 * @Description 区域controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年9月13日 下午2:06:01
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "area", produces = "text/html;charset=UTF-8")
public class AreaController {

	@Resource
	private AreaService areaService;
	@Resource
	private SchoolService schoolService;
	@Resource
	private UserService userService;

	/**
	 * 查询符合条件的区域
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("findArea")
	@ResponseBody
	public String findArea(Integer parentId, Integer leave) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Area> areas = areaService.findJuniorArea(parentId, leave);
		resultMap.put("success", true);
		resultMap.put("areas", areas);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据区域级别或父级区域id查询区域及区域下的所有学校
	 * 
	 * @param id
	 * @param leave
	 * @return
	 */
	@RequestMapping("findSchoolOfArea")
	@ResponseBody
	public String findSchoolOfArea(Integer id, Integer leave) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SchoolInfo> schoolInfos = new ArrayList<SchoolInfo>();
		List<Integer> areasId = new ArrayList<Integer>();

		if(leave == null || "".equals(leave)) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		if (leave == 1) {
			List<Area> cities = areaService.findJuniorArea(id, leave + 1); // 获取市
			for (Area city : cities) {
				List<Area> districts = areaService.findJuniorArea(city.getId(),
						leave + 2); // 获取区
				for (Area district : districts) {
					areasId.add(district.getId());
				}
			}
		} else if (leave == 2) {
			List<Area> districts = areaService.findJuniorArea(id, leave + 1); // 获取区
			for (Area district : districts) {
				areasId.add(district.getId());
			}
		} else if (leave == 3) {
			areasId.add(id);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "参数错误");
			return JSONUtil.toObject(resultMap).toString();
		}
		for (int i = 0; i < areasId.size(); i++) {
			schoolInfos.addAll(schoolService.findByAreaId(areasId.get(i)));
		}
		resultMap.put("success", true);
		resultMap.put("schools", schoolInfos);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 根据区域级别或父级区域id查询区域及区域下的所有学校及其登录数
	 * 
	 * @param id
	 * @param leave
	 * @return
	 */
	@RequestMapping("/getSchoolAndLoginCountOfArea")
	@ResponseBody
	public String getSchoolAndLoginCountOfArea(Integer id, Integer leave) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SchoolInfo> schoolInfos = new ArrayList<SchoolInfo>();
		List<Integer> areasId = new ArrayList<Integer>();

		if(leave == null || "".equals(leave)) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		if (leave == 1) {
			List<Area> cities = areaService.findJuniorArea(id, leave + 1); // 获取市
			for (Area city : cities) {
				List<Area> districts = areaService.findJuniorArea(city.getId(),
						leave + 2); // 获取区
				for (Area district : districts) {
					areasId.add(district.getId());
				}
			}
		} else if (leave == 2) {
			List<Area> districts = areaService.findJuniorArea(id, leave + 1); // 获取区
			for (Area district : districts) {
				areasId.add(district.getId());
			}
		} else if (leave == 3) {
			areasId.add(id);
		} else {
			resultMap.put("success", false);
			resultMap.put("msg", "参数错误");
			return JSONUtil.toObject(resultMap).toString();
		}
		for (int i = 0; i < areasId.size(); i++) {
			schoolInfos.addAll(schoolService.findByAreaId(areasId.get(i)));
		}
		// 获取计算学校登录数
		for (SchoolInfo info : schoolInfos) {
			Map<String, Object> loginCountMap = new HashMap<String, Object>();
			
			int schoolCountOfParent = Integer.parseInt(userService.getLoginCountForSchool(info.getId()));
			int notLoginCountOfParent = Integer.parseInt(userService.getNotLoginCountForSchool(info.getId()));
			int loginCountOfParent = schoolCountOfParent - notLoginCountOfParent;
			double loginParentPercentage = schoolCountOfParent != 0 ?
					(double) loginCountOfParent / schoolCountOfParent : 0;
			int schoolCountOfTeacher = Integer.parseInt(userService.getLoginCountForSchoolTeacher(info.getId()));
			int notLoginCountOfTeacher = Integer.parseInt(userService.getNotLoginCountForSchoolTeacher(info.getId()));
			int loginCountOfTeacher = schoolCountOfTeacher - notLoginCountOfTeacher;
			double loginTeacherPercentage = schoolCountOfTeacher != 0 ?
					(double) loginCountOfTeacher / schoolCountOfTeacher : 0;
			
			loginCountMap.put("schoolCountOfParent", schoolCountOfParent);
			loginCountMap.put("notLoginCountOfParent", notLoginCountOfParent);
			loginCountMap.put("loginCountOfParent", loginCountOfParent);
			loginCountMap.put("loginParentPercentage", new DecimalFormat("0.00").format(100 * loginParentPercentage) + "%");
			loginCountMap.put("schoolCountOfTeacher", schoolCountOfTeacher);
			loginCountMap.put("notLoginCountOfTeacher", notLoginCountOfTeacher);
			loginCountMap.put("loginCountOfTeacher", loginCountOfTeacher);
			loginCountMap.put("loginTeacherPercentage", new DecimalFormat("0.00").format(100 * loginTeacherPercentage) + "%");
			
			info.setLoginCountMap(loginCountMap);
		}
		resultMap.put("success", true);
		resultMap.put("schools", schoolInfos);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 查询区域树
	 * 
	 * @param node
	 * @return
	 */
	@RequestMapping("showAreaTree")
	@ResponseBody
	public String showAreaTree(int node) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		Area area = areaService.selectAreaByPrimaryKey(node);
		if (area != null && "3".equals(area.getLeave())) {
			resultMap.put("schools", schoolService.findByAreaId(node));
		} else {
			resultMap.put("areas", areaService.findJuniorArea(node, null));
		}
		return JSONUtil.toObject(resultMap).toString();
	}
}
