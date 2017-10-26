package com.topview.multimedia.service.album.photo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaAlbum;

/**
 * 照片删除 项目名称:com.topview.multimedia.service.album.photo<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class PhotoDeleteProcess implements PhotoProcess {
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;
	@Autowired
	private MultimediaAlbumMapper multimediaAlbumMapper;
	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;

	public boolean doProcess(PhotoProcessContext context) {

		try { //TODO 为什么不分开在多个process中？
			multimediaCollectMapper.deleteByMultiId(context.getInfo().getId()); // 删除收藏
			multimediaPhotoMapper.deleteByPrimaryKey(context.getInfo().getId()); // 删除图片
			MultimediaAlbum album = multimediaAlbumMapper.findById(context
					.getInfo().gettMId()); // 查询删除图片所在的相册，将图片数量减一，更新最后更新时间
			album.setPhotoCount2(album.getPhotoCount2() - 1);
			album.setUpdateTime(new Date());
			multimediaAlbumMapper.updateByPrimaryKeySelective(album);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
