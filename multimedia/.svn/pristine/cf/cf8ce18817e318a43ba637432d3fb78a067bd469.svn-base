package com.topview.multimedia.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.Reply;

@Repository
public class ReplyMapperImpl extends BaseDaoImpl<Reply> implements ReplyMapper{
	
	public List<Reply> selectByPostIdAndDate(Map<String, Object> map) {
		return template.selectList(getFirstInterface() + ".selectByPostIdAndDate", map);
	}

	public int deleteByPostId(String postId) {
		return template.delete(getFirstInterface() + ".deleteByPostId", postId);
	}

	public int selectCount(String postId) {
		return template.selectOne(getFirstInterface() + ".selectCount", postId);
	}
}