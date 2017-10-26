package com.topview.multimedia.service.section.article;

import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

public class ArticleProcessContext {

	private RichTextInfo info;
	private RichTextInfoResult result;
	private String richTextType;

	public RichTextInfo getInfo() {
		return info;
	}

	public void setInfo(RichTextInfo info) {
		this.info = info;
	}

	public RichTextInfoResult getResult() {
		return result;
	}

	public void setResult(RichTextInfoResult result) {
		this.result = result;
	}

	public String getRichTextType() {
		return richTextType;
	}

	public void setRichTextType(String richTextType) {
		this.richTextType = richTextType;
	}

}
