package com.topview.multimedia.service.section.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.dao.MultimediaZoneMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.po.MultimediaZone;

@Service
public class ArticleCheckProcess implements ArticleProcess{
	@Autowired
	private MultimediaZoneMapper multimediaZoneMapper;
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(ArticleProcessContext context) {
		try {
			MultimediaZone zone = multimediaZoneMapper.findById(context
					.getInfo().getZoneId());
			MultimediaRichText article = multimediaRichTextMapper.findById(context
					.getInfo().gettMId());
			if (zone != null && article != null&&zone.getId().equals(article.gettMId())) {
				context.getResult().setSuccess(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
