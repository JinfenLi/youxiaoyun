package com.topview.school.service.school.article;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.result.RichTextInfoResult;
import com.topview.school.util.HtmlUtil;

@Service
public class SchoolArticleFindProcess implements SchoolArticleProcess{
	private static final Logger logger = Logger
			.getLogger(SchoolArticleFindProcess.class);
	@Autowired
	private ArticleService articleService;
	@Override
	public boolean doProcess(SchoolArticleProcessContext context) {
		try{
		RichTextInfoResult result = articleService.articleFind(context.getInfo());
		if (result.getInfoResult() != null
				&& result.getInfoResult().size() > 0) {
			String text = result.getInfoResult().get(0).getContext();
			if(!text.contains("<!doctype html><html><head>")) {
				text = HtmlUtil.toHtml(text);
			}
			result.getInfoResult().get(0).setContext(text);
		}
		context.getResult().setInfoResult(result.getInfoResult());
		context.getResult().setSuccess(true);
		return true;
		}catch(Exception e){
			logger.error("clazz find  article fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}	

}
