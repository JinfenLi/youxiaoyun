package com.topview.school.service.clazz.library.video;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.vo.result.VideoInfoResult;
@Service
public class ClazzVideoFindProcess implements ClazzVideoProcess{
	private static final Logger logger = Logger
			.getLogger(ClazzVideoFindProcess.class);
	@Autowired
	private VideoService VideoService;
	@Override
	public boolean doProcess(ClazzVideoProcessContext context) {
		try{
		VideoInfoResult result = VideoService.videoFind(context.getInfo());
		context.getResult().setResult(result.getResult());
		context.getResult().setSuccess(true); 
		return true;
		}catch(Exception e){
			logger.error("clazz find video fail");
			context.getResult().setSuccess(false); 
			return false;
		}
	}

}
