package com.topview.multimedia.service.section.draft;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
@Service
public class DraftDeleteProcess implements DraftProcess{
	private static final Logger logger = Logger
			.getLogger(DraftDeleteProcess.class);
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(DraftProcessContext context) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			multimediaRichTextMapper.deleteByPrimaryKey(context.getRichText().getId());
			result.put("success", true);
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("draft delete process fail");
		}
		result.put("success", false);
		result.put("message", "草稿删除失败");
		context.setResult(result);
		return false;
	}

}
