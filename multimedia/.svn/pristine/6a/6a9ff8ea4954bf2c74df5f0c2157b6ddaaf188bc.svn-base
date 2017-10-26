package com.topview.multimedia.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.HornMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.Horn;

@Repository
public class HornMapperDaoImpl extends BaseDaoImpl<Horn> implements HornMapper {

	@Override
	public List<Horn> getHornByUserId(String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("flag", 1);
		return template.selectList(getFirstInterface() + ".getHornByUserId", params);
	}

	@Override
	public List<Horn> getHornBySenderId(String senderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("senderId", senderId);
		return template.selectList(getFirstInterface() + ".getHornBySenderId", params);
	}

}
