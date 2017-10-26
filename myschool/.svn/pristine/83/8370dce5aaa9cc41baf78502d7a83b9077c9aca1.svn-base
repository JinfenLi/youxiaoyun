package com.topview.school.dao.school;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.GradeResources;
import com.topview.school.po.ResourceType;

/**
 * 年级资源类
* @ClassName: GradeResourcesMapper 
* @author lxd  <836696016@qq.com>
* @date 2015年8月13日 下午3:35:52 
* @version V1.0
 */
public interface GradeResourcesMapper extends BaseDao<GradeResources>{
    
	/**
	 * 根据属性值分页查找
	* @Title: getAllResources 
	* @param  params 要查询的属性
	* @return List<GradeResources> 
	* @throws
	 */
	public List<GradeResources> getAllResources(Map<String, Object> params);
	
	public int addType(Map<String,Object> params);
	
	public int getCountByGrade(Map<String, Object> params);
	
	public int getCountBySchool(Map<String,Object> params);
	
	public String getResourceType(String resourceId);
	
	public GradeResources getResourcesById(String resourceId);
}