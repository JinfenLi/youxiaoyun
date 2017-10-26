/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:41:31 
 * @version V1.0
 */
package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaCollect;

/** 
 * @ClassName: CollectInfo 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:41:31 
 *  
 */
public class CollectInfo {
	private String id;
	private String pointtype;
	private String pointid;
	private String userid;

	private String createtime;
	private Pager pager;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPointtype() {
		return pointtype;
	}
	public void setPointtype(String pointtype) {
		this.pointtype = pointtype;
	}
	public String getPointid() {
		return pointid;
	}
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


	
	public static CollectInfo changeToVo(MultimediaCollect collect) {
		CollectInfo collectInfo = new CollectInfo();
		collectInfo.setId(collect.getId());
		collectInfo.setPointid(collect.getPointid());
		collectInfo.setUserid(collect.getUserid());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		collectInfo.setCreatetime(df.format(collect.getCreattime()));
		collectInfo.setPointtype(collect.getPointtype());
		return collectInfo;

	}
	
	public static List<CollectInfo> changeToVo(List<MultimediaCollect> list) {
		
		List<CollectInfo> infos = new ArrayList<CollectInfo>();
		if(list.size()>0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}

		return infos;
	}
	
	public static MultimediaCollect changeToPo(CollectInfo collectInfo){
		MultimediaCollect collect = new MultimediaCollect();
		collect.setId(collectInfo.getId());
		collect.setPointid(collectInfo.getPointid());
		collect.setPointtype(collectInfo.getPointtype());
		collect.setUserid(collectInfo.getUserid());
		return collect;
		
	}
	
	public static List<MultimediaCollect> changeToPo(List<CollectInfo> list){
		List<MultimediaCollect> collect = new ArrayList<MultimediaCollect>();
		if(list.size()>0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				collect.add(changeToPo(list.get(i)));
			}
		}
		return collect;
		
	}
	public List<Object> convert2Object(List<CollectInfo> lines) {

		List<Object> objects = new LinkedList<Object>();
		for (int i = 0; i < lines.size(); i++) {
			objects.add(i, lines.get(i));
		}
		return objects;
	
}
}