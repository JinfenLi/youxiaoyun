package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaVideoLibrary;

/**
 * 视频库DAO接口
 * 项目名称:com.topview.multimedia.dao<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public interface MultimediaVideoLibraryMapper extends BaseDao<MultimediaVideoLibrary>{
	
	/**
	 * 根据多媒体空间查询视频库数量
	 * @param zoneId
	 * @return
	 */
	public int selectCount(String zoneId);
}