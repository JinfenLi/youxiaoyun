package com.topview.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.message.dao.EnvelopeMapper;
import com.topview.message.dao.base.impl.BaseDaoImpl;
import com.topview.message.po.Envelope;

/**
 * @Description 消息发送情况基础Dao接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午11:29:57
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
@Repository("envelopeDao")
public class EnvelopeMapperImpl extends BaseDaoImpl<Envelope> implements EnvelopeMapper{

	public List<Envelope> selectByMessageId(String messageId) {
		return template.selectList(getStatement("selectByMessageId"), messageId);
	}
  
}