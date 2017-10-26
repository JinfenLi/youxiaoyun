package com.topview.school.dao.school.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.SchoolMapper;
import com.topview.school.po.School;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：SchoolMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:46:25  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:46:25  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class SchoolMapperImpl extends BaseDaoImpl<School> implements SchoolMapper{

	/**
	 * 分页查询学校
	 */
	public List<School> selectAll(int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("limit", limit);
		return template.selectList(getStatement("selectAll"), map);
	}

	/**
	 * 模糊查询学校
	 */
	public List<School> selectSchoolLike(String name) {
		return template.selectList(getStatement("selectSchoolLike"), name);
	}

	@Override
	public List<School> selectByParentPhone(String mobile) {
		return template.selectList(getStatement("selectByParentPhone"), mobile);
	}

	@Override
	public List<School> findByAreaId(Integer areaId) {
		return template.selectList(getStatement("findByAreaId"), areaId);
	}
}
