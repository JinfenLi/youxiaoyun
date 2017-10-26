package com.topview.multimedia.service.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;

/**
 * 视频库删除 项目名称:com.topview.multimedia.service.library<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class LibraryDeleteProcess implements LibraryProcess {

	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;

	public boolean doProcess(LibraryProcessContext context) {

		try {
			multimediaCollectMapper.deleteByMultiId(context.getInfo().getId());
			multimediaVideoLibraryMapper.deleteByPrimaryKey(context.getInfo()
					.getId());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
