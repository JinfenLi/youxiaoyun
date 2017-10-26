package com.topview.multimedia.service.library.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.po.MultimediaVideoLibrary;

/**
 * 视频删除 项目名称:com.topview.multimedia.service.library.video<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class VideoDeleteProcess implements VideoProcess {

	@Autowired
	private MultimediaVideoMapper multimediaVideoMapper;
	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;
	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(VideoProcessContext context) {

		try {
			multimediaCollectMapper.deleteByMultiId(context.getInfo().getId());// 删除收藏
			multimediaVideoMapper.deleteByPrimaryKey(context.getInfo().getId()); // 删除视频
			MultimediaVideoLibrary library = multimediaVideoLibraryMapper
					.findById(context.getInfo().gettMId());// 查询删除的视频所在的视频库，将视频数量减一
			library.setPhotoCount2(library.getPhotoCount2() - 1);
			multimediaVideoLibraryMapper.updateByPrimaryKeySelective(library);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
