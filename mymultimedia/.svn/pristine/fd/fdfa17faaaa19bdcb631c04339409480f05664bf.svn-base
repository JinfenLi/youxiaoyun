package com.topview.multimedia.service.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.po.Reply;
import com.topview.multimedia.vo.ReplyVo;

/**
 * 保存评论
 * 
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月3日 下午4:16:45
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SaveReplyProcess implements PostProcess {

	@Resource
	private ReplyMapper replyMapper;

	public boolean doProcess(PostProcessContext context) {
		Reply reply = ReplyVo.changeToPo(context.getReplyVo());
		if (reply == null) {
			return false;
		}
		try {
			if (replyMapper.insertSelective(reply) > 0) {
				context.getReplyVoResult().setSuccess(true);
				return true;
			} else {
				context.getReplyVoResult().setSuccess(false);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
