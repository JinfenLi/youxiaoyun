package com.topview.school.vo.version;

import com.topview.school.po.VersionUpdate;
import com.topview.school.util.DateFormatUtil;

public class VersionInfo {

	private int id;
	private String version; // 版本号
	private String description; // 描述
	private String comment; // 备注信息，预留字段
	private String detail; // 更新细节
	private String url; // 版本下载地址
	private String updateTime; //更新时间
	private String device; //所属设备：1为安卓，2为IOS
	private String schoolId; //所属学校id
	private String schoolName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public static VersionInfo changeToVo(VersionUpdate version) {
		VersionInfo info = new VersionInfo();
		info.setComment(version.getComment());
		info.setDescription(version.getDescription());
		info.setDetail(version.getDetail());
		info.setUpdateTime(DateFormatUtil.formatDateToSeconds(version.getUpdateTime()));
		info.setUrl(version.getUrl());
		info.setId(version.getId());
		info.setVersion(version.getVersion());
		return info;
		
	}
	
}
