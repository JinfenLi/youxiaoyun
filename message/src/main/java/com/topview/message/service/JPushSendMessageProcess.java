package com.topview.message.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.util.NotEmptyString;
import com.topview.message.vo.OfflineMessageVo;

/**用于实现极光推送单发的推送组件
 * @author dr
 *
 */
@Service("jPushSendMessageProcess")
public class JPushSendMessageProcess  implements PushProcess{
	
	@Resource
	private JPushService jPushService;

	@Override
	public boolean doProcess(PushProcessContext context) {
		
		OfflineMessageVo message = context.getVo();
		
		String recieverAlias = message.getReceiverId();
		if(NotEmptyString.isNotNullString(recieverAlias)){
			String content = message.toJsonString();
			
			//先发出一个通知类型给ios
			//根据
			String type = message.getType();
			
			System.out.println(recieverAlias + type);
			
			if(type.equals("4")||type.equals("成绩推送")){
				this.jPushService.sendNoticeToAllias(recieverAlias, "您有一条成绩推送，请点击查看", 2);
			}
			else if(type.equals("3")||type.equals("请假申请")){
				this.jPushService.sendNoticeToAllias(recieverAlias, "您有一条请假申请，请点击查看", 2);
			}
			else if(type.equals("6")||type.equals("学生评价")){
				System.out.println(recieverAlias);
				this.jPushService.sendNoticeToAllias(recieverAlias, "您有一条学生评价，请点击查看", 2);	
				System.out.println(recieverAlias);
			}
			else if(type.equals("2")||type.equals("我的短信")){
				this.jPushService.sendNoticeToAllias(recieverAlias, "您有一条新的校园通知，请点击查看", 2);
			}
			else{
				this.jPushService.sendNoticeToAllias(recieverAlias, "您有一条新消息，请点击查看", 2);
			}
			return jPushService.sendMessageToAllias(recieverAlias, content, null);
		}
		return false;
	}

}
