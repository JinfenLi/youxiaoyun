package com.topview.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.message.dao.BaiduPushMapper;
import com.topview.message.dao.base.impl.BaseDaoImpl;
import com.topview.message.po.BaiduPush;

/**
 * @author zjd
 * 百度云推送基础接口
 */
@Repository("baiduPushDao")
public class BaiduPushMapperImpl extends BaseDaoImpl<BaiduPush> implements BaiduPushMapper{

	public List<BaiduPush> selectByUserId(String userId) {
		return template.selectList(getStatement("selectByUserId"), userId);
	}

	public List<BaiduPush> selectByChannelId(String channelId) {
		return template.selectList(getStatement("selectByChannelId"), channelId);
	}

}
