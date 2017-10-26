package com.topview.school.service.clazz.leave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.bean.Pager;
import com.topview.school.dao.leave.LeaveAccessoryMapper;
import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.LeaveAccessory;
import com.topview.school.po.Student;
import com.topview.school.po.Teacher;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

/**
 * @Description 老师或家长登陆后获取能看到的所有请假申请
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月26日 上午12:12:14
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class GetLeaveProcess implements LeaveProcess {

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private LeaveAccessoryMapper accessoryMapper;
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ParentMapper parentMapper;
	@Resource
	private TeacherMapper teacherMapper;

	@Override
	public boolean doProcess(LeaveProcessContext context) {

		// 定义存放结果的变量
		LeaveInfoResult finalResult = context.getResult();
		List<LeaveInfo> leaveInfos = new ArrayList<LeaveInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		Pager pager = context.getPager();
		int totalCount = 0;
		UserInfo userInfo = (UserInfo) context.getSession().getAttribute(
				"currentUser");
		String clazzId = (String) context.getSession().getAttribute("clazzId");
		if (clazzId == null || "".equals(clazzId)) {
			clazzId = userInfo.getClass_id();
		}

		switch (userInfo.getUser_type().ordinal()) {
		case 1: // 老师类型根据班级id获取请假申请记录
			map.put("clazzId", clazzId);
			map.put("offset", (pager.getPageNumber() - 1) * pager.getPageSize());
			map.put("limit", pager.getPageSize());
			leaveInfos = leaveMapper.findByClazzId(map); // 分页查询请假条
			totalCount = leaveMapper.selectCountByClazzId(clazzId);
			break;
		case 2:// 家长类型根据学生id获取请假申请记录
			String studentId = (String) context.getSession().getAttribute(
					"studentId");
			if (studentId == null || "".equals(studentId)) {
				studentId = userInfo.getParent_info().getStudent_id();
			}
			map.put("studentId", studentId);
			map.put("offset", (pager.getPageNumber() - 1) * pager.getPageSize());
			map.put("limit", pager.getPageSize());
			leaveInfos = leaveMapper.findByStudentId(map);
			totalCount = leaveMapper.selectCountByStudentId(studentId);
			break;
		default:// 非法用户
			finalResult.setResult(null);
			finalResult.setSuccess(false);
			return false;
		}
		// 查询并添加班主任id、班主任姓名和附件url
		for (int i = 0; i < leaveInfos.size(); i++) {
			Teacher teacher = teacherMapper.findHeadTeacher(clazzId);
			leaveInfos.get(i).setTeacherId(teacher.getId());
			leaveInfos.get(i).setTeacherName(teacher.getName());
			Student s = studentMapper.selectByPrimaryKey(leaveInfos.get(i).getStudentId());
			leaveInfos.get(i).setPicUrl(s.getPicurl());//应IOS要求增加返回学生头像
			List<LeaveAccessory> accessories = accessoryMapper
					.selectByLeaveId(leaveInfos.get(i).getId());
			List<String> urls = new ArrayList<String>();
			for (int j = 0; j < accessories.size(); j++) {
				urls.add(accessories.get(j).getUrl());
			}
			leaveInfos.get(i).setUrls(urls);
		}
		finalResult.setCode(totalCount);
		finalResult.setResult(leaveInfos);
		finalResult.setSuccess(true);
		return true;
	}

}
