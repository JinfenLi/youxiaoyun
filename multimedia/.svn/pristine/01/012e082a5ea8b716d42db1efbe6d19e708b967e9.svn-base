package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.EvaluationTemplate;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午8:20:45 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Repository
public class EvaluationTemplateMapperImpl extends BaseDaoImpl<EvaluationTemplate> implements
		EvaluationTemplateMapper {

	@Override
	public int selectCount(String uploaderId) {
		return template.selectOne(getFirstInterface() + ".selectCount", uploaderId);
	}

	

}
