package com.topview.school.vo.school;

import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.Departments;
import com.topview.school.vo.User.TeacherVo;

public class DepartmentsVo {

	private String id;
	private String tScSubjectId; // 科目id
	private String tScSchoolId; // 学校id
	private String name; // 科室名称
	private String info; // 科室备注:所属累呗
	private Boolean template; // 是否模板
	private String templateName; // 模板名称
	private String phone; // 科室电话
	private String type; // 科室类型:有年级意义上的部门、科目意义上的部门和常规的部门
	private List<TeacherVo> teachers; //部门下的所有老师
	private String teacherId; //负责人id
	private String leadingTeacher; //负责人姓名

	public static DepartmentsVo changeToVo(Departments d) {
		DepartmentsVo vo = new DepartmentsVo();
		vo.setId(d.getId());
		vo.settScSubjectId(d.gettScSubjectId());
		vo.settScSchoolId(d.gettScSchoolId());
		vo.setName(d.getName());
		vo.setInfo(d.getInfo());
		vo.setTemplate(d.getTemplate());
		vo.setTemplateName(d.getTemplateName());
		vo.setPhone(d.getPhone());
		vo.setType(d.getType());
		vo.setTeacherId(d.getTeacherId());
		return vo;
	}
	
	public static List<DepartmentsVo> changeToVo(List<Departments> departments) {
		List<DepartmentsVo> vos = new ArrayList<DepartmentsVo>();
		for(Departments d : departments) {
			vos.add(DepartmentsVo.changeToVo(d));
		}
		return vos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettScSubjectId() {
		return tScSubjectId;
	}

	public void settScSubjectId(String tScSubjectId) {
		this.tScSubjectId = tScSubjectId;
	}

	public String gettScSchoolId() {
		return tScSchoolId;
	}

	public void settScSchoolId(String tScSchoolId) {
		this.tScSchoolId = tScSchoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Boolean getTemplate() {
		return template;
	}

	public void setTemplate(Boolean template) {
		this.template = template;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TeacherVo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherVo> teachers) {
		this.teachers = teachers;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getLeadingTeacher() {
		return leadingTeacher;
	}

	public void setLeadingTeacher(String leadingTeacher) {
		this.leadingTeacher = leadingTeacher;
	}

}
