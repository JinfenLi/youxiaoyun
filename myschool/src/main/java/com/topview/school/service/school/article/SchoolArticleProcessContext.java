package com.topview.school.service.school.article;

import com.topview.multimedia.service.section.article.ArticleProcessContext;

public class SchoolArticleProcessContext extends ArticleProcessContext{
	private String schoolId;
	private String type;
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
