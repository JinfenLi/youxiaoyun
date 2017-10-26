package com.topview.multimedia.service.library.video;

import java.util.List;

import javax.annotation.Resource;

import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

public class VideoServiceImpl implements VideoService {

	private List<VideoProcess> videoSaveProcesses;
	private List<VideoProcess> videoDeleteProcesses;
	private List<VideoProcess> videoFindAllProcesses;
	private List<VideoProcess> videoFindProcesses;
	private List<VideoProcess> videoFindNoPagerProcesses;
	private List<VideoProcess> videoUpdateProcesses;
	@Resource
	private MultimediaVideoMapper multimediaVideoMapper;

	public VideoInfoResult videoSave(VideoInfo info) {
		return doProcess(info, videoSaveProcesses);
	}

	public VideoInfoResult videoDelete(VideoInfo info) {
		return doProcess(info, videoDeleteProcesses);
	}

	public VideoInfoResult videoUpdate(VideoInfo info) {
		return doProcess(info, videoUpdateProcesses);
	}

	public VideoInfoResult videoFindAll(VideoInfo info) {
		return doProcess(info, videoFindAllProcesses);
	}
	public VideoInfoResult videoFind(VideoInfo info) {
		return doProcess(info, videoFindProcesses);
	}
	public VideoInfoResult videoFindNoPager(VideoInfo info) {
		return doProcess(info, videoFindNoPagerProcesses);
	}
	public List<VideoProcess> getVideoSaveProcesses() {
		return videoSaveProcesses;
	}

	public void setVideoSaveProcesses(List<VideoProcess> videoSaveProcesses) {
		this.videoSaveProcesses = videoSaveProcesses;
	}

	public List<VideoProcess> getVideoDeleteProcesses() {
		return videoDeleteProcesses;
	}

	public void setVideoDeleteProcesses(List<VideoProcess> videoDeleteProcesses) {
		this.videoDeleteProcesses = videoDeleteProcesses;
	}

	public List<VideoProcess> getVideoFindAllProcesses() {
		return videoFindAllProcesses;
	}

	public void setVideoFindAllProcesses(
			List<VideoProcess> videoFindAllProcesses) {
		this.videoFindAllProcesses = videoFindAllProcesses;
	}

	public List<VideoProcess> getVideoUpdateProcesses() {
		return videoUpdateProcesses;
	}

	public void setVideoUpdateProcesses(List<VideoProcess> videoUpdateProcesses) {
		this.videoUpdateProcesses = videoUpdateProcesses;
	}

	public List<VideoProcess> getVideoFindProcesses() {
		return videoFindProcesses;
	}

	public void setVideoFindProcesses(List<VideoProcess> videoFindProcesses) {
		this.videoFindProcesses = videoFindProcesses;
	}

	public List<VideoProcess> getVideoFindNoPagerProcesses() {
		return videoFindNoPagerProcesses;
	}

	public void setVideoFindNoPagerProcesses(
			List<VideoProcess> videoFindNoPagerProcesses) {
		this.videoFindNoPagerProcesses = videoFindNoPagerProcesses;
	}

	public VideoInfoResult doProcess(VideoInfo info,
			List<VideoProcess> processes) {
		VideoProcessContext context = new VideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (VideoProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 根据视频库id查询视频数量
	 * @param libraryId
	 * @return
	 */
	public int selectCount(String libraryId) {
		return multimediaVideoMapper.selectCount(libraryId);
	}


}
