package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaVideoLibrary;
/**
 * 视频库信息
 * 项目名称:com.topview.multimedia.vo<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public class LibraryInfo {
	private String id;
	private String tMId;
	private String name2;
	private String comment;
	private String description2;
	private String createTime;
	private Integer photoCount2;
	private Integer type;
	private Pager pager;
	private String coverPath; //封面路径
	
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

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPhotoCount2() {
		return photoCount2;
	}

	public void setPhotoCount2(Integer photoCount2) {
		this.photoCount2 = photoCount2;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public static MultimediaVideoLibrary changeToPo(LibraryInfo info) {
		MultimediaVideoLibrary library = new MultimediaVideoLibrary();
		library.setComment(info.getComment());
		library.setDescription2(info.getDescription2());
		if(info.getId()!=null){
			library.setId(info.getId());
		}
		library.setName2(info.getName2());
		library.setPhotoCount2(info.getPhotoCount2());
		library.settMId(info.gettMId());
		library.setType(info.getType());
		return library;
	}

	public static List<MultimediaVideoLibrary> changeToPo(List<LibraryInfo> list) {
		List<MultimediaVideoLibrary> albums = new ArrayList<MultimediaVideoLibrary>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				albums.add(changeToPo(list.get(i)));
			}
		}
		return albums;
	}

	public static LibraryInfo changeToVo(MultimediaVideoLibrary library) {
		LibraryInfo info = new LibraryInfo();
		info.setComment(library.getComment());
		info.setDescription2(library.getDescription2());
		info.setId(library.getId());
		info.setName2(library.getName2());
		info.setPhotoCount2(library.getPhotoCount2());
		info.settMId(library.gettMId());
		info.setType(library.getType());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setCreateTime(df.format(library.getCreateTime()));
		return info;
	}

	public static List<LibraryInfo> changeToVo(List<MultimediaVideoLibrary> list) {
		List<LibraryInfo> infos = new ArrayList<LibraryInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}
}
