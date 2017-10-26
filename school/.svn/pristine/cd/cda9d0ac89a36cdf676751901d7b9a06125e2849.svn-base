package com.topview.school.vo.school;

import com.topview.school.po.GradeResources;
import com.topview.school.po.ResourceType;
import com.topview.school.util.DateFormatUtil;

public class GradeResourcesVO {
	//资源Id
    private String id;
    //资源名称
    private String name;
    //资源路径
    private String resourcepath;
    //年级id
    private String gradeId;
    //资源类型id
    private String resourceTypeId;
    //上传时间
    private String uploadTime;
    //是否链接
    private String isLink;
    //缩略图路径
    private String thumbnailPath;   
    //类型id
    private String typeId;
    //类型名称
    private String typeName;
    //类型对应的学校id
    private String schoolId;
    
    public static GradeResourcesVO changeToVo(GradeResources g,ResourceType type){
    	GradeResourcesVO gv = new GradeResourcesVO();
    	gv.setGradeId(g.getGradeId());
    	gv.setId(g.getId());
    	gv.setName(g.getName());
    	gv.setResourcepath(g.getResourcepath());
    	gv.setUploadTime(DateFormatUtil.formatDateToSeconds(g.getUploadTime()));
    	gv.setIsLink(g.getIsLink()+"");
    	gv.setThumbnailPath(g.getThumbnailPath());
    	gv.setResourceTypeId(g.getResourceTypeId());
    	
    	
    	gv.setTypeId(type.getId());
    	gv.setTypeName(type.getName());
    	gv.setSchoolId(type.getId());
    	return gv;
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

	public String getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getIsLink() {
		return isLink;
	}

	public void setIsLink(String isLink) {
		this.isLink = isLink;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
    	



}
