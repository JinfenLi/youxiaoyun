package com.topview.multimedia.service.library;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.vo.LibraryInfo;
/**
 * 创建视频库
 * 项目名称:com.topview.multimedia.service.library<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class LibrarySaveProcess implements LibraryProcess {

	private static final Logger logger = Logger
			.getLogger(LibrarySaveProcess.class);
	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(LibraryProcessContext context) {

		try {
			MultimediaVideoLibrary library = LibraryInfo.changeToPo(context
					.getInfo());
			library.setCreateTime(new Date());
//			library.setId(UUIDUtil.getUUID());
			library.setPhotoCount2(0);
			multimediaVideoLibraryMapper.insert(library);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("library find process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
