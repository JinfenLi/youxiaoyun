package com.topview.school.dao.curricula.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.curricula.CurriculaMapper;
import com.topview.school.po.Curricula;
import com.topview.school.vo.curricula.CurriculaVo;

/**
 * 
 * 项目名称：school <br>
 * 类名称：CurriculaMapperImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 下午8:29:14 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:29:14 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class CurriculaMapperImpl extends BaseDaoImpl<Curricula> implements
		CurriculaMapper {

	@Override
	public Curricula selectByNameAndSchoolId(String curriculaName,
			String schoolId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curriculaName", curriculaName);
		map.put("schoolId", schoolId);
		return template.selectOne(getStatement("selectByNameAndSchoolId"), map);
	}

	@Override
	public List<CurriculaVo> getCurriculaBySubjectId(String subjectId) {
		return template.selectList(getStatement("getCurriculaBySubjectId"),
				subjectId);
	}

	@Override
	public List<Curricula> getCurriculas(Map<String, Object> map) {
		return template.selectList(getStatement("getCurriculas"), map);
	}
	
}
