package com.topview.message.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.message.vo.OfflineMessageVo;

/**
 * @Description 推送消息Process(一对一私聊)
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午3:25:52
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service("pushMessage")
public class PushMessageProcess implements PushProcess {

	private static final Logger logger = Logger
			.getLogger(PushMessageProcess.class);

	//TODO 如果单独webpush站点崩溃不可用无法动态切换站点目标地址
	private static final String PRIVATE_PUSH_URL0 = "http://127.0.0.1:8081/push.ashx"; // webpush站点1
	private static final String PRIVATE_PUSH_URL1 = "http://127.0.0.1:8001/push.ashx"; // webpush站点2

	public boolean doProcess(PushProcessContext context) {

		// 准备参数
		OfflineMessageVo vo = context.getVo();
		String text = vo.toJsonString();
		String receiverId = vo.getReceiverId();
		String senderId = vo.getSenderId();
		int i = new Random().nextInt(10) + 1;
		HttpPost httpPost;
		if (i % 2 == 1) {
			httpPost = new HttpPost(PRIVATE_PUSH_URL0); // 创建httppost对象
		} else {
			httpPost = new HttpPost(PRIVATE_PUSH_URL1); // 创建httppost对象
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 建立存储传递参数的数组
		// 添加参数
		params.add(new BasicNameValuePair("op", "privatepush"));
		params.add(new BasicNameValuePair("userid", senderId));
		params.add(new BasicNameValuePair("touserid", receiverId));
		params.add(new BasicNameValuePair("Text", text));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数编码
			HttpResponse response = new DefaultHttpClient().execute(httpPost); // 发送post请求

			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				if (result.contains("\"IsOK\":true")) {
					System.out.println("发送： " + senderId + "给" + receiverId
							+ "发送了一条离线消息" + vo.toJsonString());
					context.getResult().setSuccess(true);
					return true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("——————编码转换异常————————");
			logger.error("编码转换异常", e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.out.println("——————客户端协议异常————————");
			logger.error("http客户端协议异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("—————————IO异常—————————");
			logger.error("网络IO异常", e);
			e.printStackTrace();
		}
		System.out.println("——————————发送失败———————— ：" + text);
		return false;
	}

}
