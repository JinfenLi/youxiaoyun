package com.topview.multimedia.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaVideo;
/**
 * 视频信息
 * 项目名称:com.topview.multimedia.vo<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public class VideoInfo {
	private String zoneId;
	private String id;
	private String tMId;
	private String name;
	private String description;
	private String comment;
	private String label;
	private Integer status;
	private String videoPath;
	private BigDecimal size;
	private String format;
	private Pager pager;
	private String collectid;
	
	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

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

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public static MultimediaVideo changeToPo(VideoInfo info) {
		MultimediaVideo video = new MultimediaVideo();
		video.setComment(info.getComment());
		video.setDescription(info.getDescription());
		video.setFormat(info.getFormat());
		if(info.getId()!=null){
			video.setId(info.getId());
		}
		video.setLabel(info.getLabel());
		video.setName(info.getName());
		video.setSize(info.getSize());
		video.setStatus(info.getStatus());
		video.settMId(info.gettMId());
		video.setVideoPath(info.getVideoPath());
		return video;
	}

	public static List<MultimediaVideo> changeToPo(List<VideoInfo> list) {
		List<MultimediaVideo> video = new ArrayList<MultimediaVideo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				video.add(changeToPo(list.get(i)));
			}
		}
		return video;
	}

	public static VideoInfo changeToVo(MultimediaVideo video) {
		VideoInfo info = new VideoInfo();
		info.setComment(video.getComment());
		info.setDescription(video.getDescription());
		info.setFormat(video.getFormat());
		info.setId(video.getId());
		info.setLabel(video.getLabel());
		info.setName(video.getName());
		info.setSize(video.getSize());
		info.setStatus(video.getStatus());
		info.settMId(video.gettMId());
		info.setVideoPath(video.getVideoPath());
		return info;
	}

	public static List<VideoInfo> changeToVo(List<MultimediaVideo> list) {
		List<VideoInfo> info = new ArrayList<VideoInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				info.add(changeToVo(list.get(i)));
			}
		}
		return info;
	}
	
	public List<Object> convert2Object(List<VideoInfo> lines) {

		List<Object> objects = new LinkedList<Object>();
		for (int i = 0; i < lines.size(); i++) {
			objects.add(i, lines.get(i));
		}
		return objects;
	}
	
}
