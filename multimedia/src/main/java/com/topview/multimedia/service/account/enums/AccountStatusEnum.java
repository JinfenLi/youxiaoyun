package com.topview.multimedia.service.account.enums;

public enum AccountStatusEnum {

	ALREADY_REGISTER("已注册", 0),
	ALREADY_OVERDUE("已过期", -1);
	
	private String name;
	private Integer code;
	
	AccountStatusEnum(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public AccountStatusEnum getName(String name){
		
		for(AccountStatusEnum sEnum : AccountStatusEnum.values()){
			if(sEnum.getName().equals(name))
				return sEnum;
		}
		
		return null;
	}
	
	public AccountStatusEnum getCode(Integer code){
		
		for(AccountStatusEnum sEnum : AccountStatusEnum.values()){
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
