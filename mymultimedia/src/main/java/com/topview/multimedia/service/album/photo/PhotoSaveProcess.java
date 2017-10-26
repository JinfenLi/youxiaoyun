package com.topview.multimedia.service.album.photo;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.PhotoInfo;

/**
 * 照片保存 项目名称:com.topview.multimedia.service.album.photo<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class PhotoSaveProcess implements PhotoProcess {

	@Resource
	private RecordUpdateService recordUpdateService;
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;
	@Autowired
	private MultimediaAlbumMapper multimediaAlbumMapper;

	/**
	 * 保存照片、相册照片数量加一、更新照片模块的最后更新时间
	 */
	public boolean doProcess(PhotoProcessContext context) {
		try {
			// TODO 逻辑混乱难维护难扩展，应该将各个步骤单独封装成一个process并加上事务
			MultimediaPhoto photo = PhotoInfo.changeToPo(context.getInfo());
			photo.setId(UUIDUtil.getUUID());
			MultimediaAlbum album = multimediaAlbumMapper.findById(photo
					.gettMId());
			album.setPhotoCount2(album.getPhotoCount2() + 1);
			album.setUpdateTime(new Date());
			if (multimediaPhotoMapper.insert(photo) > 0) { // 保存成功则记录数加一并更新最后更新时间
				multimediaAlbumMapper.updateByPrimaryKeySelective(album);
				if (album.gettMId().equals(context.getInfo().getZoneId())) { // 在controll里将zoneId设置为用户的schoolId，以此来判断是校园相册的更新还是班级相册
					recordUpdateService.saveOrUpdateRecord(album.gettMId(),
							"schoolAlbum");
				} else {
					recordUpdateService.saveOrUpdateRecord(album.gettMId(),
							"clazzAlbum");
				}
			}
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
