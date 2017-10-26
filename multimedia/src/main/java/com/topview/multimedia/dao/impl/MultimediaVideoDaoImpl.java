package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaVideo;
/**
 * 视频DAO实现
 * 项目名称:com.topview.multimedia.dao.impl<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Repository
public class MultimediaVideoDaoImpl extends BaseDaoImpl<MultimediaVideo> implements MultimediaVideoMapper{

	public int selectCount(String libraryId) {
		return template.selectOne(getFirstInterface() + ".selectCount", libraryId);
	}

}
