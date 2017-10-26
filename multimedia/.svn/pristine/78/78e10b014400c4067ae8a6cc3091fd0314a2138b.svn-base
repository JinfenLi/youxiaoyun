package com.topview.multimedia.service.section.article;

import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

public interface ArticleService {
	
	/**
	 * 删除文章（若有用户收藏了该文章会先删除用户的收藏记录）
	 * @param info : id
	 * @return
	 */
	public RichTextInfoResult articleDelete(RichTextInfo info);

	public RichTextInfoResult articleUpdate(RichTextInfo info);

	/**
	 * 根据文章id查询单条文章
	 * @param info : id
	 * @return
	 */
	public RichTextInfoResult articleFind(RichTextInfo info);

	/**
	 * 保存文章
	 * @param info
	 * @return
	 */
	public RichTextInfoResult articleSend(RichTextInfo info);

	/**
	 * 根据文章板块id分页查询文章(pager不能为)
	 * @param info ： tMId; 分页参数：pager
	 * @return
	 */
	public RichTextInfoResult articleFindAll(RichTextInfo info);
	
	/**
	 * 根据多媒体空间id和新闻类型查询新闻条数
	 * @param zoneId
	 * @param type
	 * @return
	 */
	public int selectCountByZoneIdAndType(String zoneId, String type);
	
	/**
	 * @dateTime 2016年8月4日下午4:22:25
	 * @author zjd
	 * @description 根据文章id返回该文章的点赞数
	 */
	public int getReadNumber(String title, String context);
}
