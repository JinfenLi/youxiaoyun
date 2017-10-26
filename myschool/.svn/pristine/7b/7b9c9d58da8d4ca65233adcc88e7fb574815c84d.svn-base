package com.topview.school.vo.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.topview.school.po.Parent;

public class ParentVo {
	
	private String id;
    private String tScStudentId;
    private String name;
    private String sex;
    private String parenttype;
    private String mobile;
    private String idcard;
    private Date createTime;
    private Integer type;
    private String password;
    private String superPassword;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String gettScStudentId() {
		return tScStudentId;
	}
	public void settScStudentId(String tScStudentId) {
		this.tScStudentId = tScStudentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getparenttype() {
		return parenttype;
	}
	public void setparenttype(String parenttype) {
		this.parenttype = parenttype;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSuperPassword() {
		return superPassword;
	}
	public void setSuperPassword(String superPassword) {
		this.superPassword = superPassword;
	}
	
	public String getParenttype() {
		return parenttype;
	}
	public void setParenttype(String parenttype) {
		this.parenttype = parenttype;
	}
	public static ParentVo changeToVo(Parent parent){
		ParentVo info = new ParentVo();
    	info.setCreateTime(parent.getCreateTime());
    	info.setId(parent.getId());
    	info.setIdcard(parent.getIdcard());
    	info.setMobile(parent.getMobile());
    	info.setName(parent.getName());
    	info.setparenttype(parent.getParenttype());
    	info.setPassword(parent.getPassword());
    	info.setSex(parent.getSex());
    	info.setSuperPassword(parent.getSuperPassword());
    	info.setType(parent.getType());
    	return info;
    }
    public static List<ParentVo> changeToVo(List<Parent> list){
    	List<ParentVo> infos = new ArrayList<ParentVo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
    	return infos;
    }
}
