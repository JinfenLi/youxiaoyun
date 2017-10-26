package com.topview.multimedia.service.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PostMapper;
import com.topview.multimedia.po.Post;
import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.vo.PostVo;

/**
 * 保存主贴
 * 
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月3日 下午4:17:11
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SavePostProcess implements PostProcess {

	@Resource
	private RecordUpdateService recordUpdateService;
	@Resource
	private PostMapper postMapper;

	public boolean doProcess(PostProcessContext context) {
		Post po = PostVo.changeToPo(context.getPostVo());
		try {
			if (postMapper.insertSelective(po) > 0) {
				recordUpdateService.saveOrUpdateRecord(po.gettMId(), "post");
				context.getPostVoResult().setSuccess(true);
				return true;
			} else {
				context.getPostVoResult().setSuccess(false);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
