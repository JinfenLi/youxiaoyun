package com.topview.school.service.payment;

import java.util.Date;

import com.topview.school.po.Payment;

public interface PaymentService {

	/**
	 * 
	* @Title: selectById
	* @Description: 通过id查找
	* @param @param id
	* @param @return    参数
	* @return Payment    返回类型
	* @throws
	 */
	public Payment selectById(String id);
	
	/**
	 * 
	* @Title: selectByParentIdAndStudentId
	* @Description: 通过家长id和学生id查找
	* @param @param parentId
	* @param @param studentId
	* @param @return    参数
	* @return Payment    返回类型
	* @throws
	 */
	public Payment selectByParentIdAndStudentId(String parentId, String studentId);
	
	/**
	 * 
	* @Title: updateDeadlineById
	* @Description: 通过id更新有效期（具体到日）
	* @param @param id
	* @param @param deadline
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean updateDeadlineById(String id, Date deadline);
	
	/**
	 * 
	* @Title: updateDeadlineByParentIdAndStudentId
	* @Description: 通过家长id和学生id更新有效期（具体到日）
	* @param @param parentId
	* @param @param studentId
	* @param @param deadline
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean updateDeadlineByParentIdAndStudentId(String parentId, String studentId, Date deadline);
	
	/**
	 * 
	* @Title: deleteById
	* @Description: 通过id删除
	* @param @param id
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean deleteById(String id);
	
	/**
	 * 
	* @Title: deleteOverdueByDate
	* @Description: 通过日期删除该日期以前已过期的所有记录（具体到日）
	* @param @param date
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean deleteOverdueByDate(Date date);
	
	/**
	 * 
	* @Title: save
	* @Description: 保存记录（如果通过家长id和学生id查找到记录则改为更新有效期（具体到日）
	* @param @param payment
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(Payment payment);
}
