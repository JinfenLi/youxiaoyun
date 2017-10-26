package com.topview.multimedia.service.album;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.vo.AlbumInfo;

/**
 * @Description 根据相册id删除相册下的所有图片
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月9日 下午10:11:45
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class PhotoDeleteByAlbumIdProcess implements AlbumProcess {

	@Resource
	private MultimediaPhotoMapper multimediaPhotoMapper;

	public boolean doProcess(AlbumProcessContext context) {
		AlbumInfo info = context.getInfo();
		if (info != null) {
			multimediaPhotoMapper.deleteByTmid(info.getId());
		}
		return true;
	}

}
