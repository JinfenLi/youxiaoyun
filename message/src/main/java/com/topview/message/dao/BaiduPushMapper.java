package com.topview.message.dao;

import java.util.List;

import com.topview.message.dao.base.BaseDao;
import com.topview.message.po.BaiduPush;

public interface BaiduPushMapper extends BaseDao<BaiduPush>{
	List<BaiduPush> selectByUserId(String userId);
	List<BaiduPush> selectByChannelId(String channelId);
}