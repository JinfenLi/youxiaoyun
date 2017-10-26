package com.topview.school.service.payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.payment.PaymentMapper;
import com.topview.school.po.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentMapper paymentMapper;
	
	/**
	 * 
	* @Title: selectById
	* @Description: 通过id查找
	* @param @param id
	* @param @return    参数
	* @return Payment    返回类型
	* @throws
	 */
	@Override
	public Payment selectById(String id) {
		return paymentMapper.selectByPrimaryKey(id);
	}

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
	@Override
	public Payment selectByParentIdAndStudentId(String parentId,
			String studentId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", parentId);
		param.put("studentId", studentId);
		return paymentMapper.selectByParentIdAndStudentId(param);
	}

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
	@Override
	public boolean updateDeadlineById(String id, Date deadline) {
		Payment payment = new Payment();
		payment.setId(id);
		payment.setDeadline(deadline);
		return paymentMapper.updateByPrimaryKeySelective(payment) > 0;
	}

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
	@Override
	public boolean updateDeadlineByParentIdAndStudentId(String parentId,
			String studentId, Date deadline) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", parentId);
		param.put("studentId", studentId);
		param.put("deadline", deadline);
		return paymentMapper.updateDeadlineByParentIdAndStudentId(param) > 0;
	}

	/**
	 * 
	* @Title: deleteById
	* @Description: 通过id删除
	* @param @param id
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	@Override
	public boolean deleteById(String id) {
		return paymentMapper.deleteByPrimaryKey(id) > 0;
	}

	/**
	 * 
	* @Title: deleteOverdueByDate
	* @Description: 通过日期删除该日期以前已过期的所有记录（具体到日）
	* @param @param date
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	@Override
	public boolean deleteOverdueByDate(Date date) {
		return paymentMapper.deleteOverdueByDate(date) > 0;
	}

	/**
	 * 
	* @Title: save
	* @Description: 保存记录（如果通过家长id和学生id查找到记录则改为更新有效期（具体到日）
	* @param @param payment
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	@Override
	public boolean save(Payment payment) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentId", payment.gettScParentId());
		param.put("studentId", payment.gettScStudentId());
		if(paymentMapper.selectByParentIdAndStudentId(param) != null) {	//如果查找到记录
			return paymentMapper.updateDeadlineByParentIdAndStudentId(param) > 0;	//改为更新有效期（具体到日）
		}
		return paymentMapper.insertSelective(payment) > 0;
	}

}
