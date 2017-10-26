package com.topview.school.vo.feedback;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 反馈vo
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 上午11:21:19
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class FeedbackInfo {

	private int id;
	private String phone;
	private String email;
	private String content;
	private String createTime;
	private boolean status;
	private List<String> urls;
	private MultipartFile[] files;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
