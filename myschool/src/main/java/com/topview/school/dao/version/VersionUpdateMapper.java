package com.topview.school.dao.version;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.VersionUpdate;

/**
 * @Description 版本信息dao
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 下午1:15:27
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface VersionUpdateMapper extends BaseDao<VersionUpdate>{

	/**
	 * 根据学校id和设备类型获取版本更新信息
	 * @param schoolId
	 * @param device
	 * @return
	 */
	public List<VersionUpdate> selectBySchoolIdAndDevice(String schoolId, String device);

}
