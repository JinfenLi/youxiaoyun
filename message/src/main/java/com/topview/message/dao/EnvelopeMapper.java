package com.topview.message.dao;

import java.util.List;

import com.topview.message.dao.base.BaseDao;
import com.topview.message.po.Envelope;

/**
 * @Description 消息发送情况基础Dao接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午11:29:57
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public interface EnvelopeMapper extends BaseDao<Envelope>{

	List<Envelope> selectByMessageId(String id);
  
}