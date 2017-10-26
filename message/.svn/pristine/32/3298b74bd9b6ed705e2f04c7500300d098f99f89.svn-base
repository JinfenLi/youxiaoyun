package com.topview.message.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.MessageMapper;
import com.topview.message.dao.EnvelopeMapper;
import com.topview.message.po.Envelope;
import com.topview.message.po.Message;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.util.UUIDUtil;
import com.topview.message.vo.OfflineMessageVo;

/**
 * @Description 保存离线消息
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午4:25:17
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service("saveOfflineMessage")
public class SaveOfflineMessageProcess implements PushProcess {

	@Resource(name = "messageDao")
	private MessageMapper messageMapper;
	@Resource(name = "envelopeDao")
	private EnvelopeMapper envelopeMapper;

	public boolean doProcess(PushProcessContext context) {

		Message message = new Message();
		Envelope envelope = new Envelope();

		OfflineMessageVo vo = context.getVo();
		if (vo.getEnvelopeId() == null || "".equals(vo.getEnvelopeId())) {
			vo.setEnvelopeId(UUIDUtil.getUUID());
		}
		vo.setMessageId(UUIDUtil.getUUID());
		message.setId(vo.getMessageId());
		message.setContent(vo.getContent());
		message.setSendTime(DateFormatUtil.parseToSeconds(vo.getSendTime()));
		message.setSms(vo.getSms()); // 短信位标志
		message.setSenderId(vo.getSenderId()); // 发送人
		message.setSenderKidId(vo.getSenderKidId());
		message.settPushTypeId(vo.getType()); // 所属模块：家园桥、我的短信等
		message.settPushPostboxId(vo.getMessageType()); // 消息类型：文字、图片、语音、视频、文件等
		message.settPushKindId(vo.getKindId());//消息的种类：作业，公告，通知，其他

		envelope.setId(vo.getEnvelopeId());
		envelope.setReceiverId(vo.getReceiverId());
		envelope.setStudentId(vo.getStudentId());
		envelope.setPushStatus("1"); // 1为"未接收",硬编码字段,当初没设计好...
		envelope.settPushMessageId(vo.getMessageId()); // 消息对应的id

		if (messageMapper.insert(message) > 0
				&& envelopeMapper.insert(envelope) > 0) {
			System.out.println("保存了一条离线消息" + vo.toJsonString());
			context.getResult().setSuccess(true);
			return true;
		}
		return false;
	}
}
