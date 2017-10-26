package com.topview.message.service;

import com.topview.message.vo.OfflineMessageVo;
import com.topview.message.vo.result.OfflineMessageVoResult;

public interface PushMsgService {
	
	/**
	 * 保存离线消息
	 * @param vo 
	 * VO的属性说明：
	 * messageId：消息的id，不需要设置; 
	 * content：消息的内容，调用前必须设置; 
	 * sendTime:发送时间，不需要设置; 
	 * receiverId:接收者id，调用前必须设置; 
	 * senderId：发送人id，调用前必须设置; 
	 * messageType:消息类型，1代表文字消息，2代表多媒体消息，调用前必须设置;  
	 * statue：消息发送状态，1为未发送，2为已发送，此处只能设置为1，调用前必须设置;  
	 * type:消息所属模块，1为家园桥，2为我的短信，3为请假申请，4为成绩推送，5为系统消息，调用前必须设置;
	 * enveloperId:所属信封id，不需要设置;
	 * sms:短信机发送标志，当消息属于我的短信时需要设置该属性值为1.
	 * @return
	 */
	public OfflineMessageVoResult saveOfflineMessage(OfflineMessageVo vo);

	/**
	 * 一对一发送单条消息
	 * @param vo
	 * @return
	 */
	public OfflineMessageVoResult pushMessage(OfflineMessageVo vo);
	
	/**
	 * 保存群发消息（不包含推送）
	 * @param vo
	 * @return
	 */
	public OfflineMessageVoResult saveMassPush(OfflineMessageVo vo);
	
	/**
	 * 判断接收者是否在线,准确率非100%，不建议使用
	 * @param receiverId
	 * @return
	 */
	public OfflineMessageVoResult judgeOnline(String receiverId);
	/**
	 * 根据消息id修改消息发送状态
	 * @param messageId
	 * @return
	 */
	public OfflineMessageVoResult updateMessage(String envelopeId);
	
	/**
	 * 根据接收者id查询未发送消息
	 * @param receiverId
	 * @return
	 */
	public OfflineMessageVoResult getOfflineMessage(String receiverId);
	
	/**
	 * web端查询我的短信记录
	 * @param senderId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public OfflineMessageVoResult getHistoryMessage(String senderId, String beginTime, String endTime,int limit, int page);

	/**
	 * @dateTime 2016年7月15日下午9:17:41
	 * @author zjd
	 * @description 获取历史消息总条数
	 */
	public int getHisoryMessageCounut(String senderId, String beginTime, String endTime);
	
	
	/**用于保存极光推送群发消息的接口
	 * @return
	 */
	public OfflineMessageVoResult saveMultiMessage(OfflineMessageVo vo);
	
	/**
	 * 通过消息的id来获取具体的信息
	 * @param vo
	 * @return
	 */
	public OfflineMessageVoResult getMessageById(OfflineMessageVo vo);
}
