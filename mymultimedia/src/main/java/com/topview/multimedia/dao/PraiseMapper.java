package com.topview.multimedia.dao;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.Praise;

public interface PraiseMapper extends BaseDao<Praise>{
	
	/**
	 * 根据帖子id和时间查询点赞
	 * @param map
	 * @return
	 */
	public List<Praise> selectByPostIdAndDate(Map<String, Object> map);
	
	/**
	 * 根据帖子id删除点赞列表
	 * @param postId
	 * @return
	 */
	public int deleteByPostId(String postId);
	
	/**
	 *根据帖子id查询点赞数
	 * @param postId
	 * @return
	 */
	public int selectCount(String postId);
	
	/**
	 * 根据点赞者id和帖子id查询点赞记录
	 * @param map
	 * @return
	 */
	public List<Praise> selectByParam(Map<String, Object> map);
}