package com.topview.message.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.MessageMapper;
import com.topview.message.vo.OfflineMessageVo;

@Service
public class GetMessageByIdProcess implements PushProcess {

	@Resource(name="messageDao")
	private MessageMapper messageMapper;
	
	@Override
	public boolean doProcess(PushProcessContext context) {
		String messageId = context.getVo().getMessageId();
		OfflineMessageVo vo = messageMapper.getMessageById(messageId);
		List<OfflineMessageVo> result = new ArrayList<OfflineMessageVo>();
		result.add(vo);
		context.getResult().setResult(result);
		context.getResult().setSuccess(true);
		return true;
	}

}
