package com.topview.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.message.dao.PostboxMapper;
import com.topview.message.dao.base.impl.BaseDaoImpl;
import com.topview.message.po.Postbox;

/**
 * @Description 消息类型基础dao接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午11:29:33
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
@Repository("postboxDao")
public class PostboxMapperImpl extends BaseDaoImpl<Postbox> implements PostboxMapper{
    
}