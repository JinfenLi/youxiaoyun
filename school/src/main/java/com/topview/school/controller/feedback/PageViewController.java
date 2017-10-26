package com.topview.school.controller.feedback;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.service.feedback.PageViewService;
import com.topview.school.util.JSONUtil;

@Controller
@RequestMapping(value = "pv", produces = "text/html;charset=UTF-8")
public class PageViewController {

	@Resource
	private PageViewService pageViewService;
	
	@RequestMapping("/getPv")
	@ResponseBody
	public String getPv(String beginTime, String endTime,String schoolId) {
		return JSONUtil.toObject(pageViewService.getPvCount(beginTime, endTime,schoolId)).toString();
	}
	
/*	@ResponseBody
	public String getIpTrace(String beginTime,String endTime,String ){
		
	}*/
	
}
