package com.topview.school.controller.msg_push;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.message.po.BaiduPush;
import com.topview.message.po.PushKey;
import com.topview.message.service.BaiduPushService;
import com.topview.message.service.PushKeyService;
import com.topview.message.service.PushMsgService;
import com.topview.message.util.UUIDUtil;
import com.topview.school.service.school.SchoolService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;


/**
 * @author zjd
 * 百度云推送
 * 感觉目前分组推送，组合，交并集推送还没机会用到
 */
@Controller
@RequestMapping(value = "/baiduPush", produces = "text/html;charset=UTF-8")
public class BaiduPushController {
	
	@Resource
	private BaiduPushService baiduPushService;
	@Resource
	private SchoolService schoolService;
	@Resource
	private PushKeyService pushKeyService;
	@Resource
	private PushMsgService pushMsgService;
	
	/**
	 * @dateTime 2016年5月1日下午12:36:44
	 * @author zjd
	 * @description 关联一个用户到百度云推送    android和ios共用,目前只推送到最近登陆的手机
	 */
	@RequestMapping("addPush")
	@ResponseBody
	public String addPush(String userId, String channelId, String apikey, String secretkey) {
		Map<String, Object> map = new HashMap<>();
		BaiduPush baiduPush = new BaiduPush();
		PushKey pushKey = new PushKey();
		String[] flag = {userId,channelId,apikey,secretkey};
		if(NotEmptyString.isNotEmpty(flag)) {
			if(baiduPushService.queryPush(userId).size() == 0) {//判断该用户是否已经关联了百度云推送服务 
				baiduPush.setId(UUIDUtil.getUUID());
				baiduPush.setUserid(userId);
				baiduPush.setChannelid(channelId);
				
				
				pushKey = pushKeyService.selectByApikey(apikey);
				if(pushKey != null) {
					baiduPush.settPushKeyId(pushKey.getId());
					
					//android只需要一个apikey和secretkey
					if("3Bxd1R4wO4sWYxGir2mZ42Tw".equals(apikey) && "x3dvBWQCGzAzPIBmKBys6vzHjXajaayS".equals(secretkey)) {
						baiduPush.setDevicetype("Android");
					}else {
						baiduPush.setDevicetype("iOS");
					}
					if(baiduPushService.addPush(baiduPush)) {
						map.put("success", true);
					}else {
						map.put("success", false);
					}
				} else {
					map.put("success", false);
				}
				
			} else {
				baiduPush = baiduPushService.queryPush(userId).get(0);
				
				
				baiduPush.setChannelid(channelId);
				baiduPush.setUserid(userId);
				
				pushKey = pushKeyService.selectByApikey(apikey);
				if(pushKey != null) {
					if("3Bxd1R4wO4sWYxGir2mZ42Tw".equals(apikey) && "x3dvBWQCGzAzPIBmKBys6vzHjXajaayS".equals(secretkey)) {
						baiduPush.setDevicetype("Android");
					}else {
						baiduPush.setDevicetype("iOS");
						
						
						baiduPush.settPushKeyId(pushKey.getId());
					}
					if(baiduPushService.update(baiduPush)) {
						map.put("success", true);
					}else {
						map.put("success", false);
					}
				} else {
					map.put("success", false);
				}
			}
		} else {
			map.put("success", false);
		}
		return JSONUtil.toObject(map).toString();
	}
}
