package com.topview.multimedia.service.album.photo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.vo.PhotoInfo;

/**
 * 图片查找 项目名称:com.topview.multimedia.service.album.photo<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class PhotoFindProcess implements PhotoProcess {
	private static final Logger logger = Logger
			.getLogger(PhotoFindProcess.class);
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;

	public boolean doProcess(PhotoProcessContext context) {
		try {
			MultimediaPhoto photo = multimediaPhotoMapper.findById(context
					.getInfo().getId());
			List<MultimediaPhoto> l = new ArrayList<MultimediaPhoto>();
			l.add(photo);
			List<PhotoInfo> list = PhotoInfo.changeToVo(l);
			context.getResult().setSuccess(true);
			context.getResult().setInfoResult(list);
			return true;
		} catch (Exception e) {
			logger.error("photo find process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
