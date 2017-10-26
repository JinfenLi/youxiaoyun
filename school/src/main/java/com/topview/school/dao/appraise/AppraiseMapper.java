/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午8:08:34 
 * @version V1.0
 */
package com.topview.school.dao.appraise;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Appraise;

/**
 * @ClassName: AppraiseMapper
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午8:08:34
 * 
 */
public interface AppraiseMapper extends BaseDao<Appraise> {

	/**
	 * 多条件查询
	 * @param params
	 * @return
	 */
	public List<Appraise> findByMulti(Map<String, Object> params);
 
	/**
	 * 通过学生ID和学期ID查询
	 * @param studentid
	 * @param semester
	 * @return
	 */
	public Appraise findByStudentidAndSemester(String studentid, String semester);

	/**
	 * 通过学生学号进行排序获取评价
	 * @param params
	 * @return
	 */
	public List<Appraise> getappraisesByIDCard(Map<String, Object> params);
	
	
	public boolean deleteAppraiseByStudent(String studentId);
	
	
}
