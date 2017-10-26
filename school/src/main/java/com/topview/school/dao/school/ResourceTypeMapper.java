package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.ResourceType;

/**
 * 资源名称dao层接口
 * @ClassName: ResourceTypeMapper 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月31日 下午8:45:20 
 * @version V1.0
 */
public interface ResourceTypeMapper extends BaseDao<ResourceType>{
	
	public List<ResourceType> getAllType(String schoolId);
}