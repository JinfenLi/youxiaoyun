package com.topview.school.vo;

/**
 * @Description 文件上传VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月9日 下午2:02:56
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class FileUploadVo {

	private String realPath; // 文件保存的绝对路径,不包括文件名在内
	private String relativePath; // 相对路径，包括文件名
	private String fileName; // 文件名

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
