package com.topview.multimedia.service.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.po.Reply;
import com.topview.multimedia.vo.ReplyVo;
import com.topview.multimedia.vo.result.ReplyVoResult;

/**
 * @Description 查看评论
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月6日 下午4:26:30
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SeeReplyProcess implements PostProcess {

	@Resource
	private ReplyMapper replyMapper;

	public boolean doProcess(PostProcessContext context) {

		ReplyVoResult result = context.getReplyVoResult();
		String postId = context.getReplyVo().getPostId();
		String lastUpdate = context.getReplyVo().getReplyTime();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postId", postId);
		map.put("replyTime", lastUpdate);

		List<Reply> replies = replyMapper.selectByPostIdAndDate(map);

		if (replies.size() > 0) {
			result.setSuccess(true);
			List<ReplyVo> vos = ReplyVo.changeToVo(replies);
			result.setResult(vos);
			context.setReplyVoResult(result);
			return true;
		}
		return false;
	}

}
