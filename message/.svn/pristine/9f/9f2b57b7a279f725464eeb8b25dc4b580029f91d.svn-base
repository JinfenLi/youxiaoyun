package com.topview.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.message.dao.PushKeyMapper;
import com.topview.message.dao.base.impl.BaseDaoImpl;
import com.topview.message.po.PushKey;

@Repository("PushKeyDao")
public class PushKeyMaperImpl extends BaseDaoImpl<PushKey> implements PushKeyMapper{

	@Override
	public PushKey selectByApikey(String apikey) {
		return template.selectOne(getStatement("selectByApikey"), apikey);
	}
	
}
