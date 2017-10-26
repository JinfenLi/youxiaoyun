package com.topview.message.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.EnvelopeMapper;
import com.topview.message.dao.MessageMapper;
import com.topview.message.po.Envelope;
import com.topview.message.po.Message;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.util.UUIDUtil;
import com.topview.message.vo.OfflineMessageVo;

/**
 * @Description 保存群发消息Process
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午4:25:17
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SaveMassPushProcess implements PushProcess {

	@Resource
	private MessageMapper messageMapper;
	@Resource
	private EnvelopeMapper envelopeMapper;

	public boolean doProcess(PushProcessContext context) {

		List<OfflineMessageVo> vos = new ArrayList<OfflineMessageVo>();
		Message message = new Message();
		OfflineMessageVo vo = context.getVo();
		String[] rsId = vo.getReceiverId().split(",");
		String[] stusId = vo.getStudentId().split(",");
		if (rsId.length != stusId.length) { // 学生id与接收者id数量不匹配
			context.getResult().setCode(-1);
			return false;
		}

		// 保存message
		vo.setMessageId(UUIDUtil.getUUID());
		message.setId(vo.getMessageId());
		message.setContent(vo.getContent());
		message.setSendTime(DateFormatUtil.parseToSeconds(vo.getSendTime()));
		message.setSms(vo.getSms()); // 短信位标志
		message.setSenderId(vo.getSenderId()); // 发送人
		message.settPushTypeId(vo.getType()); // 所属模块：家园桥、我的短信等
		message.settPushPostboxId(vo.getMessageType()); // 消息类型：文字、图片、语音、视频、文件等
		message.settPushKindId(vo.getKindId());//消息种类：作业，公告，其他，通知

		// 保存envlope
		if (messageMapper.insert(message) > 0) {
			for (int i = 0; i < rsId.length; i++) {
				Envelope env = new Envelope();
				env.setId(UUIDUtil.getUUID());
				env.setReceiverId(rsId[i]);
				env.setStudentId(stusId[i]);
				env.setPushStatus("1"); // 1为"未接收"
				env.settPushMessageId(message.getId()); // 所属消息的id
				if (envelopeMapper.insert(env) > 0) {
					System.out.println("保存了一条离线消息" + message.getId() + "__" + message.getContent()
						+ "___"	+ env.getReceiverId());
					// 封装成OfflineMessageVo，用于返回进行推送
					vo = OfflineMessageVo.changeToVo(message, env);
					vos.add(vo);
				}
			}
		} else {
			return false;
		}
		context.getResult().setResult(vos);
		context.getResult().setSuccess(true);
		return true;
	}
}
