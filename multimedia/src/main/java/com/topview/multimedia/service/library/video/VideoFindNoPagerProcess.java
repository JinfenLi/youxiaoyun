package com.topview.multimedia.service.library.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.po.MultimediaVideo;
import com.topview.multimedia.vo.VideoInfo;
/**
 * 查找全部视频
 * 项目名称:com.topview.multimedia.service.library.video<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class VideoFindNoPagerProcess implements VideoProcess {

	@Autowired
	private MultimediaVideoMapper multimendiaVideoMapper;

	public boolean doProcess(VideoProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<MultimediaVideo> l = multimendiaVideoMapper.findByMulti0(param);
			List<VideoInfo> infos = VideoInfo.changeToVo(l);
			context.getResult().setSuccess(true);
			context.getResult().setResult(infos);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
