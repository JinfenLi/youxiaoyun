package com.topview.school.service.clazz.album.photo;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.vo.PhotoInfo;
import com.topview.multimedia.vo.result.PhotoInfoResult;

@Service
public class ClazzPhotoSaveProcess implements ClazzPhotoProcess {
	private static final Logger logger = Logger
			.getLogger(ClazzPhotoSaveProcess.class);
	@Autowired
	private PhotoService photoService;
	@Resource
	private AlbumService albumService;

	@Override
	public boolean doProcess(ClazzPhotoProcessContext context) {
		try {
			PhotoInfo info = context.getInfo();
			PhotoInfoResult result = photoService.photoSave(info);
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(result.isSuccess());
			return true;
		} catch (Exception e) {
			logger.error("clazz save photo fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
