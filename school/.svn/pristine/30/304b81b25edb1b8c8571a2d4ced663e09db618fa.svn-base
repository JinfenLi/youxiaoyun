package com.topview.school.vo.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.topview.school.po.Leave;
import com.topview.school.po.LeaveAccessory;
import com.topview.school.po.Parent;
import com.topview.school.po.Teacher;

/**
 * 请假申请VO
 * 
 * @Description
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月19日 上午10:11:26
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class LeaveInfo {

	private String id; // 请假申请的id
	private String type; // 请假类型
	private String reason; // 请假原因
	private String applyTime; // 申请时间
	private int status; // 处理状态
	private String rejectReason; // 老师审批意见
	private int accessoryCount; //附件数量
	private String parentName; // 家长姓名
	private String parentId; // 家长id
	private String studentId; // 学生id
	private String studentName; // 学生姓名
	private String teacherId; //班主任id
	private String teacherName; // 班主任姓名
	private List<String> urls; // 附件url
	private String picUrl; //学生头像
	private int leaveNumber;
	
	
	
	
	public LeaveInfo() {
	}

	public LeaveInfo(String id, String type, String reason, String applyTime,
			int status, String parentId, String studentId) {
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.applyTime = applyTime;
		this.status = status;
		this.parentId = parentId;
		this.studentId = studentId;
	}

	public static LeaveInfo changeToVo(Leave leave,
			List<LeaveAccessory> accessorys, Teacher teacher, Parent parent) {
		LeaveInfo info = new LeaveInfo();

		info.setId(leave.getId());
		info.setType(leave.getType());
		info.setReason(leave.getReason());
		info.setStatus(leave.getStatus());
		info.setRejectReason(leave.getRejectReason());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String applyTime = sdf.format(leave.getApplyTime());
		info.setApplyTime(applyTime);
		info.setParentName(parent.getName());
		info.setParentId(parent.getId());
		info.setTeacherName(teacher.getName());
		info.setStudentId(leave.getStudentId());
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < accessorys.size(); i++) {
			list.add(accessorys.get(i).getUrl());
		}
		info.setUrls(list);

		return info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getAccessoryCount() {
		return accessoryCount;
	}

	public void setAccessoryCount(int accessoryCount) {
		this.accessoryCount = accessoryCount;
	}

	
	
	public int getLeaveNumber() {
		return leaveNumber;
	}

	public void setLeaveNumber(int leaveNumber) {
		this.leaveNumber = leaveNumber;
	}

	public String toJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("type", type);
		jsonObject.put("reason", reason);
		jsonObject.put("applyTime", applyTime);
		jsonObject.put("parentId", parentId);
		jsonObject.put("parentName", parentName);
		jsonObject.put("studentId", studentId);
		jsonObject.put("studentName", studentName);
		jsonObject.put("teacherName", teacherName);
		jsonObject.put("rejectReason", rejectReason);
		jsonObject.put("urls", urls);
		return jsonObject.toString();
	}

}
