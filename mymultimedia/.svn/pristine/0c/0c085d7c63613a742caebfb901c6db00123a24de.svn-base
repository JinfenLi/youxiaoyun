package com.topview.multimedia.service.album.photo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaPhotoMapper;
import com.topview.multimedia.po.MultimediaPhoto;
import com.topview.multimedia.vo.PhotoInfo;

/**
 * 查找全部图片 项目名称:com.topview.multimedia.service.album.photo<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class PhotoFindAllProcess implements PhotoProcess {
	private static final Logger logger = Logger
			.getLogger(PhotoFindAllProcess.class);
	@Autowired
	private MultimediaPhotoMapper multimediaPhotoMapper;

	public boolean doProcess(PhotoProcessContext context) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			List<MultimediaPhoto> l = multimediaPhotoMapper.findByMulti(param);
			List<PhotoInfo> list = PhotoInfo.changeToVo(l);
			context.getResult().setInfoResult(list);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("find photo success");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
