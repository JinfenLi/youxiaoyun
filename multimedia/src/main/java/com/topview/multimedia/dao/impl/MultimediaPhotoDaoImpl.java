package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaPhoto;

/**
 * 照片DAO实现 项目名称:com.topview.multimedia.dao.impl<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Repository
public class MultimediaPhotoDaoImpl extends BaseDaoImpl<MultimediaPhoto>
		implements MultimediaPhotoMapper {
	public boolean deleteByTmid(String tMId) {
		return template.delete(getFirstInterface() + ".deleteByTmid", tMId) > 0 ? true : false;
	}

	public int selectCount(String albumId) {
		return template.selectOne(getFirstInterface() + ".selectCount", albumId);
	}
}
