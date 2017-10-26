package com.topview.school.service.clazz.library.video;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

public class ClazzVideoServiceImpl implements ClazzVideoService {
	private List<ClazzVideoProcess> clazzVideoFindProcesses;
	private List<ClazzVideoProcess> clazzVideoFindNoPagerProcesses;
	private List<ClazzVideoProcess> clazzVideoFindAllProcesses;
	private List<ClazzVideoProcess> clazzVideoSaveProcesses;
	private List<ClazzVideoProcess> clazzVideoUpdateProcesses;

	@Override
	public VideoInfoResult getVideo(String videoId) {
		ClazzVideoProcessContext context = new ClazzVideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		VideoInfo info = new VideoInfo();
		info.setId(videoId);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzVideoProcess process : clazzVideoFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public VideoInfoResult getAllVideo(String libraryId, Pager pager) {
		ClazzVideoProcessContext context = new ClazzVideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		VideoInfo info = new VideoInfo();
		info.settMId(libraryId);
		info.setPager(pager);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzVideoProcess process : clazzVideoFindAllProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public VideoInfoResult getVideoNoPager(String libraryId) {
		ClazzVideoProcessContext context = new ClazzVideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		VideoInfo info = new VideoInfo();
		info.settMId(libraryId);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzVideoProcess process : clazzVideoFindNoPagerProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public VideoInfoResult saveVideo(VideoInfo info) {
		ClazzVideoProcessContext context = new ClazzVideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (ClazzVideoProcess process : clazzVideoSaveProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	public VideoInfoResult updateVideo(VideoInfo info) {
		ClazzVideoProcessContext context = new ClazzVideoProcessContext();
		VideoInfoResult result = new VideoInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (ClazzVideoProcess process : clazzVideoUpdateProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	public List<ClazzVideoProcess> getClazzVideoFindProcesses() {
		return clazzVideoFindProcesses;
	}

	public void setClazzVideoFindProcesses(
			List<ClazzVideoProcess> clazzVideoFindProcesses) {
		this.clazzVideoFindProcesses = clazzVideoFindProcesses;
	}

	public List<ClazzVideoProcess> getClazzVideoFindAllProcesses() {
		return clazzVideoFindAllProcesses;
	}

	public void setClazzVideoFindAllProcesses(
			List<ClazzVideoProcess> clazzVideoFindAllProcesses) {
		this.clazzVideoFindAllProcesses = clazzVideoFindAllProcesses;
	}

	public List<ClazzVideoProcess> getClazzVideoSaveProcesses() {
		return clazzVideoSaveProcesses;
	}

	public void setClazzVideoSaveProcesses(
			List<ClazzVideoProcess> clazzVideoSaveProcesses) {
		this.clazzVideoSaveProcesses = clazzVideoSaveProcesses;
	}

	public List<ClazzVideoProcess> getClazzVideoUpdateProcesses() {
		return clazzVideoUpdateProcesses;
	}

	public void setClazzVideoUpdateProcesses(
			List<ClazzVideoProcess> clazzVideoUpdateProcesses) {
		this.clazzVideoUpdateProcesses = clazzVideoUpdateProcesses;
	}

	public List<ClazzVideoProcess> getClazzVideoFindNoPagerProcesses() {
		return clazzVideoFindNoPagerProcesses;
	}

	public void setClazzVideoFindNoPagerProcesses(
			List<ClazzVideoProcess> clazzVideoFindNoPagerProcesses) {
		this.clazzVideoFindNoPagerProcesses = clazzVideoFindNoPagerProcesses;
	}

}
