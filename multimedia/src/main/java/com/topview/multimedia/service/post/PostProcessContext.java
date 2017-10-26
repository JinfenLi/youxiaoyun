package com.topview.multimedia.service.post;


import javax.servlet.http.HttpSession;

import com.topview.multimedia.vo.PostVo;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.ReplyVo;
import com.topview.multimedia.vo.result.PostVoResult;
import com.topview.multimedia.vo.result.PraiseVoResult;
import com.topview.multimedia.vo.result.ReplyVoResult;

public class PostProcessContext {

	// 参数封装
	private PostVo postVo;
	private PraiseVo praiseVo;
	private ReplyVo replyVo;
	private String start;
	private String limit;
	private HttpSession session;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	// 结果封装
	private PostVoResult postVoResult;
	private PraiseVoResult praiseVoResult;

	private ReplyVoResult replyVoResult;

	public PostVo getPostVo() {
		return postVo;
	}

	public void setPostVo(PostVo postVo) {
		this.postVo = postVo;
	}

	public PraiseVo getPraiseVo() {
		return praiseVo;
	}

	public void setPraiseVo(PraiseVo praiseVo) {
		this.praiseVo = praiseVo;
	}

	public ReplyVo getReplyVo() {
		return replyVo;
	}

	public void setReplyVo(ReplyVo replyVo) {
		this.replyVo = replyVo;
	}

	public PostVoResult getPostVoResult() {
		return postVoResult;
	}

	public void setPostVoResult(PostVoResult postVoResult) {
		this.postVoResult = postVoResult;
	}

	public PraiseVoResult getPraiseVoResult() {
		return praiseVoResult;
	}

	public void setPraiseVoResult(PraiseVoResult praiseVoResult) {
		this.praiseVoResult = praiseVoResult;
	}

	public ReplyVoResult getReplyVoResult() {
		return replyVoResult;
	}

	public void setReplyVoResult(ReplyVoResult replyVoResult) {
		this.replyVoResult = replyVoResult;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
