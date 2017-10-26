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
 * 查看点赞
 * @Description
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月6日 下午4:39:34
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SeePraiseProcess implements PostProcess {

	@Resource 
	private PraiseMapper praiseMapper;
	
	public boolean doProcess(PostProcessContext context) {
		
		PraiseVoResult result = context.getPraiseVoResult();
		String postId = context.getPraiseVo().getPostId();
		String praiseTime = context.getPraiseVo().getPraiserTime();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postId", postId);
		map.put("praiseTime", praiseTime);
		
		List<Praise> praises = praiseMapper.selectByPostIdAndDate(map);
		if(praises.size() > 0) {
			List<PraiseVo> vos = PraiseVo.changeToVo(praises);
			result.setSuccess(true);
			result.setResult(vos);
			context.setPraiseVoResult(result);
			return true;
		}		
		return false;
	}

}
