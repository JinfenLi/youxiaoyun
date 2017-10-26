package com.topview.school.dao.curricula;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.CurriculaAddition;

/**
 * @Description 课程表附加表
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年9月4日 下午10:52:06
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface CurriculaAdditionMapper extends BaseDao<CurriculaAddition> {
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(int id);
	
	/**
	 * 根据学期id查询课表附加项
	 * @param schoolId
	 * @return
	 */
	public CurriculaAddition selectBySemesterId(String semesterId);
}
