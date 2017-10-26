package com.topview.multimedia.service.album.photo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaZoneMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.po.MultimediaZone;

@Service
public class PhotoCheckProcess implements PhotoProcess {

	@Autowired
	private MultimediaZoneMapper multimediaZoneMapper;
	@Autowired
	private MultimediaAlbumMapper multimediaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(PhotoCheckProcess.class);

	public boolean doProcess(PhotoProcessContext context) {
		try {
			MultimediaZone zone = multimediaZoneMapper.findById(context
					.getInfo().getZoneId());
			MultimediaAlbum album = multimediaAlbumMapper.findById(context
					.getInfo().gettMId());
			if (zone != null && album != null
					&& zone.getId().equals(album.gettMId())) {
				context.getResult().setSuccess(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("check photo fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
