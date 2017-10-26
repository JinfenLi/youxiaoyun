package com.topview.multimedia.vo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.util.DateFormatUtil;
/**
 * 图片信息
 * 项目名称:com.topview.multimedia.vo<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public class PhotoInfo {
	private String zoneId;
	private String id;
	private String tMId;
	private String name;
	private String description;
	private String comment;
	private String label;
	private Integer status;
	private String videoPath;
	private String collectid;
	private String demoid;
	private String uploadTime;
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}
	

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	private Pager pager;
	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}
	public String getDemoid() {
		return demoid;
	}

	public void setDemoid(String demoid) {
		this.demoid = demoid;
	}


	public static MultimediaPhoto changeToPo(PhotoInfo info) {
		MultimediaPhoto photo = new MultimediaPhoto();
		photo.setComment(info.getComment());
		photo.setDescription(info.getDescription());
		if(info.getId()!=null){
			photo.setId(info.getId());
		}
		photo.setLabel(info.getLabel());
		photo.setName(info.getName());
		photo.setStatus(info.getStatus());
		photo.settMId(info.gettMId());
		photo.setVideoPath(info.getVideoPath());
		photo.setSort(info.getSort());
		if(info.getUploadTime()!=null){
			photo.setUploadTime(DateFormatUtil.parseToSeconds(info.getUploadTime()));
		}
		return photo;
	}

	public static List<MultimediaPhoto> changeToPo(List<PhotoInfo> list) {
		List<MultimediaPhoto> albums = new ArrayList<MultimediaPhoto>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				albums.add(changeToPo(list.get(i)));
			}
		}
		return albums;
	}

	public static PhotoInfo changeToVo(MultimediaPhoto photo) {
		PhotoInfo info = new PhotoInfo();
		info.setComment(photo.getComment());
		info.setDescription(photo.getDescription());
		info.setId(photo.getId());
		info.setLabel(photo.getLabel());
		info.setName(photo.getName());
		info.setStatus(photo.getStatus());
		info.settMId(photo.gettMId());
		info.setVideoPath(photo.getVideoPath());
		info.setSort(photo.getSort());
		if(photo.getUploadTime()!=null){
			info.setUploadTime(DateFormatUtil.formatDateToSeconds(photo.getUploadTime()));
		}
		return info;
	}

	public static List<PhotoInfo> changeToVo(List<MultimediaPhoto> list) {
		List<PhotoInfo> infos = new ArrayList<PhotoInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}
	
	public List<Object> convert2Object(List<PhotoInfo> lines) {

		List<Object> objects = new LinkedList<Object>();
		for (int i = 0; i < lines.size(); i++) {
			objects.add(i, lines.get(i));
		}
		return objects;
	}
	
	
}
