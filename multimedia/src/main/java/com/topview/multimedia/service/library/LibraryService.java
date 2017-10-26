package com.topview.multimedia.service.library;

import com.topview.multimedia.vo.LibraryInfo;
import com.topview.multimedia.vo.result.LibraryInfoResult;

public interface LibraryService {
	
	/**
	 * 创建视频库
	 * @param info
	 * @return
	 */
	public LibraryInfoResult libraryCreate(LibraryInfo info);
	
	/**
	 * 删除视频库
	 * @param info
	 * @return
	 */
	public LibraryInfoResult libraryDelete(LibraryInfo info);
	
	/**
	 * 修改视频库信息
	 * @param info
	 * @return
	 */
	public LibraryInfoResult libraryUpdate(LibraryInfo info);
	
	/**
	 * 分页查询所有视频库（pager不能为空）
	 * @param info
	 * @return
	 */
	public LibraryInfoResult libraryFindAll(LibraryInfo info);
	
	/**
	 * 不分页查询所有视频库
	 * @param info
	 * @return
	 */
	public LibraryInfoResult libraryFindNoPager(LibraryInfo info);
	
	/**
	 * 根据多媒体空间id查询视频库数量
	 * @param zoneId
	 * @return
	 */
	public int selectCount(String zoneId);
	
}
