package com.topview.message.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.topview.message.util.NotEmptyString;
/**
 * @author dr
 * 
 */
@Service
public class JPushServiceImpl implements JPushService {
	
	private static final String APP_KEY = "d9c499cdbd599cbfc3e6ade7";
	private static final String MASTER_SECRET = "fa5a2918b740c4eee2f244b5";
	private static final Logger logger = Logger.getLogger(JPushClient.class);
	JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
	@Override
	public boolean sendMessageToAllias(String alias, String messageContent,
			Integer platform) {
		
		if(NotEmptyString.isNotEmpty(new String[] {alias,messageContent})){
			PushPayload pushPayload = null;
			if(platform==null){
			pushPayload = PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setMessage(Message.newBuilder()
	                		.setMsgContent(messageContent)
	                        .build())
	                .build();
			}
			else if(platform==1){ 
				pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.alias(alias))
		                .setMessage(Message.newBuilder()
		                		.setMsgContent(messageContent)
		                        .build())
		                .build();
			}
			else if (platform == 2){
				pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.ios())
		                .setAudience(Audience.alias(alias))
		                .setMessage(Message.newBuilder()
		                		.setMsgContent(messageContent)
		                        .build())
		                .build();
			}
			if(sendMessage(pushPayload)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean sendMultiMessageToTag(String content, String tag,
			Integer platform) {
		if(NotEmptyString.isNotEmpty(new String[] {content, tag})){
			PushPayload pushPayload = null;
			if(platform==null){
			pushPayload = PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.tag(tag))
	                .setMessage(Message.newBuilder()
	                		.setMsgContent(content)
	                        .build())
	                .build();
			}
			else if(platform==1){ 
				pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.android())
		                .setAudience(Audience.tag(tag))
		                .setMessage(Message.newBuilder()
		                		.setMsgContent(content)
		                        .build())
		                .build();
			}
			else if (platform == 2){
				pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.ios())
		                .setAudience(Audience.tag(tag))
		                .setMessage(Message.newBuilder()
		                		.setMsgContent(content)
		                        .build())
		                .build();
			}
			if(sendMessage(pushPayload)){ 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean sendMessageToAll(String content) {
		if(NotEmptyString.isNotNullString(content)){
			PushPayload payload = PushPayload.alertAll(content);
			if(sendMessage(payload)){
				return true;
			}
		}
		return false;
	}
	
	public boolean sendMessage(PushPayload pushPayload){
		 try {
	            PushResult result = this.jpushClient.sendPush(pushPayload);
	            logger.info("Got result - " + result);
	            return true;	
	        } catch (APIConnectionException e) {
	            // Connection error, should retry later
	        	logger.info("Connection error, should retry later",e);

	        } catch (APIRequestException e) {
	            // Should review the error, and fix the request
	        	logger.error("Should review the error, and fix the request",e);
	        	logger.error("HTTP Status: " + e.getStatus());
	        	logger.error("Error Code: " + e.getErrorCode());
	        	logger.error("Error Message: " + e.getErrorMessage());
	        }
		return false;
	}

	//向用户别名发送通知
	@Override
	public boolean sendNoticeToAllias(String alias, String messageContent,
			Integer platform) {
		if(NotEmptyString.isNotEmpty(new String[] {alias,messageContent})){
			PushPayload pushPayload = null;
			if(platform==null){
			}
			else if (platform == 2){
				pushPayload = PushPayload.newBuilder()
						 .setPlatform(Platform.ios())
			                .setAudience(Audience.alias(alias))
			                .setNotification(Notification.newBuilder()
			                        .addPlatformNotification(IosNotification.newBuilder()
			                                .setAlert(messageContent)
			                                .setBadge(1)
			                                .build())
			                        .build())
			                 .setOptions(Options.newBuilder()
			                         .setApnsProduction(true)
			                         .build())
			                 .build();
				/*pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.ios())
		                .setAudience(Audience.alias(alias))
		                .setNotification(Notification.ios(messageContent, null))
		                .build();*/
			}
			if(sendMessage(pushPayload)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean sendNoticeToTag(String tag, String messageContent,
			String platform) {
		if(NotEmptyString.isNotEmpty(new String[] {tag,messageContent})){
			PushPayload pushPayload = null;
				pushPayload = PushPayload.newBuilder()
		                .setPlatform(Platform.ios())
		                .setAudience(Audience.tag(tag))
		                .setNotification(Notification.ios(messageContent, null))
		                .build();
			if(sendMessage(pushPayload)){
				return true;
			}
		}
		return false;
	}

}
