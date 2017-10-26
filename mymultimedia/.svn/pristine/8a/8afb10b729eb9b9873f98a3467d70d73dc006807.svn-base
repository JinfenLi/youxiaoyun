package com.topview.multimedia.service.folder.file.enums;
/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午3:42:54 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public enum FileEnums {
	
	RAR("rar",0),PPT("ppt",1),DOC("doc",2),XLS("xls",3),PDF("pdf",4),HTML("html",5)
	,TXT("txt",6);
	
	private String name;
	private Integer code;
	FileEnums(String name, Integer code){
		this.name = name;
		this.code = code;
	}
	
	public FileEnums getName(String name){
		
		for(FileEnums fe : FileEnums.values()){
			if(fe.getName().equals(name)){
				return fe;
			}
		}
		return null;
	}
	
	public FileEnums getCode(Integer code){
		
		for(FileEnums fe: FileEnums.values()){
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
