package com.topview.message.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.EnvelopeMapper;
import com.topview.message.po.Envelope;

/**
 * @Description 修改消息发送状态
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午4:25:48
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service("updateMessage")
public class UpdateMessageProcess implements PushProcess {

	@Resource(name="envelopeDao")
	private EnvelopeMapper envelopeMapper;

	public boolean doProcess(PushProcessContext context) {
		
		Envelope envelope = new Envelope();
		envelope.setId(context.getVo().getEnvelopeId());
		envelope.setPushStatus("2"); //2为"已接收",硬编码
		if (envelopeMapper.updateByPrimaryKeySelective(envelope) > 0) {
			context.getResult().setSuccess(true);
			return true;
		}
		return false;
	}

}
