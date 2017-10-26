package com.topview.multimedia.service.album;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.AlbumInfo;

/**
 * 
 * @author hcdn 保存相册封面
 */
@Service
public class AlbumSaveCoverProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	@Resource
	private RecordUpdateService recordUpdateService;
	private static final Logger logger = Logger
			.getLogger(AlbumSaveCoverProcess.class);
	

	public boolean doProcess(AlbumProcessContext context) {
		MultimediaAlbum multimediaAlbum = AlbumInfo.changeToPo(context.getInfo());
		multimediaAlbum.setCreateTime(new Date());
		multimediaAlbum.setUpdateTime(new Date());
		multimediaAlbum.setPhotoCount2(0);
		if(null != multimendiaAlbumMapper.findById(multimediaAlbum.getId())) {
			if(multimendiaAlbumMapper.updateByPrimaryKeySelective(multimediaAlbum) != 1) {
				logger.error("更新相册封面不成功");
				context.getResult().setSuccess(false);
				return false;
			}
		}else {
			multimediaAlbum.setId(UUIDUtil.getUUID());
			if(multimendiaAlbumMapper.insert(multimediaAlbum) > 0) {
				switch(context.getInfo().getType()) {
				case "1":
					recordUpdateService.saveOrUpdateRecord(multimediaAlbum.gettMId(), "ViewPager");
					break;
				case "2":
					recordUpdateService.saveOrUpdateRecord(multimediaAlbum.gettMId(), "");
					break;
				case "3":
					recordUpdateService.saveOrUpdateRecord(multimediaAlbum.gettMId(), "");
					break;
				default:
					break;
				}
			}else {
				logger.error("添加相册封面不成功");
				context.getResult().setSuccess(false);
				return false;
			}
		}
		context.getResult().setSuccess(true);
		return true;
	}

}
