/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年9月4日 下午4:15:35 
 * @version V1.0
 */
package com.topview.school.dao.appraise;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.AppraiseSubjectTemplate;

/** 
 * @ClassName: AppraiseSubjectTemplate 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年9月4日 下午4:15:35 
 *  
 */
public interface AppraiseSubjectTemplateMapper extends BaseDao<AppraiseSubjectTemplate>{

	
	public List<AppraiseSubjectTemplate> findByMulti(Map<String, Object> params);
    
	public int updateByPrimaryKeySelective(AppraiseSubjectTemplate info);

}
