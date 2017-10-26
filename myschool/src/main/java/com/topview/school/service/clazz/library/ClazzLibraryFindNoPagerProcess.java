package com.topview.school.service.clazz.library;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.library.LibraryService;
import com.topview.multimedia.vo.result.LibraryInfoResult;
import com.topview.school.dao.school.ClazzMapper;

@Service
public class ClazzLibraryFindNoPagerProcess implements ClazzLibraryProcess {
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private ClazzMapper clazzMapper;
	private static final Logger logger = Logger
			.getLogger(ClazzLibraryFindNoPagerProcess.class);

	@Override
	public boolean doProcess(ClazzLibraryProcessContext context) {
		try {
			LibraryInfoResult result = libraryService.libraryFindNoPager(context.getInfo());
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
