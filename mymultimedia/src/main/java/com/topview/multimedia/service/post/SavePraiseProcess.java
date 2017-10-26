package com.topview.multimedia.service.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PraiseMapper;
import com.topview.multimedia.po.Praise;
import com.topview.multimedia.vo.PraiseVo;

/**
 * 保存点赞
 * 
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月3日 下午4:16:45
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SavePraiseProcess implements PostProcess {

	@Resource
	private PraiseMapper praiseMapper;

	public boolean doProcess(PostProcessContext context) {
		Praise praise = PraiseVo.changToPo(context.getPraiseVo());
		if (praise == null) {
			return false;
		}
		try {
			if (praiseMapper.insertSelective(praise) > 0) {
				context.getPraiseVoResult().setSuccess(true);
				return true;
			} else {
				context.getPraiseVoResult().setSuccess(false);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
