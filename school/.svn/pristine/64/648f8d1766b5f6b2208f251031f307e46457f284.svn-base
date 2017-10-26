package com.topview.school.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.ShowFeaturesMapper;
import com.topview.school.po.ShowFeatures;


@Repository
public class ShowFeaturesMapperImpl  extends BaseDaoImpl<ShowFeatures> implements ShowFeaturesMapper {

	@Override
	public ShowFeatures getUserReadStatus(String userId) {
		return template.selectOne(getStatement("getUserReadStatus"), userId);
	}

	@Override
	public int udpateUserReadStatus(ShowFeatures showFeatures) {
		return template.update(getStatement("udpateUserReadStatus"),showFeatures);
	}

}
