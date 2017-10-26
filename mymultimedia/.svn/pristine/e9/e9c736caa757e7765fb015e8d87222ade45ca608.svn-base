/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月5日 上午9:39:32 
 * @version V1.0
 */
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
 * @ClassName: PhotoUpdateNameProcess
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月5日 上午9:39:32
 * 
 */
@Service
public class PhotoUpdateNameProcess implements PhotoProcess {

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
			MultimediaAlbum album = multimediaAlbumMapper.findById(context
					.getInfo().gettMId());
			album.setPhotoCount2(album.getPhotoCount2() - 1);
			album.setUpdateTime(new Date());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("photo update process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
