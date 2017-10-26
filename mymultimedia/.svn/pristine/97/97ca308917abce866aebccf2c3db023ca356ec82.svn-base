/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月1日 上午10:08:10 
 * @version V1.0
 */
package com.topview.multimedia.service.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaAlbumMapper;
import com.topview.multimedia.po.MultimediaAlbum;
import com.topview.multimedia.vo.AlbumInfo;

/**
 * @ClassName: AlbumFindByNameProcess
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月1日 上午10:08:10
 * 
 */
@Service
public class AlbumFindByNameProcess implements AlbumProcess {
	@Resource
	private MultimediaAlbumMapper multimendiaAlbumMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumFindByNameProcess.class);

	public boolean doProcess(AlbumProcessContext context) {

		try {
			MultimediaAlbum Album = AlbumInfo.changeToPo(context.getInfo());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", Album.getName());
			params.put("t_m_id", Album.gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<MultimediaAlbum> list = multimendiaAlbumMapper
					.findByMulti(param);
			if (list.size() == 0) {
				context.getResult().setCode(0);
			} else {
				context.getResult().setCode(1);
				context.getResult().setInfoResult(AlbumInfo.changeToVo(list));
			}
			context.getResult().setSuccess(true);
			return true;

		} catch (Exception e) {
			logger.error("find album fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
