package com.topview.school.po;

/**
 * @Description 区域表
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年9月13日 下午1:11:05
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class Area {

	private int id; //主键自增
	private String name; // 区域名称
	private int parentId; //上级区域id
	private String leave; //区域级别：1代表省级; 2代表市级; 3代表区/县级
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	
}
