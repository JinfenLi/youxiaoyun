package com.topview.multimedia.service.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.vo.AlbumInfo;

/**
 * 查找全部相册 项目名称:com.topview.multimedia.service.album<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class AlbumFindNoPagerProcess implements AlbumProcess {

	@Autowired
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumFindNoPagerProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		try {
			MultimediaAlbum album = AlbumInfo.changeToPo(context.getInfo());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", album.gettMId());
			if (album.getType() != null && !"".equals(album.getType())) {
				params.put("type", album.getType());
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<MultimediaAlbum> l = multimendiaAlbumMapper
					.findByMulti0(param);
			context.getResult().setSuccess(true);
			if (l != null) {
				context.getResult().setInfoResult(AlbumInfo.changeToVo(l));
			}
			return true;
		} catch (Exception e) {
			logger.error("find all album fail");
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
