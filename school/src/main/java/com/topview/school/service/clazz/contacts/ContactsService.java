package com.topview.school.service.clazz.contacts;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.topview.school.vo.contacts.ContactsInfo;
import com.topview.school.vo.contacts.ParentContact;
import com.topview.school.vo.contacts.result.ContactsInfoResult;

public interface ContactsService {
	
	/**
	 * 修改头像
	 * @param session
	 * @param picUrl
	 * @param userId
	 * @return
	 */
	public ContactsInfoResult updatePic(HttpSession session, String picUrl, String userId);
	
	/**
	 * 获取用户通讯录，包含家长通讯录和教师通讯录
	 * @param session
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public ContactsInfoResult getUserContacts(HttpSession session, String clazzId);
	
	/**
	 * 根据班级id获取家长通讯录
	 * @param clazzId
	 * @return
	 */
	public List<ParentContact> getParentContacts(String clazzId);
	
	/**
	 * 根据部门id获取部门教师通讯录
	 * @param departmentId
	 * @return
	 */
	public List<ContactsInfo> getDepartmentContact(String departmentId);
}
