package com.topview.multimedia.service.section.draft;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
@Service
public class DraftUpdateProcess implements DraftProcess {

	private static final Logger logger = Logger
			.getLogger(DraftUpdateProcess.class);
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(DraftProcessContext context) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			multimediaRichTextMapper.updateByPrimaryKeySelective(context.getRichText());
			result.put("success", true);
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("draft update process fail");
		}
		result.put("success", false);
		result.put("message", "草稿更新失败");
		context.setResult(result);
		return false;
	}

}
