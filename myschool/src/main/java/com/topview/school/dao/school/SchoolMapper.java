package com.topview.school.dao.school;

import java.util.List;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.School;

public interface SchoolMapper extends BaseDao<School>{

	/**
	 * 分页查询学校
	 */
	public List<School> selectAll(int offset, int limit);
		
	/**
	 * 模糊查询学校
	 * @param map
	 * @return
	 */
	public List<School> selectSchoolLike(String name);
	
	/**
	 * 根据家长手机号码查询学校
	 * @param mobile
	 * @return
	 */
	public List<School> selectByParentPhone(String mobile);

	/**
	 * 根据区域id查询学校
	 * @param areaId
	 * @return
	 */
	public List<School> findByAreaId(Integer areaId);
}