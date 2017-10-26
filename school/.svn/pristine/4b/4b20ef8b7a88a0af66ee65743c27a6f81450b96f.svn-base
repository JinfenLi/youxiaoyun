package com.topview.school.service.school.grade;

import java.util.List;
import java.util.Map;

import com.topview.school.po.GradeResources;

/**
 * 相册资源接口类
* @ClassName: GradeResourcesServiceImpl 
* @author lxd  <836696016@qq.com>
* @date 2015年8月13日 下午4:13:55 
* @version V1.0
 */
public interface GradeResourcesService{

	/**
	 * 根据属性值分页查找
	* @Title: getAllResources 
	* @param  params 要查询的属性
	* @return List<GradeResources> 
	* @throws
	 */
	//获取所有的资源
	public List<GradeResources> getAllResources(Map<String, Object> params);
	
	//获取每个年级对应的数目
	public int getCountByGrade(Map<String, Object> params);
	
	//获取学校的资源的总数
	public int getCountBySchool(Map<String,Object> params);
	
	//保存资源
	public int saveResource(GradeResources resoureces);
	
	//获取资源的类型
	public String getResourceType(String resourceId);
	
	//更新资源
	public boolean updateResource(GradeResources r); 
	
	//删除资源
	public boolean deleteResource(String resourceId);
	
	//获取id对应的资源
	public GradeResources getResourcesById(String resourceId);
	
}
