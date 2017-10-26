package com.topview.multimedia.vo.result;

import java.util.List;

import com.topview.multimedia.vo.PostVo;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.ReplyVo;

public class PostVoResult {

	private int code;
	private boolean success;
	private List<PostVo> posts; //帖子 
	private List<PraiseVo> praises; //点赞
	private List<ReplyVo> replies; //回复列表

	public List<PostVo> getPosts() {
		return posts;
	}

	public void setPosts(List<PostVo> posts) {
		this.posts = posts;
	}

	public List<PraiseVo> getPraises() {
		return praises;
	}

	public void setPraises(List<PraiseVo> praises) {
		this.praises = praises;
	}

	public List<ReplyVo> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyVo> replies) {
		this.replies = replies;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


}
