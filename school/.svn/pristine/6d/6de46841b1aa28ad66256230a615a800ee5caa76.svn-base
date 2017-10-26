package com.topview.school.dao.payment;

import java.util.Date;
import java.util.Map;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.Payment;

/**
 * 
* @ClassName: PaymentMapper
* @Description: 账号付费Mapper
* @author Catalyst
* @date 2016年1月21日
*
 */
public interface PaymentMapper extends BaseDao<Payment> {

	/**
	 * 
	* @Title: selectByParentIdAndStudentId
	* @Description: 通过家长id和学生id查找
	* @param @param param
	* @param @return    参数
	* @return Payment    返回类型
	* @throws
	 */
	public Payment selectByParentIdAndStudentId(Map<String, Object> param);

	/**
	 * 
	* @Title: updateDeadlineByParentIdAndStudentId
	* @Description: 通过家长id和学生id更新有效期（具体到日）
	* @param @param param
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public int updateDeadlineByParentIdAndStudentId(Map<String, Object> param);
	
	/**
	 * 
	* @Title: deleteOverdueByDate
	* @Description: 通过日期删除该日期以前已过期的所有记录（具体到日）
	* @param @param date
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public int deleteOverdueByDate(Date date);
}