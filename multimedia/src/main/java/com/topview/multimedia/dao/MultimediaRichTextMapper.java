package com.topview.multimedia.dao;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaRichText;
/**
 * 文章DAO接口
 * 项目名称:com.topview.multimedia.dao<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
public interface MultimediaRichTextMapper extends BaseDao<MultimediaRichText>{
	
	/**
	 * 根据多媒体空间和新闻类型查询新闻条数
	 * @param zoneId
	 * @param type
	 * @return
	 */
	public int selectCountByZoneIdAndType(String zoneId, String type);
	
	/**
	 * @dateTime 2016年8月4日下午4:24:13
	 * @author zjd
	 * @description 根据文章id返回该文章的点赞数
	 */
	public int getReadNumber(String title, String context);
}