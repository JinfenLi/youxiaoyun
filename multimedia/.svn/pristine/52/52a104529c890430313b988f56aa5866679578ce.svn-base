package com.topview.multimedia.service.section.article;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.service.section.draft.enums.RichTextTypeEnum;
import com.topview.multimedia.vo.RichTextInfo;

@Service
public class ArticleSendTypeProcess implements ArticleProcess {

	private static final Logger logger = Logger
			.getLogger(ArticleSendTypeProcess.class);
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(ArticleProcessContext context) {

		try {
			RichTextTypeEnum rE = RichTextTypeEnum.DRAFT;
			RichTextTypeEnum type = rE.getName(context.getRichTextType());
			if (type != null) {
				MultimediaRichText draft = RichTextInfo.changeToPo(context.getInfo());
				draft.setType(type.getCode());
				multimediaRichTextMapper.updateByPrimaryKey(draft);
				context.getResult().setSuccess(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("article send process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
