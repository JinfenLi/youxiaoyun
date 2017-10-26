package com.topview.school.service.clazz.library.video;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

public interface ClazzVideoService {
	public VideoInfoResult getAllVideo(String libraryId, Pager pager);

	public VideoInfoResult getVideo(String videoId);
	public VideoInfoResult getVideoNoPager(String libraryId);
	
	public VideoInfoResult saveVideo(VideoInfo info);
	
	public VideoInfoResult updateVideo(VideoInfo info);
}
