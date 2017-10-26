package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Area;

public interface AreaMapper extends BaseDao<Area> {

	/**
	 * 根据上级区域id查询下属区域id
	 * @param parentId
	 * @return
	 */
	public List<Area> findJuniorArea(Integer parentId, Integer leave);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Area selectByPrimaryKey(Integer id);
}
