package com.topview.school.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 年级资源类
* @ClassName: GradeResources 
* @author cx  <747184616@qq.com>
* @date 2015年8月13日 下午3:39:23 
* @version V1.0
 */
public class GradeResources {
	//资源Id
    private String id;
    @Size(max=127)
    @NotNull
    //资源名称
    private String name;
    @Size(max=255)
    //资源路径
    private String resourcepath;
    @NotNull
    //年级id
    private String gradeId;
    @NotNull
    //资源对应的资源类型id
    private String resourceTypeId;   
    //缩略图路径
    private String thumbnailPath;  
	//上传时间
    private Date uploadTime;
    //是否链接
    private Boolean isLink;

    public String getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourcepath() {
        return resourcepath;
    }

    public void setResourcepath(String resourcepath) {
        this.resourcepath = resourcepath;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Boolean getIsLink() {
		return isLink;
	}

	public void setIsLink(Boolean isLink) {
		this.isLink = isLink;
	}

	@Override
	public String toString() {
		return "GradeResources [id=" + id + ", name=" + name
				+ ", resourcepath=" + resourcepath + ", gradeId=" + gradeId
				+ ", resourceTypeId=" + resourceTypeId + ", thumbnailPath="
				+ thumbnailPath + ", uploadTime=" + uploadTime + ", isLink="
				+ isLink + "]";
	}

	
	
	
}