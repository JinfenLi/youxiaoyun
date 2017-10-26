package com.topview.school.service.clazz.library.video;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

@Service
public class ClazzVideoSaveProcess implements ClazzVideoProcess {
	private static final Logger logger = Logger
			.getLogger(ClazzVideoSaveProcess.class);
	@Autowired
	private VideoService VideoService;

	@Override
	public boolean doProcess(ClazzVideoProcessContext context) {
		try {
			VideoInfo info = context.getInfo();
			info.setComment(" ");
			info.setDescription(" ");
			info.setFormat("  ");
			info.setLabel(" ");
			info.setSize(new BigDecimal(0));
			VideoInfoResult result = VideoService.videoSave(info);
			context.getResult().setResult(result.getResult());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("clazz save video fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
