package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaPhoto;

/**
 * 照片DAO接口 项目名称:com.topview.multimedia.dao<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
public interface MultimediaPhotoMapper extends BaseDao<MultimediaPhoto> {

	public boolean deleteByTmid(String tMId);
	
	/**
	 * 根据相册id查询相册中已通过审核图片数量
	 * @param albumId
	 * @return
	 */
	public int selectCount(String albumId);
}