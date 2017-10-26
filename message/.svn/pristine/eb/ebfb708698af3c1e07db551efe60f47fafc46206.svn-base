package com.topview.message.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.message.dao.EnvelopeMapper;
import com.topview.message.dao.MessageMapper;
import com.topview.message.po.Envelope;
import com.topview.message.po.Message;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.util.UUIDUtil;
import com.topview.message.vo.OfflineMessageVo;


@Service
public class SaveMultiMessageProcess implements PushProcess{
	
	@Resource
	private MessageMapper messageMapper;
	@Resource
	private EnvelopeMapper envelopeMapper;

	@Transactional
	public boolean doProcess(PushProcessContext context) {

		Message message = new Message();
		OfflineMessageVo vo = context.getVo();
		List<OfflineMessageVo> vos = new ArrayList<OfflineMessageVo>();

		// 保存message
		vo.setMessageId(UUIDUtil.getUUID());
		message.setId(vo.getMessageId());
		message.setContent(vo.getContent());
		message.setSendTime(DateFormatUtil.parseToSeconds(vo.getSendTime()));
		message.setSms(vo.getSms()); // 短信位标志
		message.setSenderId(vo.getSenderId()); // 发送人
		message.settPushTypeId("7"); // 所属模块：家园桥、我的短信等,群发表示消息为群发消息
		// 消息类型：文字、图片、语音、视频、文件等
		message.settPushPostboxId("1"); //群发接口默认为文字 
		message.settPushKindId(vo.getKindId());//消息种类：作业，公告，其他，通知

		// 保存envlope
		if (messageMapper.insert(message) > 0) {
				Envelope env = new Envelope();
				env.setId(UUIDUtil.getUUID());
				env.setReceiverId(vo.getReceiverId());
				env.setPushStatus("0"); // 群发消息 不需要保存是否接受
				env.settPushMessageId(message.getId()); // 所属消息的id
				if (envelopeMapper.insert(env) > 0) {
					System.out.println("保存了一条群发消息" + message.getId() + "__" + message.getContent()
						+ "___"	+ env.getReceiverId());
					// 封装成OfflineMessageVo，用于返回进行推送
					vo = OfflineMessageVo.changeToVo(message, env);
					vos.add(vo);
				}
		} else {
			context.getResult().setSuccess(false);
			return false;
		}
		context.getResult().setResult(vos);
		context.getResult().setSuccess(true);
		return true;
	}

}
