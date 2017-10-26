package com.topview.school.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.Parent2StudentMapper;
import com.topview.school.po.Parent2Student;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：ParentMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:44:39  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:44:39  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class Parent2StudentMapperImpl extends BaseDaoImpl<Parent2Student> implements Parent2StudentMapper{

	@Override
	public int delete(String studentId, String parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("studentId", studentId);
		params.put("parentId", parentId);
		return template.delete(getStatement("delete"), params);
	}

}
