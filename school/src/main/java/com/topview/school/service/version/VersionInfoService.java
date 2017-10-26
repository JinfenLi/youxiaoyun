package com.topview.school.service.version;

import java.util.List;

import com.topview.school.po.VersionInfo;

public interface VersionInfoService {
	int deleteByPrimaryKey(String id);


    int insert(VersionInfo record);

    /**
     * @description 根据用户的手机号获取用户在数据库中的版本记录
     * @param id
     * @return
     */
    List<VersionInfo> selectByUserMobile(String userMobile);
}
