package com.topview.multimedia.service.album.photo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.vo.PhotoInfo;

/**
 * 照片更新 项目名称:com.topview.multimedia.service.album.photo<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class PhotoUpdateProcess implements PhotoProcess {

	private static final Logger logger = Logger
			.getLogger(PhotoUpdateProcess.class);
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;

	@Autowired
	private MultimediaAlbumMapper multimediaAlbumMapper;

	public boolean doProcess(PhotoProcessContext context) {

		try {
			MultimediaPhoto photo = PhotoInfo.changeToPo(context.getInfo());
			multimediaPhotoMapper.updateByPrimaryKeySelective(photo);
			MultimediaAlbum album = multimediaAlbumMapper.findById(context
					.getInfo().gettMId());
			album.setPhotoCount2(album.getPhotoCount2() + 1);
			album.setUpdateTime(new Date());
			multimediaAlbumMapper.updateByPrimaryKeySelective(album);
			MultimediaAlbum album1 = multimediaAlbumMapper.findById(context
					.getInfo().getDemoid());
			album1.setPhotoCount2(album1.getPhotoCount2() - 1);
			album1.setUpdateTime(new Date());
			multimediaAlbumMapper.updateByPrimaryKeySelective(album1);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("photo update process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
