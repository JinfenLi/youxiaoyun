package com.topview.school.service.clazz.album;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;
@Service
public class ClazzAlbumFindByTypeProcess implements ClazzAlbumProcess{
	@Autowired
	private AlbumService albumService;
	private static final Logger logger = Logger
			.getLogger(ClazzAlbumFindProcess.class);
	@Override
	public boolean doProcess(ClazzAlbumProcessContext context) {
		try {
			AlbumInfo info = context.getInfo();
			AlbumInfoResult result = albumService.albumFindByType(info);
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("clazz find album by type fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
