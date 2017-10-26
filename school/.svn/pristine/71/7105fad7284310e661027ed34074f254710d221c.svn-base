package com.topview.school.vo.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.topview.school.po.School;

public class SchoolInfo {
	
	private String id;
    private String name;
    private Integer type;
    private String address;
    private String phone;
    private Integer postcode;
    private String email;
    private String website;
    private String logo;
    private Integer areaId; //区域id
    private String areaName; //所属区域名称
    private Map<String, Object> loginCountMap; //学校登录数Map

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Map<String, Object> getLoginCountMap() {
		return loginCountMap;
	}

	public void setLoginCountMap(Map<String, Object> loginCountMap) {
		this.loginCountMap = loginCountMap;
	}

	public static SchoolInfo changeToVo(School school){
    	SchoolInfo info = new SchoolInfo();
    	info.setAddress(school.getAddress());
    	info.setEmail(school.getEmail());
    	info.setId(school.getId());
    	info.setName(school.getName());
    	info.setPhone(school.getPhone());
    	info.setPostcode(school.getPostcode());
    	info.setType(school.getType());
    	info.setWebsite(school.getWebsite());
    	info.setLogo(school.getLogo());
    	info.setAreaId(school.getAreaId());
    	return info;
    }
    public static List<SchoolInfo> changeToVo(List<School> list){
    	List<SchoolInfo> infos = new ArrayList<SchoolInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
    	return infos;
    }
    
    public static School changeToPo(SchoolInfo info){
    	School school = new School();
    	if(info.getId() != null && !"".equals(info.getId())) {
    		school.setId(info.getId());
    	}
    	school.setAddress(info.getAddress());
    	school.setEmail(info.getEmail());
    	school.setName(info.getName());
    	school.setPhone(info.getPhone());
    	school.setPostcode(info.getPostcode());
    	school.setType(info.getType());
    	school.setWebsite(info.getWebsite());
    	school.setLogo(info.getLogo());
    	school.setAreaId(info.getAreaId());
    	return school;
    }
    public static List<School> changeToPo(List<SchoolInfo> list){
    	List<School> infos = new ArrayList<School>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToPo(list.get(i)));
			}
		}
    	return infos;
    }
}
