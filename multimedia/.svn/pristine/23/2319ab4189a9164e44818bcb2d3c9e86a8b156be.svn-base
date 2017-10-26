package com.topview.multimedia.service.file.enums;
/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午3:42:54 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public enum FileEnums {
	
	PDF("pdf",0),PPT("ppt",1),DOC("doc",2),XLS("xls",3),DOCX("docx",3),XLSX("xlsx",4),
	JPG("jpg",5),HTML("html",6)
	,TXT("txt",7);
	
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
