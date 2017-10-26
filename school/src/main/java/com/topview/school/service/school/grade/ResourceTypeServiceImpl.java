package com.topview.school.service.school.grade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.ResourceTypeMapper;
import com.topview.school.po.ResourceType;
import com.topview.school.util.UUIDUtil;

/**
 * 资源类型service层接口实现类
 * @ClassName: ResourceTypeServiceImpl 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月31日 下午8:58:28 
 * @version V1.0
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService{

	@Resource
	private ResourceTypeMapper resourceTypeMapper;
	
	@Override
	public boolean saveType(ResourceType type) {
		if(type == null){
			return false;
		}
		type.setId(UUIDUtil.getUUID());
		return resourceTypeMapper.insertSelective(type) > 0 ? true : false;
	}

	@Override
	public boolean updateType(ResourceType type) {
		
		return resourceTypeMapper.updateByPrimaryKeySelective(type) > 0 ? true : false;
	}

	@Override
	public boolean deleteType(String id) {
		return resourceTypeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public List<ResourceType> getAllType(String schoolId) {
		return resourceTypeMapper.getAllType(schoolId);
	}

	@Override
	public ResourceType getById(String id) {
		return resourceTypeMapper.selectByPrimaryKey(id);
	}



}
