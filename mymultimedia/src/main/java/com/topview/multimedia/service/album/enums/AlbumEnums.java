package com.topview.multimedia.service.album.enums;


public enum AlbumEnums {
	//ViewPager类型是首页轮播的图片
	VIEW_PAGER("ViewPager",0),SIMPLE("普通",1), NOPASS("未审核", 2);
	
	private String name;
	private Integer code;
	
	AlbumEnums(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public AlbumEnums getName(String name){
		
		for(AlbumEnums sEnum : AlbumEnums.values()){
			if(sEnum.getName().equals(name))
				return sEnum;
		}
		
		return null;
	}
	
	public AlbumEnums getCode(Integer code){
		
		for(AlbumEnums sEnum : AlbumEnums.values()){
			if(sEnum.getCode() == code)
				return sEnum;
		}
		
		return null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
