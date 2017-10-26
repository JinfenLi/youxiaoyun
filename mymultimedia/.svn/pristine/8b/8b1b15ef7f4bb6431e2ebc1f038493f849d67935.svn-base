package com.topview.multimedia.service.library;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.vo.LibraryInfo;
/**
 * 更新视频库信息
 * 项目名称:com.topview.multimedia.service.library<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class LibraryUpdateProcess implements LibraryProcess {

	private static final Logger logger = Logger
			.getLogger(LibraryUpdateProcess.class);
	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(LibraryProcessContext context) {

		try {
			MultimediaVideoLibrary library = LibraryInfo.changeToPo(context.getInfo());
			multimediaVideoLibraryMapper.updateByPrimaryKeySelective(library);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("library update process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
