package com.topview.school.vo.contacts.result;

import java.util.List;

import com.topview.school.vo.contacts.ContactsInfo;
import com.topview.school.vo.contacts.ParentContact;
import com.topview.school.vo.contacts.TeacherContact;

public class ContactsInfoResult {

	private boolean success; 
	private int code; 
	private List<ContactsInfo> teacherResult;
	private List<ContactsInfo> studentResult;
	
	private List<ParentContact> parentContacts;
	private List<TeacherContact> teacherContacts; 
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<ContactsInfo> getTeacherResult() {
		return teacherResult;
	}
	public void setTeacherResult(List<ContactsInfo> teacherResult) {
		this.teacherResult = teacherResult;
	}
	public List<ContactsInfo> getStudentResult() {
		return studentResult;
	}
	public void setStudentResult(List<ContactsInfo> studentResult) {
		this.studentResult = studentResult;
	}
	public List<ParentContact> getParentContacts() {
		return parentContacts;
	}
	public void setParentContacts(List<ParentContact> parentContacts) {
		this.parentContacts = parentContacts;
	}
	public List<TeacherContact> getTeacherContacts() {
		return teacherContacts;
	}
	public void setTeacherContacts(List<TeacherContact> teacherContacts) {
		this.teacherContacts = teacherContacts;
	}
}
