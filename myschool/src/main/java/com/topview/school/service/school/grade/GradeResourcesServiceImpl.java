package com.topview.school.service.school.grade;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.GradeResourcesMapper;
import com.topview.school.po.GradeResources;

/**
 * 年级资源
* @ClassName: GradeResourcesServiceImpl 
* @author lxd  <836696016@qq.com>
* @date 2015年8月13日 下午4:30:27 
* @version V1.0
 */
@Service
public class GradeResourcesServiceImpl implements GradeResourcesService{

	@Resource
	private GradeResourcesMapper gradeResourcesMapper;

	@Override
	public List<GradeResources> getAllResources(Map<String, Object> params) {
		return gradeResourcesMapper.getAllResources(params);
	}

	@Override
	public int getCountByGrade(Map<String, Object> params) {
		return gradeResourcesMapper.getCountByGrade(params);
	}

	@Override
	public int saveResource(GradeResources resoureces) {
		return gradeResourcesMapper.insert(resoureces);
	}

	@Override
	public String getResourceType(String resourceId) {
		return gradeResourcesMapper.getResourceType(resourceId);
	}

	@Override
	public boolean updateResource(GradeResources r) {
		return gradeResourcesMapper.updateByPrimaryKeySelective(r) > 0 ? true : false;
	}

	@Override
	public boolean deleteResource(String resourceId) {
		return gradeResourcesMapper.deleteByPrimaryKey(resourceId)>0?true:false;
	}

	@Override
	public int getCountBySchool(Map<String, Object> params) {
		return gradeResourcesMapper.getCountBySchool(params);
	}

	@Override
	public GradeResources getResourcesById(String resourceId) {
			return gradeResourcesMapper.getResourcesById(resourceId);
	}
	

}
