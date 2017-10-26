package com.topview.school.service.clazz.leave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveMapper;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.po.Clazz;
import com.topview.school.vo.leave.LeaveInfo;
import com.topview.school.vo.leave.result.LeaveInfoResult;

@Service
public class getCountByTeacherIdProcess implements LeaveProcess {

	@Resource
	private LeaveMapper leaveMapper;
	@Resource
	private ClazzMapper clazzMapper;

	@Override
	public boolean doProcess(LeaveProcessContext context) {
		LeaveInfoResult result = context.getResult();
		LeaveInfo info = context.getInfo();
		String semesterId = context.getSemesterId();
		String teacherId = info.getTeacherId();
		int count = 0;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("status", 1);
		List<Clazz> list = clazzMapper.selectTeacherClazzs(teacherId, semesterId);
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				param.put("clazzId", list.get(i).getId());
				count += leaveMapper.selectCountByClazzIdAndStatus(param);
			}
		}
		result.setCode(count);
		result.setSuccess(true);
		return true;
	}
	
	
	
}
