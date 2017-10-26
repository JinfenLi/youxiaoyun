package com.topview.school.dao.version.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.version.VersionUpdateMapper;
import com.topview.school.po.VersionUpdate;

@Repository
public class VersionUpdateMapperImpl extends BaseDaoImpl<VersionUpdate>
		implements VersionUpdateMapper {

	@Override
	public List<VersionUpdate> selectBySchoolIdAndDevice(String schoolId,
			String device) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schoolId", schoolId);
		map.put("device", device);
		return template.selectList(getStatement("selectBySchoolIdAndDevice"),
				map);
	}

}
