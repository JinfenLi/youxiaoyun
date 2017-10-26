package com.topview.message.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

@Service("judgeOnline")
public class JudgeOnlineProcess implements PushProcess {

	private static final Logger logger = Logger
			.getLogger(JudgeOnlineProcess.class);
	private static final String PRIVATE_PUSH_URL = "http://127.0.0.1:8081/push.ashx"; // 目标URL

	public boolean doProcess(PushProcessContext context) {
		// 准备参数
		OfflineMessageVo vo = context.getVo();
		String receiverId = vo.getReceiverId();

		HttpPost httpPost = new HttpPost(PRIVATE_PUSH_URL); // 创建httppost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 建立存储传递参数的数组
		// 添加参数
		params.add(new BasicNameValuePair("op", "isonline"));
		params.add(new BasicNameValuePair("userid", receiverId));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数编码
			HttpResponse response = new DefaultHttpClient().execute(httpPost); // 发送post请求

			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				if (result.contains("\"isonline\":true")) {
					context.getResult().setSuccess(true);
					return true;
				}
			}
		} catch (UnsupportedEncodingException e) {
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
		return false;
	}

}
