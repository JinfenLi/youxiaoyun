package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.po.MultimediaFolder;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午1:45:50 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public class FolderInfo {
	
	 private String id;

	    //所属多媒体空间id
	    private String tMId;

	    //文件夹名字
	    private String name;
	    
	    //描述
	    private String description;

	    //创建时间
	    private String createTime;

	    //文件总数
	    private Integer fileCount;

	    //更新时间
	    private String updateTime;
        private Pager pager;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public Integer getFileCount() {
			return fileCount;
		}
		public void setFileCount(Integer fileCount) {
			this.fileCount = fileCount;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		public Pager getPager() {
			return pager;
		}
		public void setPager(Pager pager) {
			this.pager = pager;
		}
        
		public static MultimediaFolder changeToPo(FolderInfo info){
			
			MultimediaFolder folder = new MultimediaFolder();
			if(info.getId()!=null){
				folder.setId(info.getId());
			}
			folder.setDescription(info.getDescription());
			folder.setFileCount(info.getFileCount());
			folder.setName(info.getName());
			folder.settMId(info.gettMId());
			return folder;
			
		}
		
		public static List<MultimediaFolder> changeToPo(List<FolderInfo> list){
			List<MultimediaFolder> folder = new ArrayList<MultimediaFolder>();
			if(list.size()>0){
				int size = list.size();
				for(int i=0;i<size;i++){
					folder.add(changeToPo(list.get(i)));
				}
			}
			return folder;
		
		}
		
		public static FolderInfo changeToVo(MultimediaFolder folder){
			FolderInfo info = new FolderInfo();
			if(folder.getId()!=null){
				info.setId(folder.getId());
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			info.setCreateTime(df.format(folder.getCreateTime()));
			info.setDescription(folder.getDescription());
			info.setFileCount(folder.getFileCount());
			info.setName(folder.getName());
			info.settMId(folder.gettMId());
			info.setUpdateTime(df.format(folder.getUpdateTime()));
			return info;
		}
		
		public static List<FolderInfo> changeToVo(List<MultimediaFolder> folder){
			List<FolderInfo> list = new ArrayList<FolderInfo>();
			if(folder.size()>0){
				int size = folder.size();
				for (int i=0;i<size;i++){
					list.add(changeToVo(folder.get(i)));
				}
			}
			return list;
		}
        
}
