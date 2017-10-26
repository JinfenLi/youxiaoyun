package com.topview.school.util;

import org.apache.log4j.Logger;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：VerifyMessageUtil <br>
 * 类描述：通过阿里大鱼的api发送重置密码的验证码短信  <br>
 * 创建人：dr  <br>
 *
 */


public class VerifyMessageUtil {
		
		private static final Logger logger = Logger.getLogger(VerifyMessageUtil.class);
	
		private final static String API_URL="http://gw.api.taobao.com/router/rest";
		
		private final static String APP_ID="23279688";
		
		private final static String APP_SECRET="f7815d7efaed649530c987839bf19f1c";
		
		public static void sendVerifyMessage(String MobileNum,String password){
			if("".equals(MobileNum)||"".equals(password)){
				logger.error("Mobile Number and password can not be null");
			}
			TaobaoClient client = new DefaultTaobaoClient(API_URL,APP_ID,APP_SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend("123456");
			req.setSmsType("normal");
			req.setSmsFreeSignName("优校云");
			req.setSmsParamString("{\"code\":\""+password+"\",\"product\":\"用户\"}");
			req.setRecNum(MobileNum);
			req.setSmsTemplateCode("SMS_2985105");
			try {
				AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
				logger.debug(rsp.getBody());
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
}
