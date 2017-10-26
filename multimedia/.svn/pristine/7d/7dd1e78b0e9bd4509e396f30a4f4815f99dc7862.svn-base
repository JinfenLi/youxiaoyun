package com.topview.multimedia.service.evaluation.enums;


/** * @author  Rachel 
@date 创建时间：2016年9月21日 下午10:02:47 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public enum EvaluationTemplateEnums {
	
	PASS("pass",0),UNPASS("unpass",1),CLOSE("close",2),UPDATENOPASS("updatenopass",3);
	
	private String name;
	private Integer code;
	EvaluationTemplateEnums(String name, Integer code){
		this.name = name;
		this.code = code;
	}
	
	public EvaluationTemplateEnums getName(String name){
		
		for(EvaluationTemplateEnums fe : EvaluationTemplateEnums.values()){
			if(fe.getName().equals(name)){
				return fe;
			}
		}
		return null;
	}
	
	public EvaluationTemplateEnums getCode(Integer code){
		
		for(EvaluationTemplateEnums fe: EvaluationTemplateEnums.values()){
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
