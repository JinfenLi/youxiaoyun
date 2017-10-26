package com.topview.multimedia.service.album;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.vo.AlbumInfo;
/**
 * 查找全部相册
 * 项目名称:com.topview.multimedia.service.album<br>
 * 
 * @author dr<br>
 * 创建时间:2016.10.12<br>
 */
@Service
public class AlbumFindByIdProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumFindAllProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		try {
			MultimediaAlbum album= multimendiaAlbumMapper.selectByPrimaryKey(context.getInfo().getId());
			context.getResult().setSuccess(true);
			if (album != null) {
				List<MultimediaAlbum> result = new ArrayList<MultimediaAlbum>();
				result.add(album);
				context.getResult().setInfoResult(AlbumInfo.changeToVo(result));
			}
			return true;
		} catch (Exception e) {
			logger.error("find all album fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
