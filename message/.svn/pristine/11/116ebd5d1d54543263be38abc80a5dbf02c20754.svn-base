package com.topview.message.service;

import java.util.List;

import com.baidu.yun.push.auth.PushKeyPair;
import com.topview.message.po.BaiduPush;

/**
 * @author zjd
 * 百度云推送基础接口，Android与iOS的区别是密钥，通知的json格式不一样
 */
public interface BaiduPushService{
	
	public boolean addPush(BaiduPush baiduPush);
	
	public boolean deletePush(String id);
	
	public List<BaiduPush> queryPush(String userId);
	
	public List<BaiduPush> queryPushByChannelId(String channelId);
	
	public boolean update(BaiduPush baiduPush);
	
	
	/**
	 * @param messageExpires 消息的有效时间(默认5个钟)
	 * @param message 消息的内容
	 * @param SendTime 定时发送，大于60S(默认立即发送)
	 * @return 
	 */
	public boolean pushMessageToAndroidAll(int messageExpires, String message, int SendTime);

	/**
	 * @param messageExpires 消息的有效时间(默认5个钟)
	 * @param message 消息的内容
	 * @param channelid 每一台手机对应一个channelid
	 * @return 
	 */
	public boolean pushMessageToAndroidOne(String message, String channelid);
	
	/**
	 * @param messageExpires 消息的有效时间(默认5个钟)
	 * @param message 消息的内容
	 * @param SendTime 定时发送单位s（默认立即发送）
	 * @return 
	 */
	public boolean pushMessageToIOSAll(int messageExpires, String message, int SendTime, PushKeyPair pair);

	/**
	 * @param messageExpires 消息的有效时间(默认5个钟)
	 * @param message 消息的内容
	 * @param channelid 每一台手机对应一个channelid
	 * @return 
	 */
	public boolean pushMessageToIOSOne(String message, String channelid);
	
//	/**
//	 * @param tagName 标签分组
//	 * @param messageExpires 消息的有效时间(默认5个钟)
//	 * @param message 消息内容
//	 * @param sendTime 定时发送 单位s(默认是立即发送)
//	 */
//	public boolean pushMessageToIOSTag(String tagName, int messageExpires, String message, int sendTime);
//	
//	/**
//	 * @param tagName 标签分组
//	 * @param messageExpires 消息的有效时间(默认5个钟)
//	 * @param message 消息内容
//	 * @param sendTime 定时发送 单位s(默认是立即发送)
//	 */
//	public boolean pushMessageToAndroidTag(String tagName, int messageExpires, String message, int sendTime);
//
//	/**
//	 * @param tagName 标签分组
//	 * @param deviceType 设备类型 3:android 4.ios
//	 */
//	public boolean createTag(String tagName, int deviceType);
//	
//	/**
//	 * @param tagName 标签分组
//	 * @param deviceType 设备类型 3:android 4.ios
//	 */
//	public boolean deleteTag(String tagName, int deviceType);
//	
//	/**
//	 * @param tagName 标签分组
//	 * @param channelIds channlelId组
//	 * @param deviceType 设备类型
//	 */
//	public boolean addDevicesToTag(String tagName, String[] channelIds, String deviceType);
//	
//	/**
//	 * @param tagName 标签分组
//	 * @param channelIds channlelId组
//	 * @param deviceType 设备类型
//	 */
//	public boolean deleteDevicesFromTag(String tagName, String[] channelIds, String deviceType);
}
