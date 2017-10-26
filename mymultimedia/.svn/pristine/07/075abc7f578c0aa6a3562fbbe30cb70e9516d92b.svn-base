package com.topview.multimedia.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.PostMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.Post;

@Repository
public class PostMapperImpl extends BaseDaoImpl<Post> implements PostMapper{

	public List<Post> selectByClazzIdAndDate(Map<String, Object> map) {
		return template.selectList(getFirstInterface() + ".selectByClazzIdAndDate", map);
	}
}