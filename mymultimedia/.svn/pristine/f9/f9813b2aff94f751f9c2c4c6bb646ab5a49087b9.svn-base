package com.topview.multimedia.service.section.draft;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.service.section.draft.enums.RichTextTypeEnum;
@Service
public class DraftLimitProcess implements DraftProcess{

	public boolean doProcess(DraftProcessContext context) {
		MultimediaRichText richText = context.getRichText();
		RichTextTypeEnum type = RichTextTypeEnum.DRAFT;
		Map<String, Object> result = new HashMap<String, Object>();
		if(richText.getType() != type.getCode()){
			result.put("message", "不是草稿");
			result.put("success", false);
			context.setResult(result);
			return false;
		}
		result.put("success", true);
		context.setResult(result);
		return true;
	}

}
