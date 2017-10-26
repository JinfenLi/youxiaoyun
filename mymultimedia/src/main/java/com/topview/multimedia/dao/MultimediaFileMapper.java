package com.topview.multimedia.dao;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaFile;

public interface MultimediaFileMapper extends BaseDao<MultimediaFile>{
	
public boolean deleteByTmid(String tMId);
	
	/**
	 * 根据文件夹id查询文件夹中的文件总数
	 * @param folderId
	 * @return
	 */
	public int selectCount(String folderId);
	
	/**
	 * 获取所有可视文件
	 * @param ids
	 * @return
	 */
	public List<MultimediaFile> findIn(List<String> ids,int offset, int limit);
	
	
    
}