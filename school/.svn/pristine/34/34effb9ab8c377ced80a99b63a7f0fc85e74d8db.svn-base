package com.topview.school.service.school.article;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.section.article.ArticleService;
@Service
public class SchoolArticleDeleteProcess implements SchoolArticleProcess{
	@Autowired
	private ArticleService articleService;
	private static final Logger logger = Logger
			.getLogger(SchoolArticleDeleteProcess.class);
	@Override
	public boolean doProcess(SchoolArticleProcessContext context) {
		try{
			articleService.articleDelete(context.getInfo());
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e){
			logger.error("clazz delete article fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
