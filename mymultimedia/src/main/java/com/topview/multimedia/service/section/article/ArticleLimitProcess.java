package com.topview.multimedia.service.section.article;

import org.springframework.stereotype.Service;

import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.service.section.draft.enums.RichTextTypeEnum;
import com.topview.multimedia.vo.RichTextInfo;
@Service
public class ArticleLimitProcess implements ArticleProcess{

	public boolean doProcess(ArticleProcessContext context) {
		MultimediaRichText richText = RichTextInfo.changeToPo(context.getInfo());
		RichTextTypeEnum type = RichTextTypeEnum.DRAFT;
		System.out.println(type.getCode()+"process");
		if(richText.getType() != type.getCode()){
			context.getResult().setSuccess(false);
			return false;
		}
		context.getResult().setSuccess(true);
		return true;
	}

}
