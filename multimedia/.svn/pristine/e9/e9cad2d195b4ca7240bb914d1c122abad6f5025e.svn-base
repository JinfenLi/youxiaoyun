package com.topview.multimedia.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.PraiseMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.Praise;

@Repository
public class PraiseMapperImpl extends BaseDaoImpl<Praise> implements PraiseMapper{

	public List<Praise> selectByPostIdAndDate(Map<String, Object> map) {
		return template.selectList(getFirstInterface() + ".selectByPostIdAndDate", map);
	}

	public int deleteByPostId(String postId) {
		return template.delete(getFirstInterface() + ".deleteByPostId", postId);
	}

	public int selectCount(String postId) {
		return template.selectOne(getFirstInterface() + ".selectCount", postId);
	}

	public List<Praise> selectByParam(Map<String, Object> map) {
		return template.selectList(getFirstInterface() + ".selectByParam", map);
	}
}