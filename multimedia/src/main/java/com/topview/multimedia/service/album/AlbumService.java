package com.topview.multimedia.service.album;

import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;

public interface AlbumService {
	
	/**
	 * 创建相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumCreate(AlbumInfo info);

	/**
	 * 删除相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumDelete(AlbumInfo info);

	/**
	 * 更新相册信息
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumUpdate(AlbumInfo info);

	/**
	 * 根据多媒体空间id分页查询所有相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumFindAll(AlbumInfo info);
	
	/**
	 * 根据多媒体空间id和相册类型查询相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumFindByType(AlbumInfo info);
	
	/**
	 * 根据多媒体空间id不分页查询相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumFindNoPager(AlbumInfo info);
	
	/**
	 * 根据相册名称查询相册
	 * @param info
	 * @return
	 */
	public AlbumInfoResult albumFindByName(AlbumInfo info);
	
	/**
	 * @dateTime 2016年7月27日下午1:56:37
	 * @author zjd
	 * @description 保存相册的封面
	 */
	public AlbumInfoResult albumSaveCover(AlbumInfo info);
	
	/**
	 * @dateTime 2016年7月28日下午9:48:30
	 * @author zjd
	 * @description 删除多个相册
	 */
	public AlbumInfoResult albumDeletes(AlbumInfo info);
	
	
	
	public AlbumInfoResult getAlubmById(AlbumInfo info);
}
