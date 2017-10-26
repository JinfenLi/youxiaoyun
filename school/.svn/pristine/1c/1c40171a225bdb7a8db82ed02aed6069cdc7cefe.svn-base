/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:09:53 
 * @version V1.0
 */
package com.topview.school.dao.appraise.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.po.Appraise;

/**
 * @ClassName: AppraiseMapperImpl
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:09:53
 * 
 */
@Repository
public class AppraiseMapperImpl extends BaseDaoImpl<Appraise> implements
		AppraiseMapper {

	private String path = "com.topview.school.dao.AppraiseMapper";

	@Override
	public List<Appraise> findByMulti(Map<String, Object> params) {

		return template.selectList(path + ".findByMulti", params);
	}

	@Override
	public Appraise findByStudentidAndSemester(String studentid, String semester) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentid);
		map.put("semester", semester);
		return template.selectOne(getStatement("findByStudentidAndSemester"),
				map);
	}

	
	@Override
	public List<Appraise> getappraisesByIDCard(Map<String, Object> map) {
		return template.selectList(getStatement("getappraisesByIDCard"), map);
	}

	@Override
	public boolean deleteAppraiseByStudent(String studentId) {
		return template.delete(getStatement("deleteAppraiseByStudentId"),studentId)>0;
	}
}
