package com.topview.multimedia.service.library.video;

import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

public class VideoProcessContext {

	private VideoInfo info;
	private VideoInfoResult result;

	public VideoInfo getInfo() {
		return info;
	}

	public void setInfo(VideoInfo info) {
		this.info = info;
	}

	public VideoInfoResult getResult() {
		return result;
	}

	public void setResult(VideoInfoResult result) {
		this.result = result;
	}

}
