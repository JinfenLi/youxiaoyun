/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午4:17:44 
 * @version V1.0
 */
package com.topview.school.dao.appraise.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.appraise.AppraiseSubjectTemplateMapper;
import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.po.AppraiseSubjectTemplate;

/** 
 * @ClassName: AppraiseTemplateMapperAppraiseTemplateMapper 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午4:17:44 
 *  
 */
@Repository
public class AppraiseSubjectTemplateMapperImpl extends BaseDaoImpl<AppraiseSubjectTemplate> implements 
                    AppraiseSubjectTemplateMapper{

	private String path = "com.topview.school.dao.AppraiseSubjectTemplateMapper";

	@Override
	public List<AppraiseSubjectTemplate> findByMulti(Map<String, Object> params) {
		return template.selectList(path + ".findByMulti", params);
	}

	
	
}
