package com.topview.multimedia.service.album;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.vo.AlbumInfo;

/**
 * 创建相册 项目名称:com.topview.multimedia.service.album<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class AlbumCreateProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumCreateProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		try {
			MultimediaAlbum album = AlbumInfo.changeToPo(context.getInfo());
			album.setCreateTime(new Date());
			album.setUpdateTime(new Date());
			album.setPhotoCount2(0);
			if (multimendiaAlbumMapper.insert(album) > 0) {
				context.getResult().setSuccess(true);
			} else {
				context.getResult().setSuccess(false);
			}
			return true;
		} catch (Exception e) {
			logger.error("create album fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
