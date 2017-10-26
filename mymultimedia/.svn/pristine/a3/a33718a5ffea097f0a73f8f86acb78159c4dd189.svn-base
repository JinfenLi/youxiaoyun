package com.topview.multimedia.service.album;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;
/**
 * 相册更新
 * 项目名称:com.topview.multimedia.service.album<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class AlbumUpdateProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumUpdateProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		AlbumInfoResult result = new AlbumInfoResult();
		try {
			MultimediaAlbum album = AlbumInfo.changeToPo(context.getInfo());
			if(multimendiaAlbumMapper.updateByPrimaryKeySelective(album) > 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("update album fail" + e.getMessage());
			e.printStackTrace();
			result.setSuccess(false);
			context.setResult(result);
			return false;
		}
	}

}
