package com.topview.school.service.clazz.library;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.library.LibraryService;
import com.topview.multimedia.vo.result.LibraryInfoResult;

@Service
public class ClazzLibraryFindProcess implements ClazzLibraryProcess {
	@Autowired
	private LibraryService libraryService;
//	@Autowired
//	private ClazzMapper clazzMapper;
	private static final Logger logger = Logger
			.getLogger(ClazzLibraryFindProcess.class);

	@Override
	public boolean doProcess(ClazzLibraryProcessContext context) {
		try {
			LibraryInfoResult result = libraryService.libraryFindAll(context.getInfo());
			context.getResult().setResult(result.getResult());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("clazz find library fail");
			context.getResult().setSuccess(true);
			return false;
		}
	}

}
