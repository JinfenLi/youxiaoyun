package com.topview.school.dao.school.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.GradeResourcesMapper;
import com.topview.school.po.GradeResources;
import com.topview.school.po.ResourceType;

/**
 * 年级资源接口实现类
* @ClassName: GradeResourcesMapperImpl 
* @author lxd  <836696016@qq.com>
* @date 2015年8月13日 下午3:42:17 
* @version V1.0
 */
@Repository
public class GradeResourcesMapperImpl extends BaseDaoImpl<GradeResources> implements GradeResourcesMapper{

	@Override
	public List<GradeResources> getAllResources(Map<String, Object> params) {
		return template.selectList(getStatement("getAllSchoolResource"), params);
	}

	@Override
	public int getCountByGrade(Map<String, Object> params) {
		return template.selectOne(getStatement("getCountByGrade"), params);
	}

	@Override
	public int addType(Map<String, Object> params) {
		return template.insert(getStatement("insertType"), params);
	}


	@Override
	public String getResourceType(String resourceId) {
		return template.selectOne(getStatement("getResourceType"),resourceId);
	}

	@Override
	public int getCountBySchool(Map<String, Object> params) {
		return template.selectOne(getStatement("getCountBySchool"), params);
	}

	@Override
	public GradeResources getResourcesById(String resourceId) {
		return template.selectOne(getStatement("selectByPrimaryKey"),resourceId);
	}



}
