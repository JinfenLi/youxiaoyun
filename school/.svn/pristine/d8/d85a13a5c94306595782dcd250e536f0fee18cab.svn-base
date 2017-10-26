package com.topview.school.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.TeacherPositionMapper;
import com.topview.school.po.TeacherPosition;

/**
 * 
 * 项目名称：school <br>
 * 类名称：TeacherPositionMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:33:03 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:33:03 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class TeacherPositionMapperImpl extends BaseDaoImpl<TeacherPosition>
		implements TeacherPositionMapper {

	@Override
	public List<TeacherPosition> getPositions(String teacher_id) {
		return template.selectList(getStatement("getPositions"), teacher_id);
	}

	@Override
	public int getCount(String schoolId) {
		return template.selectOne("getAllCount", schoolId);
	}

	@Override
	public List<TeacherPosition> getAllBySchoolId(int offset, int limit,
			String schoolId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("limit", limit);
		map.put("schoolId", schoolId);
		return template.selectList(getStatement("getAllBySchoolId"), map);
	}

}
