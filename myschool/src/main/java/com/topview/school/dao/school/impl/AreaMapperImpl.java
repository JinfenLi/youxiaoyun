package com.topview.school.dao.school.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.AreaMapper;
import com.topview.school.po.Area;

/**
 * @Description 区域dao
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年9月13日 下午1:15:56
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Repository
public class AreaMapperImpl extends BaseDaoImpl<Area> implements AreaMapper {

	@Override
	public List<Area> findJuniorArea(Integer parentId, Integer leave) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("leave", leave);
		return template.selectList(getStatement("findJuniorArea"), params);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		return template.selectOne(getStatement("selectByPrimaryKey"), id);
	}
	
}
