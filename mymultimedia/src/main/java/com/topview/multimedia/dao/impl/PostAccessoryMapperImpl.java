package com.topview.multimedia.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.PostAccessoryMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.PostAccessory;

@Repository
public class PostAccessoryMapperImpl extends BaseDaoImpl<PostAccessory> implements PostAccessoryMapper{

	public List<PostAccessory> selectByPostId(String postId) {
		return template.selectList(getFirstInterface() + ".selectByPostId", postId);
	}

	public int deleteByPostId(String postId) {
		return template.delete(getFirstInterface() + ".deleteByPostId", postId);
	}
}