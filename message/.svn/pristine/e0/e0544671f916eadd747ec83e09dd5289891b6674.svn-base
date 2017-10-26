package com.topview.message.service;


/**
 * @author dr
 *	＠desc 该类用于实现极光推送的推送功能，随着老用户更替，将逐步取代原有socket推送
 */
public interface JPushService {
		
	/**
	 * 根据用户别名  单发 自定义消息 到 某个用户
	 * @param alias 用户别名，用户为家长时为用户Id，用户为教师时为用户的的孩子的Id
	 * @param messageContent 发送短信的内容
	 * @param platform 选择发送的平台，1为安卓，2为Ios 不选择默认发送到所有平台
	 * @return 是否发送成功
	 */
	public boolean sendMessageToAllias(String alias, String messageContent, Integer platform);
	
	
	/**
	 * 根据用户别名  单发 通知到 某个用户
	 * @param alias 用户别名，用户为家长时为用户Id，用户为教师时为用户的的孩子的Id
	 * @param messageContent 发送短信的内容
	 * @param platform 选择发送的平台，1为安卓，2为Ios 不选择默认发送到所有平台
	 * @return 是否发送成功
	 */
	public boolean sendNoticeToAllias(String alias, String messageContent, Integer platform);
	
	/**根据标签 群发 自定义消息   给该标签下的所有用户
	 * @param content 群发内容
	 * @param tag 用户所在分类
	 * @param platform 平台 
	 * @return 是否发送成功
	 */
	public boolean sendMultiMessageToTag(String content, String tag, Integer platform);
	
	
	/**向所有用户发送一条消息。（极度紧急情况下使用，一般不建议使用）
	 * @param content 消息内容
	 * @return 
	 */
	public boolean sendMessageToAll(String content);
	
	/**
	 * 根据标签 群发   通知到 标签下的所有用户
	 * @param tag用户别名，用户为家长时为用户Id，用户为教师时为用户的的孩子的Id
	 * @param messageContent 发送短信的内容
	 * @param platform 选择发送的平台，1为安卓，2为Ios 不选择默认发送到所有平台
	 * @return 是否发送成功
	 */
	public boolean sendNoticeToTag(String tag, String messageContent, String platform);
	
	
	
}
