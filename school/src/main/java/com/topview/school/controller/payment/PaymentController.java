package com.topview.school.controller.payment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.po.Payment;
import com.topview.school.service.payment.PaymentService;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.JSONUtil;

@Controller
@RequestMapping(value = "/payment", produces = "text/html;charset=UTF-8")
public class PaymentController {

	@Resource
	private PaymentService paymentService;
	
	/**
	 * 
	* @Title: selectById
	* @Description: 通过id查找
	* @param @param id
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/selectById")
	@ResponseBody
	public String selectById(String id) {
		return JSONUtil.toObject(paymentService.selectById(id)).toString();
	}
	
	/**
	 * 
	* @Title: selectByParentIdAndStudentId
	* @Description: 通过家长id和学生id查找
	* @param @param parentId
	* @param @param studentId
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/selectByParentIdAndStudentId")
	@ResponseBody
	public String selectByParentIdAndStudentId(String parentId, String studentId) {
		return JSONUtil.toObject(paymentService
				.selectByParentIdAndStudentId(parentId, studentId)).toString();
	}
	
	/**
	 * 
	* @Title: updateDeadlineById
	* @Description: 通过id更新有效期（具体到日）
	* @param @param id
	* @param @param deadlineString
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/updateDeadlineById")
	@ResponseBody
	public String updateDeadlineById(String id, String deadlineString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", paymentService
				.updateDeadlineById(id, DateFormatUtil.parseToDay(deadlineString)));
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: updateDeadlineByParentIdAndStudentId
	* @Description: 通过家长id和学生id更新有效期（具体到日）
	* @param @param parentId
	* @param @param studentId
	* @param @param deadlineString
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/updateDeadlineByParentIdAndStudentId")
	@ResponseBody
	public String updateDeadlineByParentIdAndStudentId(String parentId,
			String studentId, String deadlineString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", paymentService
				.updateDeadlineByParentIdAndStudentId(parentId, studentId,
						DateFormatUtil.parseToDay(deadlineString)));
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: deleteById
	* @Description: 通过id删除
	* @param @param id
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", paymentService.deleteById(id));
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: deleteOverdueByDate
	* @Description: 通过日期删除该日期以前已过期的所有记录（具体到日）
	* @param @param dateString
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/deleteOverdueByDate")
	@ResponseBody
	public String deleteOverdueByDate(String dateString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", paymentService
				.deleteOverdueByDate(DateFormatUtil.parseToDay(dateString)));
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 
	* @Title: save
	* @Description: 保存记录（如果通过家长id和学生id查找到记录则改为更新有效期（具体到日）
	* @param @param id
	* @param @param studentId
	* @param @param parentId
	* @param @param schoolId
	* @param @param deadlineString
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(String id, String studentId, String parentId,
			String schoolId, String deadlineString) {
		Payment payment = new Payment();
		payment.setId(id);
		payment.settScStudentId(studentId);
		payment.settScParentId(parentId);
		payment.settScSchoolId(schoolId);
		payment.setDeadline(DateFormatUtil.parseToDay(deadlineString));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", paymentService.save(payment));
		return JSONUtil.toObject(resultMap).toString();
	}
}
