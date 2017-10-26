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
import com.topview.message.vo.HistoryMessageVo;

@Service("getHistoryMessage")
public class GetHistoryMessageProcess implements PushProcess {

	@Resource(name="messageDao")
	private MessageMapper messageMapper;
	@Resource(name="envelopeDao")
	private EnvelopeMapper envelopeMapper;

	//TODO 接收人过多时存在分页问题
	public boolean doProcess(PushProcessContext context) {
		// 声明变量
		List<HistoryMessageVo> historyMessageVos = new ArrayList<HistoryMessageVo>();
		String senderId = context.getVo().getSenderId();
		String beginTime = context.getBeginTime();
		String endTime = context.getEndTime();
		int page = context.getPage();
		int limit = context.getLimit();
		int offset = (page - 1) * limit;
		// 查询历史短信内容
		List<Message> messages = messageMapper.getHistoryMessage(senderId,
				beginTime, endTime, limit, offset);
		int total = messageMapper.getHisoryMessageCounut(senderId, beginTime, endTime);
		
		for (int i = 0; i < messages.size(); i++) {
			// 声明变量与准备参数
			HistoryMessageVo vo = new HistoryMessageVo();
			List<String> receiversId = new ArrayList<String>();
			String content = messages.get(i).getContent();
			String sendTime = DateFormatUtil.formatDateToSeconds(messages
					.get(i).getSendTime());
			// 根据短信id查询接收者id列表
			List<Envelope> envelopes = envelopeMapper.selectByMessageId(
					messages.get(i).getId());
			for (int j = 0; j < envelopes.size(); j++) {
				receiversId.add(envelopes.get(j).getReceiverId());
			}
			vo.setContent(content);
			vo.setSendTime(sendTime);
			
			vo.setReceiversId(receiversId);
			historyMessageVos.add(vo);
		}
		context.setTotal(total);
		context.getResult().setHistoryMessageVos(historyMessageVos);
		context.getResult().setSuccess(true);
		return true;
	}

}
