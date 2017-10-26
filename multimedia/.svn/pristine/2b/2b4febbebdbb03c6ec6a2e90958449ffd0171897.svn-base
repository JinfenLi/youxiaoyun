package com.topview.multimedia.dao;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.Reply;

public interface ReplyMapper extends BaseDao<Reply>{
	
	/**
	 * 根据帖子id和时间点获取评论
	 * @param map
	 * @return
	 */
	public List<Reply> selectByPostIdAndDate(Map<String, Object> map);
	
	/**
	 * 根据帖子id删除所有评论
	 * @param postId
	 * @return
	 */
	public int deleteByPostId(String postId);
	
	/**
	 * 根据帖子id查询评论数
	 * @param postId
	 * @return
	 */
	public int selectCount(String postId);
}