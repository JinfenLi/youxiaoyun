package com.topview.school.controller.school.grade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.ResourceType;
import com.topview.school.service.school.grade.ResourceTypeService;
import com.topview.school.util.JSONUtil;

/**
 * 
 * @ClassName: ResourceTypeController 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月31日 下午9:15:40 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/resource_type", produces = "text/html;charset=UTF-8")
public class ResourceTypeController {

	@Autowired
	private ResourceTypeService resourceTypeService;
	
	@RequestMapping(value ="/addType", method=RequestMethod.POST)
	@ResponseBody
	public String add(ResourceType type){
		Map<String, Object> result = new HashMap<String, Object>();
		if(type == null){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		if(type.getId() == null){
			result.put("result", resourceTypeService.saveType(type));
		}else{
			result.put("result", resourceTypeService.updateType(type));
		}
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping("/deleteType")
	@ResponseBody
	public String delete(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		if(id == null){
			result.put("result", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("result", resourceTypeService.deleteType(id));
		return JSONUtil.toObject(result).toString();
	}
	
	@RequestMapping("/getAllType")
	@ResponseBody
	public String getAllType(String schoolId){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ResourceType> ts = new ArrayList<ResourceType>();
		ResourceType t = new ResourceType();
		t.setId("");
		t.setName("所有");
		t.setSchoolId(schoolId);
		ts.add(t);
		ts.addAll(resourceTypeService.getAllType(schoolId));
		result.put("result", ts);
		return JSONUtil.toObject(result).toString();
	}
}
