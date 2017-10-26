package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.service.file.enums.FileEnums;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午1:45:10 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class FileInfo {
	
	
	
    private String id;
    
    //所属文件夹的id
    private String tMId;

    //名称
    private String name;

    //上传时间
    private String uploadTime;
    
    //大小
    private String size;

    //上传者
    private String uploader;
     //上传者ID
    private String uploaderId;

    //类型
    private String type;
    
    //文件路径
    private String filePath;
   

    private Pager pager;

	
    
    public static MultimediaFile changeToPo(FileInfo fileInfo){
    	MultimediaFile file = new MultimediaFile();
    	if(fileInfo.getId()!=null){
    		file.setId(fileInfo.getId());
    	}
    	file.setFilePath(fileInfo.getFilePath());
    	file.setName(fileInfo.getName());
    	file.setSize(fileInfo.getSize());
    	file.settMId(fileInfo.gettMId());
    	file.setUploader(fileInfo.getUploader());
    	file.setUploaderId(fileInfo.getUploaderId());
    	
    	FileEnums f =null;
    	for(FileEnums fe: FileEnums.values()){
			if(fe.getName().equals(fileInfo.getType())){
				f=fe;
				break;
			}
    	}
    	if(f!=null){
    		file.setType(f.getCode());
    	}else{
    		file.setType(-1);
    	}
    	
    	return file;
    }
    
    public static List<MultimediaFile> changeToPo(List<FileInfo> list){
    	
    	List<MultimediaFile> folder = new ArrayList<MultimediaFile>();
    	
    	if(list.size()>0){
    		int size =list.size();
    		for(int i=0;i<size;i++){
    			folder.add(changeToPo(list.get(i)));
    		}
    	}
    	return folder;
    	
    }
    
    public static FileInfo changeToVo(MultimediaFile file){
    	FileInfo info = new FileInfo();
    	info.setId(file.getId());
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	info.setName(file.getName());
    	info.setSize(file.getSize());
    	info.settMId(file.gettMId());
    	info.setUploader(file.getUploader());
    	info.setUploaderId(file.getUploaderId());
    	info.setUploadTime(df.format(file.getUploadTime()));
    	FileEnums f =null;
    	for(FileEnums fe: FileEnums.values()){
			if(fe.getCode() == file.getType()){
				f=fe;
			}
    	}
    	if(f!=null){
    		info.setType(f.getName());
    	}else{
    		info.setType("无此类型");
    	}
    	
    	info.setFilePath(file.getFilePath());
    	return info;
    }
    
    public static List<FileInfo> changeToVo(List<MultimediaFile> file){
    	List<FileInfo> list = new ArrayList<FileInfo>();
    	if(file.size()>0){
    		int size = file.size();
    		for(int i=0;i<size;i++){
    			list.add(changeToVo(file.get(i)));
    		}
    	}
    	return list;
    	
    }



	



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String gettMId() {
		return tMId;
	}



	public void settMId(String tMId) {
		this.tMId = tMId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public String getUploader() {
		return uploader;
	}



	public void setUploader(String uploader) {
		this.uploader = uploader;
	}



	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public Pager getPager() {
		return pager;
	}



	public void setPager(Pager pager) {
		this.pager = pager;
	}

	

	
	

}
