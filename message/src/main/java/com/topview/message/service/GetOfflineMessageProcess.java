package com.topview.message.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.MessageMapper;
import com.topview.message.vo.OfflineMessageVo;


/**
 * @Description 查询 用户未接收的离线消息
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午4:26:05
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service("getOfflineMessage")
public class GetOfflineMessageProcess implements PushProcess {

	@Resource(name="messageDao")
	private MessageMapper messageMapper;

	public boolean doProcess(PushProcessContext context) {

		String receiverId = context.getVo().getReceiverId();

		List<OfflineMessageVo> messages = messageMapper
				.getOfflineMessages(receiverId);
		if (messages.size() > 0) {
			context.getResult().setResult(messages);
			context.getResult().setSuccess(true);
			return true;
		}
		return false;
	}

}
