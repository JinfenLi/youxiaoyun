package com.topview.multimedia.service.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PraiseMapper;
import com.topview.multimedia.po.Praise;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.result.PraiseVoResult;

/**
 * @Description 根据用户id和帖子id判断用户是否点过赞
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月10日 下午8:31:56
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class JudgePraiseProcess implements PostProcess {

	@Resource
	private PraiseMapper praiseMapper;

	public boolean doProcess(PostProcessContext context) {

		PraiseVoResult result = context.getPraiseVoResult();
		PraiseVo vo = context.getPraiseVo();
		String praiserId = vo.getPraiserId();
		String postId = vo.getPostId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("praiserId", praiserId);
		map.put("postId", postId);
		List<Praise> praises = praiseMapper.selectByParam(map);
		if (praises.size() > 0) {
			result.setResult(PraiseVo.changeToVo(praises));
			result.setSuccess(true);
		}
		context.setPraiseVoResult(result);
		return true;
	}

}
