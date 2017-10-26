package com.topview.school.po;

import java.util.Date;

/**
 * @Description 用户反馈表
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 上午10:49:12
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class Feedback {

	private int id;
	private String phone; // 联系电话
	private String email;
	private String content; // 反馈内容
	private Date createTime; // 反馈时间
	private boolean status; // 是否已处理

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
