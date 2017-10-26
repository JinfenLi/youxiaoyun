package com.topview.multimedia.po;

import java.util.Date;

/**
 * @Description 更新记录表
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年11月10日 下午1:16:54
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class RecordUpdate {

	private String id;
	private String tMId; // 所属学校id/年级id/班级id
	private Date lastUpdate; // 最后更新时间
	private String module; // 所属模块

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
