package com.topview.multimedia.service.section.draft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaSectionMapper;
import com.topview.multimedia.po.MultimediaSection;

@Service
public class DraftFindAllProcess implements DraftProcess {

	@Autowired
	private MultimediaSectionMapper multimendiaSectionMapper;
	private static final Logger logger = Logger
			.getLogger(DraftFindAllProcess.class);

	public boolean doProcess(DraftProcessContext context) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("t_m_id", context.getRichText().gettMId());
			List<MultimediaSection> l = multimendiaSectionMapper.findByMulti(param);
			result.put("success", true);
			result.put("message", "find draft success");
			result.put("list", l);
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("find draft fail");
		}
		result.put("success", false);
		result.put("message", "find draft fail");
		result.put("list", null);
		context.setResult(result);
		return false;
	}

}
