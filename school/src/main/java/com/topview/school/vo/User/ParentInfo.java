package com.topview.school.vo.User;

import java.util.List;

import com.topview.school.po.Student;

/**
 * @Title: ParentInfo.java
 * @Package com.topview.school.vo.User
 * @Description: TODO
 * @author Sherlock-lee
 * @date 2015年5月6日 下午5:42:20
 * @version V1.0
 */
public class ParentInfo {
	/**
	 * @ClassName: ParentInfo
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年5月6日 下午5:42:20
	 */
	List<Student> students = null;
	private String student_id = null;
	private String parent_Name = null; // 孩子姓名
	private int student_count; // 一个家长关联了几个学生
	private List<String> kids = null;// 一个家长对应多个孩子

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getParent_Name() {
		return parent_Name;
	}

	public void setParent_Name(String parent_Name) {
		this.parent_Name = parent_Name;
	}

	public List<String> getKids() {
		return kids;
	}

	public void setKids(List<String> kids) {
		this.kids = kids;
	}

	public int getStudent_count() {
		return student_count;
	}

	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}

}
