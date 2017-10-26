package com.topview.school.service.clazz.leave.accessory;

import java.util.List;

import com.topview.school.po.LeaveAccessory;

public class LeaveAccessoryServiceImpl implements LeaveAccessoryService {

	private List<LeaveAccessoryProcess> saveAccessoryProcesses;
	
	@Override
	public boolean saveAccessory(LeaveAccessory accessory) {
		
		LeaveAccessoryContext context = new LeaveAccessoryContext();
		context.setAccessory(accessory);
		
		for (LeaveAccessoryProcess process : saveAccessoryProcesses) {
			if (!process.doProcess(context))
				break;
		}
		return context.isSuccess();
	}

	public List<LeaveAccessoryProcess> getSaveAccessoryProcesses() {
		return saveAccessoryProcesses;
	}

	public void setSaveAccessoryProcesses(
			List<LeaveAccessoryProcess> saveAccessoryProcesses) {
		this.saveAccessoryProcesses = saveAccessoryProcesses;
	}

}
