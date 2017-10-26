package com.topview.multimedia.vo;

import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.po.Horn;
import com.topview.multimedia.util.DateFormatUtil;

public class HornInfo {
	private String id;
	//小喇叭内容
	private String message;
	//小喇叭开始时间
	private String beginTime;
	//小喇叭结束时间
	private String endTime;
	//接受者ids
	private List<String> userIds;
	//0代表不再使用，1代表正在使用
	private Integer flag;
	//发送小喇叭用户id
	private String sendId;
	//发送小喇叭的用户名字
	private String senderName;
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public static Horn changeToPo(HornInfo hornInfo) {
		Horn horn = new Horn();
		horn.setId(hornInfo.getId());
		horn.setSendId(hornInfo.getSendId());
		horn.setMessage(hornInfo.getMessage());
		horn.setFlag(hornInfo.getFlag());
		horn.setBeginTime(DateFormatUtil.parseToDay(hornInfo.getBeginTime()));
		horn.setEndTime(DateFormatUtil.parseToDay(hornInfo.getEndTime()));
		return horn;
	}
	
	public static List<Horn> changeToPo(List<HornInfo> hornInfoList) {
		List<Horn> list = new ArrayList<>();
		for(HornInfo hornInfo : hornInfoList) {
			list.add(changeToPo(hornInfo));
		}
		return list;
	}
	
	public static HornInfo changeToVo(Horn horn) {
		HornInfo hornInfo = new HornInfo();
		hornInfo.setSendId(horn.getSendId());
		hornInfo.setBeginTime(DateFormatUtil.formatDateToDay(horn.getBeginTime()));
		hornInfo.setEndTime(DateFormatUtil.formatDateToDay(horn.getEndTime()));
		hornInfo.setFlag(horn.getFlag());
		hornInfo.setMessage(horn.getMessage());
		hornInfo.setId(horn.getId());
		return hornInfo;
	}
	
	public static List<HornInfo> changToVo(List<Horn> hornList) {
		List<HornInfo> list = new ArrayList<>();
		for(Horn horn : hornList) {
			list.add(changeToVo(horn));
		}
		return list;
	}
}
