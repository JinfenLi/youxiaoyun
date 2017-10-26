package com.topview.multimedia.service.library.video;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.po.MultimediaVideo;
import com.topview.multimedia.vo.VideoInfo;
/**
 * 查找视频
 * 项目名称:com.topview.multimedia.service.library.video<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class VideoFindProcess implements VideoProcess{

	@Autowired
	private MultimediaVideoMapper multimediaVideoMapper;

	public boolean doProcess(VideoProcessContext context) {

		try {
			MultimediaVideo video = multimediaVideoMapper.findById(context.getInfo().getId());
			List<VideoInfo> infos = new ArrayList<VideoInfo>();
			infos.add(VideoInfo.changeToVo(video));
			context.getResult().setResult(infos);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
