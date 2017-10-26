package com.topview.school.service.school.grade;

import java.util.List;

import com.topview.school.po.ResourceType;

/**
 * 资源类型service层接口
 * @ClassName: ResourceTypeService 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月31日 下午8:50:41 
 * @version V1.0
 */
public interface ResourceTypeService {

	public boolean saveType(ResourceType type);
	
	public boolean updateType(ResourceType type);
	
	public boolean deleteType(String id);
	
	public ResourceType getById(String id);
	
	public List<ResourceType> getAllType(String schoolId);
}
