package com.topview.school.controller.msg_push;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.message.service.RecordService;
import com.topview.message.vo.result.RecordVoResult;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.util.JSONUtil;

@Controller
@RequestMapping(value = "/pushrecord", produces = "text/html;charset=UTF-8")
public class MessagePushRecordController {
	
	@Resource
	private RecordService recordService;
	@Resource
	private StudentService studentService;
	
	/**
	 * 通过messageId去访问消息的接受者有哪些
	 * @param session
	 * @param messageId		消息的id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryByMessageId")
	public String queryByMessageId(HttpSession session,String messageId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] filter = {"type","id","messageId"};
		RecordVoResult result = recordService.queryByMessageId(messageId);
		if(result.isSuccess()) {
			result.getResult().setTotalNubmer(studentService.countByClazzId(result.getResult().getClazzId()));
			resultMap.put("result", result.getResult());
			resultMap.put("success", true);
		}
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	/**
	 * 向服务器发送谁看了通知
	 * @param session
	 * @param messageId		消息的id
	 * @param type			通知类型
	 * @param userId		看了通知的用户id
	 * @param userName		用户名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertRecord")
	public String insertRecord(HttpSession session,String messageId,int type,String userId,String userName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] filter = {"type","id","messageId"};
		boolean flag = recordService.insertRecord(messageId, userName, userId, type);
		resultMap.put("success", flag);
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	
	
}
