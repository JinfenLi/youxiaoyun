package com.topview.multimedia.service.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.dao.PostAccessoryMapper;
import com.topview.multimedia.dao.PostMapper;
import com.topview.multimedia.dao.PraiseMapper;
import com.topview.multimedia.dao.ReplyMapper;
import com.topview.multimedia.po.Post;

/**
 * @Description 删除主帖、 评论、 或者点赞
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月6日 下午4:34:41
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class DeleteProcess implements PostProcess {

	@Resource
	private PostMapper postMapper;
	@Resource
	private PraiseMapper praiseMapper;
	@Resource
	private ReplyMapper replyMapper;
	@Resource
	private PostAccessoryMapper postAccessoryMapper;
	
	@Transactional
	public boolean doProcess(PostProcessContext context) {
		
		String id;
		if(context.getPostVo() != null) {
			try {
				id = context.getPostVo().getId();
				postAccessoryMapper.deleteByPostId(id); //删除附件
				praiseMapper.deleteByPostId(id); //删除点赞
				replyMapper.deleteByPostId(id); //删除评论
				Post post = new Post();
				post.setId(id);
				post.setStatus(2);
				postMapper.updateByPrimaryKeySelective(post); //将帖子状态置为2（表示已删除，不可见状态）
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		} else if(context.getReplyVo() != null) { //删除回复、评论
			replyMapper.deleteByPrimaryKey(context.getReplyVo().getId());
		} else if(context.getPraiseVo() != null) { //取消点赞s
			praiseMapper.deleteByPrimaryKey(context.getPraiseVo().getId());
		} else {
			return false;
		}
		context.getPostVoResult().setSuccess(true);
		return true;
	}

}
