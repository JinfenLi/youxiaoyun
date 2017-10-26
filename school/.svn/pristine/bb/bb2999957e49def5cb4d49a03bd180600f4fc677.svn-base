package com.topview.school.service.clazz.leave;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveAccessoryMapper;
import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.LeaveAccessory;
import com.topview.school.po.Teacher;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

/**
 * @Description 单独获取一条请假申请
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月26日 上午12:19:26
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class GetOneLeaveProcess implements LeaveProcess {

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private LeaveAccessoryMapper leaveAccessoryMapper;
	@Resource
	private ParentMapper parentMappper;
	@Resource
	private TeacherMapper teacherMapper;

	@Override
	public boolean doProcess(LeaveProcessContext context) {

		LeaveInfoResult result = context.getResult();
		List<LeaveInfo> infos = new ArrayList<LeaveInfo>();
		HttpSession session = context.getSession();
		String leaveId = context.getInfo().getId();

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		LeaveInfo leaveInfo = leaveMapper.findByLeaveId(leaveId);
		if (leaveInfo == null) {
			result.setSuccess(false);
			context.setResult(result);
			return false;
		}
		if(userInfo.getTeacher_info()!= null &&userInfo.getTeacher_info().getLeading_class()!= null){
				//查询班主任名字
				Teacher teacher = teacherMapper.findHeadTeacher(userInfo.getTeacher_info().getLeading_class());
				leaveInfo.setTeacherId(teacher.getId());
				leaveInfo.setTeacherName(teacher.getName());
				//查询附件路径
				List<LeaveAccessory> accessories = leaveAccessoryMapper
						.selectByLeaveId(leaveId);
				List<String> urls = new ArrayList<String>();
				for(LeaveAccessory accessory : accessories) {
					urls.add(accessory.getUrl());
				}
				leaveInfo.setUrls(urls);
				infos.add(leaveInfo);
				result.setResult(infos);
				result.setSuccess(true);
				context.setResult(result);
				return true;
		}
		else{
			//如果登陆用户为家长 根据学生的classId来获取
			//查询班主任名字
			Teacher teacher = teacherMapper.findHeadTeacher(userInfo.getClass_id());
			leaveInfo.setTeacherId(teacher.getId());
			leaveInfo.setTeacherName(teacher.getName());
			//查询附件路径
			List<LeaveAccessory> accessories = leaveAccessoryMapper
					.selectByLeaveId(leaveId);
			List<String> urls = new ArrayList<String>();
			for(LeaveAccessory accessory : accessories) {
				urls.add(accessory.getUrl());
			}
			leaveInfo.setUrls(urls);
			infos.add(leaveInfo);
			result.setResult(infos);
			result.setSuccess(true);
			context.setResult(result);
			return true;
		}
	}

}
