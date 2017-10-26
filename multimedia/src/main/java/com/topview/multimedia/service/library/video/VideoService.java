package com.topview.multimedia.service.library.video;

import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;

public interface VideoService {
	
	/**
	 * 保存视频
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoSave(VideoInfo info);
	
	/**
	 * 删除视频
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoDelete(VideoInfo info);
	
	/**
	 * 更新视频信息
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoUpdate(VideoInfo info);
	
	/**
	 * 根据视频库id分页查询视频（pager不能为空）
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoFindAll(VideoInfo info);
	
	/**
	 * 根据视频id查找视频
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoFind(VideoInfo info);
	
	/**
	 * 根据视频库id不分页查询所有视频
	 * @param info
	 * @return
	 */
	public VideoInfoResult videoFindNoPager(VideoInfo info);
	
	/**
	 * 根据视频库id查询视频库中视频的数量
	 * @param libraryId
	 * @return
	 */
	public int selectCount(String libraryId);
}
