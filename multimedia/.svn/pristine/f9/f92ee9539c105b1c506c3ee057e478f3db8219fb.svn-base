package com.topview.multimedia.dao;

import java.util.List;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.PostAccessory;

public interface PostAccessoryMapper extends BaseDao<PostAccessory> {
	
	/**
	 * 根据主贴id查询附件
	 * @param postId
	 * @return
	 */
	public List<PostAccessory> selectByPostId(String postId);
	
	/**
	 * 根据主贴id删除附件
	 * @param postId
	 * @return
	 */
	public int deleteByPostId(String postId);
}