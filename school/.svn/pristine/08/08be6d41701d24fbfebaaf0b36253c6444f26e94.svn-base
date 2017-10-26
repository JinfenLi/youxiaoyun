package com.topview.school.service.clazz.album;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;
import com.topview.school.dao.school.ClazzMapper;

@Service
public class ClazzAlbumFindProcess implements ClazzAlbumProcess {
	private static final Logger logger = Logger
			.getLogger(ClazzAlbumFindProcess.class);
	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private AlbumService albumService;

	@Override
	public boolean doProcess(ClazzAlbumProcessContext context) {
		try {
			AlbumInfo info = context.getInfo();
			AlbumInfoResult result = albumService.albumFindAll(info);
			context.getResult().setInfoResult(result.getInfoResult()); 
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("clazz find album fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
