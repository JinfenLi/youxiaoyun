package com.topview.school.service.clazz.leave.enums;

public enum LeaveStatusEnum {

	NOT_REVIEWED("未审核", 1),
	ALREADY_APPROVE("同意", 2),
	ALREADY_REJECTED("不同意", 3);
	
	private String name;
	private Integer code;
	
	LeaveStatusEnum(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public static LeaveStatusEnum getName(String name){
		
		for(LeaveStatusEnum sEnum : LeaveStatusEnum.values()){
			if(sEnum.getName().equals(name))
				return sEnum;
		}
		
		return null;
	}
	
	public static LeaveStatusEnum getCode(Integer code){
		
		for(LeaveStatusEnum sEnum : LeaveStatusEnum.values()){
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
