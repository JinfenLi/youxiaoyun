package com.topview.multimedia.service.section.draft;

import java.util.Map;

import com.topview.multimedia.po.MultimediaRichText;

public class DraftProcessContext {

	private MultimediaRichText richText;
	private String richTextType;
	private Map<String, Object> result;
	private String sectionId;
	private String zoneId;
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getRichTextType() {
		return richTextType;
	}

	public void setRichTextType(String richTextType) {
		this.richTextType = richTextType;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public MultimediaRichText getRichText() {
		return richText;
	}

	public void setRichText(MultimediaRichText richText) {
		this.richText = richText;
	}

}
