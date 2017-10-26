package com.topview.multimedia.service.folder.file;

import com.topview.multimedia.vo.FileInfo;
import com.topview.multimedia.vo.result.FileInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午1:42:19 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
public interface FileService {
	
	/**
	 * 保存文件
	 * @param file
	 * @return
	 */
	public FileInfoResult fileSave(FileInfo file);
	
	/**
	 * 删除文件
	 * @param file
	 * @return
	 */
	public FileInfoResult fileDelete(FileInfo file);
	
	/**
	 * 根据文件夹id查询文件
	 * @param file
	 * @return
	 */
	public FileInfoResult fileFindAll(FileInfo file);
	
	/**
	 * 根据文件id产看文件
	 * @param file
	 * @return
	 */
	
	public FileInfoResult fileFind(FileInfo file);
	
	/**
	 * 判断文件主人
	 * @param file
	 * @return
	 */
	public FileInfoResult fileJudgeOwner(FileInfo file);
	
	/**
	 * 查看某个文件夹中文件数量
	 * @param folderId
	 * @return
	 */
	public int selectCount(String folderId);

}
