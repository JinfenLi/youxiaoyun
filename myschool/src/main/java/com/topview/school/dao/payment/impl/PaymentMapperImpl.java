package com.topview.school.dao.payment.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.payment.PaymentMapper;
import com.topview.school.po.Payment;

/**
 * 
* @ClassName: PaymentMapperImpl
* @Description: 账号付费Mapper实现类
* @author Catalyst
* @date 2016年1月21日
*
 */
@Repository
public class PaymentMapperImpl extends BaseDaoImpl<Payment> implements
		PaymentMapper {

	/**
	 * 
	* @Title: selectByParentIdAndStudentId
	* @Description: 通过家长id和学生id查找
	* @param @param param
	* @param @return    参数
	* @return Payment    返回类型
	* @throws
	 */
	@Override
	public Payment selectByParentIdAndStudentId(Map<String, Object> param) {
		return template.selectOne(getStatement("selectByParentIdAndStudentId"), param);
	}

	/**
	 * 
	* @Title: updateDeadlineByParentIdAndStudentId
	* @Description: 通过家长id和学生id更新有效期（具体到日）
	* @param @param param
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	@Override
	public int updateDeadlineByParentIdAndStudentId(Map<String, Object> param) {
		return template.update(getStatement("updateDeadlineByParentIdAndStudentId"), param);
	}

	/**
	 * 
	* @Title: deleteOverdueByDate
	* @Description: 通过日期删除该日期以前已过期的所有记录（具体到日）
	* @param @param date
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	@Override
	public int deleteOverdueByDate(Date date) {
		return template.delete(getStatement("deleteOverdueByDate"), date);
	}
}
