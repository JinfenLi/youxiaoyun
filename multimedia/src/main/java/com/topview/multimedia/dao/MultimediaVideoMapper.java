package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaVideo;

/**
 * 视频DAO接口
 * 项目名称:com.topview.multimedia.dao<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public interface MultimediaVideoMapper extends BaseDao<MultimediaVideo>{
	
	/**
	 * 根据视频库id查询视频数量
	 * @param libraryId
	 * @return
	 */
	public int selectCount(String libraryId);
}