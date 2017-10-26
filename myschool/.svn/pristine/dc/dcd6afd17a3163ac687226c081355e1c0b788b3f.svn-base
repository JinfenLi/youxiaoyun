package com.topview.school.service.version.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.version.VersionUpdateMapper;
import com.topview.school.po.VersionUpdate;
import com.topview.school.service.version.VersionUpdateService;

@Service
public class VersionUpdateServiceImpl implements VersionUpdateService {

	@Resource
	private VersionUpdateMapper versionUpdateMapper;

	@Override
	public boolean insert(VersionUpdate versionUpdate) {
		return versionUpdateMapper.insert(versionUpdate) > 0 ? true : false;
	}

	@Override
	public List<VersionUpdate> getBySchoolIdAndDevice(String schoolId,
			String device) {
		return versionUpdateMapper.selectBySchoolIdAndDevice(schoolId, device);
	}

}
