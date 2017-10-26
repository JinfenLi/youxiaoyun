package com.topview.school.dao.feedback.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.feedback.PageViewMapper;
import com.topview.school.po.PageView;

@Repository
public class PageViewMapperImpl extends BaseDaoImpl<PageView> implements
		PageViewMapper {

	@Override
	public int selectIpCount(String beginTime, String endTime, String url, String schoolId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("schoolId", schoolId);
		params.put("url", url);
		return template.selectOne(getStatement("selectIpCount"), params);
	}

	@Override
	public int selectVisitCount(String beginTime, String endTime, String url,String schoolId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("schoolId",schoolId);
		params.put("url", url);
		return template.selectOne(getStatement("selectVisitCount"), params);
	}

}
