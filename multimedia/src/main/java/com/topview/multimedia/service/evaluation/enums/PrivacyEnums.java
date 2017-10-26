package com.topview.multimedia.service.evaluation.enums;
/** * @author  Rachel 
@date 创建时间：2016年11月9日 下午11:04:07 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public enum PrivacyEnums {
	
	GOLOBAL("global",0),PUBLIC("public",1),PRIVACY("private",2);
	
	private String name;
	private Integer code;
	PrivacyEnums(String name, Integer code){
		this.name = name;
		this.code = code;
	}
	
	public PrivacyEnums getName(String name){
		
		for(PrivacyEnums fe : PrivacyEnums.values()){
			if(fe.getName().equals(name)){
				return fe;
			}
		}
		return null;
	}
	
	public PrivacyEnums getCode(Integer code){
		
		for(PrivacyEnums fe: PrivacyEnums.values()){
			if(fe.getCode() == code){
				return fe;
			}
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
