package com.topview.school.dao.school.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.ResourceTypeMapper;
import com.topview.school.po.ResourceType;

/**
 * 资源类型接口实现类
 * @ClassName: ResourceTypeMapperImpl 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月31日 下午8:46:58 
 * @version V1.0
 */
@Repository
public class ResourceTypeMapperImpl extends BaseDaoImpl<ResourceType> implements ResourceTypeMapper{

	@Override
	public List<ResourceType> getAllType(String schoolId) {
		return template.selectList(getStatement("getAllType"),schoolId);
	}

}
