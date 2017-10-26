package com.topview.school.service.version.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.version.VersionInfoMapper;
import com.topview.school.po.VersionInfo;
import com.topview.school.service.version.VersionInfoService;

@Service
public class VersionInfoServiceImpl implements VersionInfoService{
	
	@Resource
	private VersionInfoMapper versionInfoMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.versionInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(VersionInfo record) {
		return this.versionInfoMapper.insert(record);
	}
 

	@Override
	public List<VersionInfo> selectByUserMobile(String userMobile) {
		return this.versionInfoMapper.selectByUserMobile(userMobile);
	}
		
}
