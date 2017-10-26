package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaVideoLibrary;
/**
 * 视频库DAO实现
 * 项目名称:com.topview.multimedia.dao.impl<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Repository
public class MultimediaVideoLibraryDaoImpl extends BaseDaoImpl<MultimediaVideoLibrary> implements MultimediaVideoLibraryMapper{

	public int selectCount(String zoneId) {
		return template.selectOne(getFirstInterface() + ".selectCount", zoneId);
	}

}
