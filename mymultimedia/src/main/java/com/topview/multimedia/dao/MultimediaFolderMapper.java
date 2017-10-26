package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaFolder;

public interface MultimediaFolderMapper extends BaseDao<MultimediaFolder>{
   
	
	public int selectCount(String tmId);
}