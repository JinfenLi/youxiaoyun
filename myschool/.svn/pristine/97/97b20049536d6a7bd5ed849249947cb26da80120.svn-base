package com.topview.school.service.version;

import java.util.List;

import com.topview.school.po.VersionUpdate;

/**
 * @Description 版本信息service层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 下午1:18:10
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface VersionUpdateService {

	/**
	 * 上传版本更新信息
	 * 
	 * @param versionUpdate
	 * @return
	 */
	public boolean insert(VersionUpdate versionUpdate);

	/**
	 * 根据版本和设备类型获取版本更新信息
	 * 
	 * @param schoolId
	 * @param device
	 * @return
	 */
	public List<VersionUpdate> getBySchoolIdAndDevice(String schoolId,
			String device);
}
