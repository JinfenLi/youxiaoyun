package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.po.MultimediaZone;
/**
 * 账号信息
 * 项目名称:com.topview.multimedia.vo<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public class AccountInfo {
	private String id;
	private String comment;
	private Integer accountStatus;
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static MultimediaZone changeToPo(AccountInfo accountInfo) {
		MultimediaZone zone = new MultimediaZone();
		zone.setAccountStatus(accountInfo.getAccountStatus());
		zone.setComment(accountInfo.getComment());
		if(accountInfo.getId()!=null){
			zone.setId(accountInfo.getId());
		}
		return zone;
	}

	public static List<MultimediaZone> changeToPo(List<AccountInfo> list) {
		List<MultimediaZone> zones = new ArrayList<MultimediaZone>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				zones.add(changeToPo(list.get(i)));
			}
		}
		return zones;
	}

	public static AccountInfo changeToVo(MultimediaZone zone) {
		AccountInfo info = new AccountInfo();
		info.setAccountStatus(zone.getAccountStatus());
		info.setComment(zone.getComment());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setCreateTime(df.format(zone.getCreateTime()));
		info.setId(zone.getId());
		return info;
	}

	public static List<AccountInfo> changeToVo(List<MultimediaZone> list) {
		List<AccountInfo> infos = new ArrayList<AccountInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}
}
