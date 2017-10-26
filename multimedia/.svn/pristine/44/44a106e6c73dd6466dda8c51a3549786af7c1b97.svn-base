package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaSection;
/**
 * 文章分类信息
 * 项目名称:com.topview.multimedia.vo<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public class SectionInfo {
	private String id;
	private String tMId;
	private String createTime;
	private String name2;
	private String description2;
	private String icon;
	private String type;
	private Pager pager;
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettMId() {
		return tMId;
	}

	public void settMId(String tMId) {
		this.tMId = tMId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static MultimediaSection changeToPo(SectionInfo info) {
		MultimediaSection section = new MultimediaSection();
		section.setDescription2(info.getDescription2());
		section.setIcon(info.getIcon());
		section.setId(info.getId());
		section.setName2(info.getName2());
		section.settMId(info.gettMId());
		section.setType(info.getType());
		return section;
	}

	public static List<MultimediaSection> changeToPo(List<SectionInfo> list) {
		List<MultimediaSection> section = new ArrayList<MultimediaSection>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				section.add(changeToPo(list.get(i)));
			}
		}
		return section;
	}

	public static SectionInfo changeToVo(MultimediaSection section) {
		SectionInfo info = new SectionInfo();
		info.setDescription2(section.getDescription2());
		info.setIcon(section.getIcon());
		info.setId(section.getId());
		info.setName2(section.getName2());
		info.settMId(section.gettMId());
		info.setType(section.getType());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setCreateTime(df.format(section.getCreateTime()));
		return info;
	}

	public static List<SectionInfo> changeToVo(List<MultimediaSection> list) {
		List<SectionInfo> info = new ArrayList<SectionInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				info.add(changeToVo(list.get(i)));
			}
		}
		return info;
	}
}
