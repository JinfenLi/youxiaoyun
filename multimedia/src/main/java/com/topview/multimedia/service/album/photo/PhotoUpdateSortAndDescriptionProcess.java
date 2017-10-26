package com.topview.multimedia.service.album.photo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.vo.PhotoInfo;

@Service
public class PhotoUpdateSortAndDescriptionProcess implements PhotoProcess {


	private static final Logger logger = Logger
			.getLogger(PhotoUpdateNameProcess.class);
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;

	@Autowired
	private MultimediaAlbumMapper multimediaAlbumMapper;

	public boolean doProcess(PhotoProcessContext context) {
		try {
			MultimediaPhoto photo = PhotoInfo.changeToPo(context.getInfo());
			multimediaPhotoMapper.updateByPrimaryKeySelective(photo);
//			MultimediaAlbum album = multimediaAlbumMapper.findById(context
//					.getInfo().gettMId());
//			album.setUpdateTime(new Date());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("photo update process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
