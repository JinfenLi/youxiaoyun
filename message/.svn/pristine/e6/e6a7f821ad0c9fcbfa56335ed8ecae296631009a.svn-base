package com.topview.message.vo;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.topview.message.po.Envelope;
import com.topview.message.po.Message;
import com.topview.message.util.DateFormatUtil;

/**
 * @Description 离线消息VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月16日 下午2:53:40
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class OfflineMessageVo {

	private String messageId; // 消息id  （群发可用）
	private String content; // 消息内容 （群发可用）
	private String sendTime; // 发送时间 （群发可用）
	private String receiverId; // 接收者id （群发可用，群发时将作为群组id 如 学校id 班级id ）
	private String senderId; // 发送者id
	private String senderKidId; //发送者当前在线孩子id
	private String messageType; // 消息的类型：语音、文字、视频、图片等（群发可用,群发时将保存为“文字”）
	private String statue; // 消息的状态:已发送、未发送等
	private String type; // 消息所属的模块:我的短信、家园桥、请假申请等（群发可用,群发时将保存为“群发消息”）
	private String envelopeId; // 所属信封的id
	private int sms; // 标志使用手机短信发送且未发送
	private String studentId; //学生id
	private String kindId;//用于作校园通知的区别 消息种类：1.作业  2.公告  3.通知  4.其他（群发可用,群发时将保存为“通知”）
	
	
	//用于适应成绩模块推送的字段，增加考试id
	private String examId;//考试的id
	private String examStudentId;//考试对应的学生的id
	

	/**
	 * 对推送内几个类别字段的详细说明
	 * --------------------- type ---------------------
	 *7	群发消息
	 *6	学生评价
	 *5	系统通知
	 *4	成绩推送
	 *3	请假申请
	 *2	我的短信
	 *1	家园桥
	 *8	校园公告
	 *
	 * 
	 * 
	 */

	private MultipartFile[] files;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderKidId() {
		return senderKidId;
	}

	public void setSenderKidId(String senderKidId) {
		this.senderKidId = senderKidId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnvelopeId() {
		return envelopeId;
	}

	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public static OfflineMessageVo changeToVo(Message msg, Envelope env) {
		OfflineMessageVo vo = new OfflineMessageVo();
		vo.setMessageId(msg.getId());
		vo.setContent(msg.getContent());
		vo.setSenderId(msg.getSenderId());
		vo.setSenderKidId(msg.getSenderKidId());
		vo.setSms(msg.getSms());
		vo.setSendTime(DateFormatUtil.formatDateToSeconds(msg.getSendTime()));
		switch (msg.gettPushTypeId()) {
		case "1":
			vo.setMessageType("家园桥");
			break;
		case "2":
			vo.setType("我的短信");
			break;
		case "3":
			vo.setType("请假申请");
			break;
		case "4":
			vo.setType("成绩推送");
			break;
		case "5":
			vo.setType("系统通知");
			break;
		case "6":
			vo.setType("学生评价");
			break;
		case "7":
			vo.setType("群发消息");
			break;
		case "8":
			vo.setType("校园公告");
			break;
		default: //添加新的推送模块必须修改此处代码否则报错
			break;
		}
		switch (msg.gettPushPostboxId()) {
		case "1":
			vo.setMessageType("文字");
			break;
		case "2":
			vo.setMessageType("图片");
		case "3":
			vo.setMessageType("语音");
		case "4":
			vo.setMessageType("视频");
		case "5":
			vo.setMessageType("文件");
		default: //添加新类型必须修改此处代码否则报错
			break;
		}
		vo.setEnvelopeId(env.getId());
		vo.setReceiverId(env.getReceiverId());
		vo.setStatue(env.getStudentId());
		vo.setStudentId(env.getStudentId());
		vo.setKindId(msg.gettPushKindId());
		
		return vo;
	}
	
	public String toJsonString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("envelopeId", envelopeId);
		jsonObject.put("messageId", messageId);
		jsonObject.put("content", content.replace("+", "%2B")); //加号要转成%2B
		jsonObject.put("sendTime", sendTime);
		jsonObject.put("receiverId", receiverId);
		jsonObject.put("senderId", senderId);
		jsonObject.put("senderKidId", senderKidId);
		jsonObject.put("type", type);
		jsonObject.put("messageType", messageType);
		jsonObject.put("studentId", studentId);
		jsonObject.put("kindId", kindId);
		return jsonObject.toString();
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExamStudentId() {
		return examStudentId;
	}

	public void setExamStudentId(String examStudentId) {
		this.examStudentId = examStudentId;
	}

	
	
}
