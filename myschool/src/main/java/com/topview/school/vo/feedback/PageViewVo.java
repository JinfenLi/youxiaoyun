package com.topview.school.vo.feedback;

import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.PageView;
import com.topview.school.util.DateFormatUtil;

/**
 * @Description 页面访问记录VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年10月4日 下午2:39:41
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PageViewVo {

	private String id;
	private String ip; 
	private String accessTime; //访问时间
	private String url; //访问URL
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static PageViewVo changeToVo(PageView pageView) {
		PageViewVo vo = new PageViewVo();
		vo.setId(pageView.getId());
		vo.setIp(pageView.getIp());
		vo.setAccessTime(DateFormatUtil.formatDateToSeconds(pageView.getAccessTime()));
		vo.setUrl(pageView.getUrl());
		return vo;
	}
	
	public static List<PageViewVo> changeToVo(List<PageView> pageViews) {
		List<PageViewVo> pageViewVos = new ArrayList<PageViewVo>();
		for(PageView pv : pageViews) {
			pageViewVos.add(changeToVo(pv));
		}
		return pageViewVos;
	}
}
