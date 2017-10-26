package com.topview.multimedia.service.album;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaZoneMapper;
import com.topview.multimedia.po.MultimediaZone;
/**
 * 检查信息是否通过
 * 项目名称:com.topview.multimedia.service.album<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class AlbumCheckProcess implements AlbumProcess {
	@Resource
	private MultimediaZoneMapper multimediaZoneMapper;
	private static final Logger logger = Logger
			.getLogger(AlbumCheckProcess.class);

	public boolean doProcess(AlbumProcessContext context) {
		try {
			MultimediaZone zone = multimediaZoneMapper.findById(context
					.getInfo().gettMId());
			if (zone != null) {
				context.getResult().setSuccess(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("check album fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
