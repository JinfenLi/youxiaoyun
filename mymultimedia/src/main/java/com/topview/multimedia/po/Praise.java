package com.topview.multimedia.po;

import java.util.Date;

public class Praise {
	
	private String id;

	private String tMultimediaPostId; //帖子id

	private String praiserId; //点赞者id

	private Date praiserTime; //点赞时间
	
	private String praiserName; //点赞者姓名
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettMultimediaPostId() {
		return tMultimediaPostId;
	}

	public void settMultimediaPostId(String tMultimediaPostId) {
		this.tMultimediaPostId = tMultimediaPostId;
	}

	public String getPraiserId() {
		return praiserId;
	}

	public void setPraiserId(String praiserId) {
		this.praiserId = praiserId;
	}

	public Date getPraiserTime() {
		return praiserTime;
	}

	public void setPraiserTime(Date praiserTime) {
		this.praiserTime = praiserTime;
	}

	public String getPraiserName() {
		return praiserName;
	}

	public void setPraiserName(String praiserName) {
		this.praiserName = praiserName;
	}
}