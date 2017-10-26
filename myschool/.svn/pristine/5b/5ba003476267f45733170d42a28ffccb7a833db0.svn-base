package com.topview.school.dao.version.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.version.VersionInfoMapper;
import com.topview.school.po.VersionInfo;

@Repository
public class VersionInfoMapperImpl extends BaseDaoImpl<VersionInfo> implements VersionInfoMapper {

	@Override
	public int deleteByPrimaryKey(String id) {
		return template.delete(getStatement("deleteByPrimaryKey"));
	}

	@Override
	public int insert(VersionInfo record) {
		return template.insert(getStatement("insert"),record);
	}


	@Override
	public List<VersionInfo> selectByUserMobile(String userMobile) {
			return template.selectList(getStatement("selectByUserMobile"),userMobile);
	}

}
