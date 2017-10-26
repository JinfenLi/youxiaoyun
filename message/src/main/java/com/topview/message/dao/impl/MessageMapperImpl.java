package com.topview.message.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.message.dao.MessageMapper;
import com.topview.message.dao.base.impl.BaseDaoImpl;
import com.topview.message.po.Message;
import com.topview.message.vo.OfflineMessageVo;

/**
 * @Description Message（消息）基础dao接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午11:28:41
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
@Repository("messageDao")
public class MessageMapperImpl extends BaseDaoImpl<Message> implements MessageMapper{

	public List<OfflineMessageVo> getOfflineMessages(String receiverId) {
		return template.selectList(getStatement("getOfflineMessages"), receiverId);
	}

	public List<Message> getHistoryMessage(String senderId, String beginTime, String endTime, int limit, int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("senderId", senderId);
		params.put("limit", limit);
		params.put("offset", offset);
		return template.selectList(getStatement("getHistoryMessage"), params);
	}

	@Override
	public int getHisoryMessageCounut(String senderId, String beginTime, String endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("senderId", senderId);
		return template.selectOne(getStatement("getHisoryMessageCount"), params);
	}

	@Override
	public OfflineMessageVo getMessageById(String messageId) {
		return template.selectOne(getStatement("getMessageById"),messageId);
	}
}