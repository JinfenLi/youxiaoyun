package com.topview.multimedia.service.section.draft;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.service.section.draft.enums.RichTextTypeEnum;
@Service
public class DraftSaveProcess implements DraftProcess {

	private static final Logger logger = Logger
			.getLogger(DraftSaveProcess.class);
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(DraftProcessContext context) {

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MultimediaRichText richText = context.getRichText();
			richText.setType(RichTextTypeEnum.DRAFT.getCode());
			multimediaRichTextMapper.insert(richText);
			result.put("success", true);
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("draft save process fail");
		}
		result.put("success", false);
		result.put("message", "草稿保存失败");
		context.setResult(result);
		return false;
	}

}
