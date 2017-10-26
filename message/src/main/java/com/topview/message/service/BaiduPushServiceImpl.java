package com.topview.message.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.topview.message.dao.BaiduPushMapper;
import com.topview.message.dao.PushKeyMapper;
import com.topview.message.po.BaiduPush;
import com.topview.message.po.PushKey;

import net.sf.json.JSONObject;

@Service
public class BaiduPushServiceImpl implements BaiduPushService {

	@Resource
	private BaiduPushMapper baiduPushMapper;
	
	@Resource
	private PushKeyMapper PushKeyMapper;
	
	/************************************************************************************************************/
	/************************************************* android  *************************************************/
	/************************************************************************************************************/
	public boolean pushMessageToAndroidAll(int messageExpires, String message, int sendTime) {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "3Bxd1R4wO4sWYxGir2mZ42Tw";
		String secretKey = "x3dvBWQCGzAzPIBmKBys6vzHjXajaayS";

		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest();

			request.addMessageType(0);// 设置消息类型,0表示消息,1表示通知,默认为0.
			request.addDeviceType(3);// 设置设备类型为Android

			if (messageExpires != 0) {
				request.addMsgExpires(messageExpires);// 设置消息的有效时间,单位秒,默认3600 x
														// 5.
			}
			if (sendTime != 0) {
				request.addSendTime(System.currentTimeMillis() / 1000 + sendTime);// 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
			}

			// 创建 Android的通知
			JSONObject notification = new JSONObject();
			notification.put("title", "百度云推送");
			notification.put("description", message);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);
			request.addMessage(notification.toString());// 设置消息内容

			System.out.println(new Date(System.currentTimeMillis() / 1000));
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			
			return true;
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				e.printStackTrace();
			}
			return false;
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
			return false;
		}

	}

	// android单播
	public boolean pushMessageToAndroidOne(String message, String channelid) {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "3Bxd1R4wO4sWYxGir2mZ42Tw";
		String secretKey = "x3dvBWQCGzAzPIBmKBys6vzHjXajaayS";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建 Android的通知
			JSONObject notification = new JSONObject();
			notification.put("title", "TEST");
			notification.put("description", message);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest();

			request.addChannelId(channelid);
			request.addMessageType(0);// 设置消息类型,0表示消息,1表示通知,默认为0.
			request.addDeviceType(3);// 设置设备类型为Android
			request.addMessage(notification.toString());
			
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
			
			return true;
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				e.printStackTrace();
			}
//			return false;
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return false;
	}
	
	public boolean pushMessageToAndroidTag(String tagName, int messageExpires, String message, int sendTime) {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "3Bxd1R4wO4sWYxGir2mZ42Tw";
		String secretKey = "x3dvBWQCGzAzPIBmKBys6vzHjXajaayS";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			
			// 4. specify request arguments
			// 创建 Android的通知
			JSONObject notification = new JSONObject();
			notification.put("title", "TEST");
			notification.put("description", message);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
//			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);
			
			PushMsgToTagRequest request = new PushMsgToTagRequest()
					.addTagName(tagName)
					.addMessageType(1)  
					.addMessage(notification.toString())
					.addDeviceType(3);
			
			if(messageExpires != 0) {
				request.addMsgExpires(messageExpires);
			}
			
			if(sendTime != 0) {
				request.addSendTime(System.currentTimeMillis() / 1000 + sendTime);
			}
			
			// 5. http request
			PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			
			return true;
		} catch (PushClientException e) {
			e.printStackTrace();
		} catch (PushServerException e) {
			System.out.println(String.format(
					"requestId: %d, errorCode: %d, errorMessage: %s",
					e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
		}
		return false;
	}
	
	/************************************************************************************************************/
	/************************************************* ios  *****************************************************/
	/************************************************************************************************************/
	public boolean pushMessageToIOSAll(int messageExpires, String message, int SendTime, PushKeyPair pair) {
		// 1. get apiKey and secretKey from developer console
//		String apiKey = "Mv6g4Th4hIlQL57MsKM0b3MB";
//		String secretKey = "oO4fGojdrlBLWO0XMChGSjpTTuTaSkKV";
//		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建IOS通知
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", message);
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			
			PushMsgToAllRequest request = new PushMsgToAllRequest();
			request.addMessageType(1).addMessage(notification.toString()).addDepolyStatus(2).addDeviceType(4);
			if(messageExpires != 0) {
				request.addMsgExpires(messageExpires);
			}
			
			if (SendTime != 0) {
				request.addSendTime(System.currentTimeMillis() / 1000 + SendTime);
			}

			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			
			return true;
		} catch (PushClientException e) {
			e.printStackTrace();
		} catch (PushServerException e) {
			System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
					e.getErrorCode(), e.getErrorMsg()));
		}
		return false;
	}

	// ios单播
	public boolean pushMessageToIOSOne(String message, String channelid) {

		
		List<BaiduPush> baiduPushList = baiduPushMapper.selectByChannelId(channelid);
		if(baiduPushList.size() != 0) {
			BaiduPush baiduPush = baiduPushList.get(0);
			PushKey PushKey = PushKeyMapper.selectByPrimaryKey(baiduPush.gettPushKeyId());
			
			PushKeyPair pair = new PushKeyPair(PushKey.getApikey(), PushKey.getSecretkey());
			
			// 2. build a BaidupushClient object to access released interfaces
			BaiduPushClient pushClient = new BaiduPushClient(pair,
					BaiduPushConstants.CHANNEL_REST_URL);

			// 3. register a YunLogHandler to get detail interacting information
			// in this request.
			pushClient.setChannelLogHandler(new YunLogHandler() {
				public void onHandle(YunLogEvent event) {
					System.out.println(event.getMessage());
				}
			});
			  
			  
			try {
				// 4. specify request arguments
				// make IOS Notification
				JSONObject notification = new JSONObject();
				JSONObject jsonAPS = new JSONObject();
				jsonAPS.put("alert", message);
				jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
				jsonAPS.put("badge", 1);//设置角标
				notification.put("aps", jsonAPS);
				notification.put("key1", "value1");

				PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
						.addChannelId(channelid)
						.addMessageType(1)// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
						.addMessage(notification.toString())
						.addDeployStatus(2) // IOS,
											// DeployStatus
											// => 1: Developer
											// 2: Production.
						.addDeviceType(4);// deviceType => 3:android, 4:ios
				
				// 5. http request
				PushMsgToSingleDeviceResponse response = pushClient
						.pushMsgToSingleDevice(request);
				// Http请求结果解析打印
				System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
						+ response.getSendTime());
				
				return true;
			} catch (PushClientException e) {
					e.printStackTrace();
			} catch (PushServerException e) {
					System.out.println(String.format(
							"requestId: %d, errorCode: %d, errorMessage: %s",
							e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		
		
		return false;
	}

	/************************************************************************************************************/
	/************************************************* crud *****************************************************/
	/************************************************************************************************************/
	public boolean update(BaiduPush baiduPush) {
		if(baiduPushMapper.updateByPrimaryKey(baiduPush) != 0) {
			return true;
		}
		return false;
	}
	
	public boolean addPush(BaiduPush baiduPush) {
		if(baiduPushMapper.insert(baiduPush) != 0) {
			return true;
		}
		return false;
	}

	public boolean deletePush(String id) {
		if(baiduPushMapper.deleteByPrimaryKey(id) != 0 ){
			return true;
		}
		return false;
	}

	public List<BaiduPush> queryPush(String userId) {
		return baiduPushMapper.selectByUserId(userId);
	}
	
	public List<BaiduPush> queryPushByChannelId(String channelId) {
		return baiduPushMapper.selectByChannelId(channelId);
	}
}
