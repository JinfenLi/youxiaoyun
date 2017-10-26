package com.topview.multimedia.service.album;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaPhotoMapper;

@Service
public class AlbumDeletesProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;

	private static final Logger logger = Logger
			.getLogger(AlbumDeleteProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		try {
			String tMId = context.getInfo().getId();
			multimediaPhotoMapper.deleteByTmid(tMId); //TODO 删除收藏不应该放在这里
			if(multimendiaAlbumMapper.deleteByPrimaryKey(tMId) > 0) {
				context.getResult().setSuccess(true);
			} else {
				context.getResult().setSuccess(false);
			}
			return true;
		} catch (Exception e) {
			logger.error("delete album fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
