package com.topview.school.service.clazz.library;

import com.topview.multimedia.service.library.LibraryProcessContext;

public class ClazzLibraryProcessContext extends LibraryProcessContext{
	private String studentId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
}
