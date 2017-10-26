package com.topview.school.dao.curricula.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.curricula.CurriculaTimeMapper;
import com.topview.school.po.CurriculaTime;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：CurriculaTimeMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:40:56  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:40:56  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class CurriculaTimeMapperImpl extends BaseDaoImpl<CurriculaTime> implements CurriculaTimeMapper{

	@Override
	public List<CurriculaTime> selectByTscCurriculaVariableId(String id) {
			return template.selectList(getStatement("selectByTscCurriculaVariableId"), id);
		}

	@Override
	public int deleteByCurriculaVariableId(String curriculaVariableId) {
		return template.delete(getStatement("deleteByCurriculaVariableId"), curriculaVariableId);
	}
}
