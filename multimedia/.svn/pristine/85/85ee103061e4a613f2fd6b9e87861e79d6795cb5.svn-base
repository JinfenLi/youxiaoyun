package com.topview.multimedia.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.po.Reply;

/**
 * @Description 回复Vo
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月21日 下午2:45:33
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class ReplyVo {

	private String id;
	private String postId; // 所属帖子id
	private String replyerId; // 回复者id
	private String replyTime; // 回复时间
	private String ownerId; // 被回复的人的id
	private String replyerName; // 回复者姓名
	private String ownerName; // 被回复者姓名
	private String content; // 评论内容
	private String picUrl;	//回复者的图片路径

	public static Reply changeToPo(ReplyVo vo) {
		Reply reply = new Reply();
		reply.setId(vo.getId());
		reply.setPostId(vo.getPostId());
		reply.setReplyerId(vo.getReplyerId());
		reply.setReplyerName(vo.getReplyerName());
		reply.setOwnerName(vo.getOwnerName());
		reply.setContent(vo.getContent());
		reply.setOwnerId(vo.getOwnerId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			reply.setReplyTime(df.parse(vo.getReplyTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return reply;
	}

	public static ReplyVo changeToVo(Reply reply) {
		ReplyVo vo = new ReplyVo();
		vo.setId(reply.getId());
		vo.setOwnerId(reply.getOwnerId());
		vo.setPostId(reply.getPostId());
		vo.setReplyerId(reply.getReplyerId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vo.setReplyTime(df.format(reply.getReplyTime()));
		vo.setReplyerName(reply.getReplyerName());
		vo.setOwnerName(reply.getOwnerName());
		vo.setContent(reply.getContent());
		return vo;
	}

	public static List<ReplyVo> changeToVo(List<Reply> replies) {
		List<ReplyVo> vos = new ArrayList<ReplyVo>();
		for (Reply r : replies) {
			vos.add(ReplyVo.changeToVo(r));
		}
		return vos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getReplyerId() {
		return replyerId;
	}

	public void setReplyerId(String replyerId) {
		this.replyerId = replyerId;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getReplyerName() {
		return replyerName;
	}

	public void setReplyerName(String replyerName) {
		this.replyerName = replyerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	
}
