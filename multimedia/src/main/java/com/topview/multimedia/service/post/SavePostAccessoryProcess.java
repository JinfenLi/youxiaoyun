package com.topview.multimedia.service.post;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.PostAccessoryMapper;
import com.topview.multimedia.po.PostAccessory;
import com.topview.multimedia.util.UUIDUtil;

/**
 * 保存附件路径
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月3日 下午4:17:27
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SavePostAccessoryProcess implements PostProcess {

	@Resource
	private PostAccessoryMapper postAccessoryMapper;

	/**
	 * 保存附件
	 */
	public boolean doProcess(PostProcessContext context) {
		String postId = context.getPostVo().getId();
		int i=1;
		List<String> filePath = context.getPostVo().getUrls();
		if (filePath != null) {
			for (String path : filePath) {
				PostAccessory accessory = new PostAccessory();
				accessory.setId(UUIDUtil.getUUID());
				accessory.settMutimediaPostId(postId);
				accessory.setUrl(path);
				accessory.setorders(i++);
				postAccessoryMapper.insertSelective(accessory);
			}
		}
		return true;
	}

}
