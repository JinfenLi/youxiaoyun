package com.topview.school.service.clazz.leave.accessory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.leave.LeaveAccessoryMapper;

@Service
public class SaveAccessoryProcess implements LeaveAccessoryProcess {

	@Autowired
	private LeaveAccessoryMapper accessoryMapper;
	@Override
	public boolean doProcess(LeaveAccessoryContext context) {
		
		if(accessoryMapper.insert(context.getAccessory()) == 0){
			context.setSuccess(false);
			return false;
		}
		else {
			context.setSuccess(true);
			return true;
		}
	}

}
