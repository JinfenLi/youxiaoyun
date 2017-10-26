package com.topview.school.service.school.article;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.result.RichTextInfoResult;

public interface SchoolArticleService {
	
	public RichTextInfoResult getArticleById(String articleId);
	public RichTextInfoResult getAllArticle(String schoolId, String type,Pager pager);
	public RichTextInfoResult articleDelete(String schoolId);
	public RichTextInfoResult articleSend(String schoolId, String type, String content, String title, String path, String createTime);
	public RichTextInfoResult articleCopy(String articleId, String zoneId, String type);
}
