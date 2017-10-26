package com.topview.school.service.clazz.leave.accessory;

import com.topview.school.po.LeaveAccessory;

public class LeaveAccessoryContext {

	private LeaveAccessory accessory; // 附件对象
	private boolean success;

	public LeaveAccessory getAccessory() {
		return accessory;
	}

	public void setAccessory(LeaveAccessory accessory) {
		this.accessory = accessory;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
