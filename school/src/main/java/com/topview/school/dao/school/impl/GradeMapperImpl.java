package com.topview.school.dao.school.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.school.GradeMapper;
import com.topview.school.po.Grade;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：GradeMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:44:05  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:44:05  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class GradeMapperImpl extends BaseDaoImpl<Grade> implements GradeMapper{

	@Override
	public List<Grade> getAllGrade(String schoolId) {
		return template.selectList(getStatement("getAllGrade"), schoolId);
	}

	@Override
	public List<Grade> isExist(String schoolId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schoolId", schoolId);
		map.put("name", name);
		return template.selectList(getStatement("isExist"), map);
	}

}
