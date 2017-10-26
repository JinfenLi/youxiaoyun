package com.topview.school.po;

/**
 * @Description 请假附件po
 */
public class LeaveAccessory {

	private String id;
	private String tScLeaveId; //请假申请id 
	private int type; //附件类型，目前只有图片
	private String url; //附件url

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettScLeaveId() {
		return tScLeaveId;
	}

	public void settScLeaveId(String tScLeaveId) {
		this.tScLeaveId = tScLeaveId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
