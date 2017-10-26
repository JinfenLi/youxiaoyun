package com.topview.multimedia.service.library.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.dao.MultimediaZoneMapper;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.po.MultimediaZone;
@Service
public class VideoCheckProcess implements VideoProcess{
	@Autowired
	private MultimediaZoneMapper multimediaZoneMapper;
	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(VideoProcessContext context) {
		try {
			MultimediaZone zone = multimediaZoneMapper.findById(context
					.getInfo().getZoneId());
			MultimediaVideoLibrary library = multimediaVideoLibraryMapper.findById(context
					.getInfo().gettMId());
			if (zone != null && library != null&&zone.getId().equals(library.gettMId())) {
				context.getResult().setSuccess(true);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}
}
