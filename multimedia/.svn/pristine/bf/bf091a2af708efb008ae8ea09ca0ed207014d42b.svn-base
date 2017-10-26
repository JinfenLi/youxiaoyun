package com.topview.multimedia.service.album.photo;

import com.topview.multimedia.vo.PhotoInfo;
import com.topview.multimedia.vo.result.PhotoInfoResult;

public interface PhotoService {
	
	/**
	 * 保存图片
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoSave(PhotoInfo photo);

	/**
	 * 删除图片
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoDelete(PhotoInfo photo);

	/**
	 * 根据相册id分页查询图片
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoFindAll(PhotoInfo photo);

	/**
	 * 根据图片id查询图片
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoFind(PhotoInfo photo);

	/**
	 * 修改图片信息
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoUpdate(PhotoInfo photo);

	/**
	 * 修改图片name属性
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoUpdateName(PhotoInfo photo);
	
	/**
	 * @dateTime 2016年7月25日下午2:41:18
	 * @author zjd
	 * @description 修改图片的顺序
	 */
	public PhotoInfoResult photoUpdateSortAndDescription(PhotoInfo phott);

	/**
	 * 根据相册id不分页查询图片
	 * @param photo
	 * @return
	 */
	public PhotoInfoResult photoFindNoPager(PhotoInfo photo);

	/**
	 * 根据相册id查询相册中已通过审核图片数目
	 * @param albumId
	 * @return
	 */
	public int selectCount(String albumId);
}
