package com.topview.school.vo.exam;

import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.Clazz;
import com.topview.school.po.Teacher;
import com.topview.school.util.ExamNameUtil;

public class ExamInfoToTeacher {

	private String id;	//考试id
	private String subject; //科目名称：语文、数学
	private String clazz; //班级名称
	private String time; //考试时间
	private String unit; //考试单元
	private String teacherName; //该门课程的老师姓名
	private String templetId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public static ExamInfoToTeacher changeToVo(ExamInfo examInfo,Teacher teacher, Clazz clazz){
		ExamInfoToTeacher e = new ExamInfoToTeacher();
		ExamNameUtil util = new ExamNameUtil(examInfo.getName());
		e.setId(examInfo.getId());
		e.setSubject(util.getSubject());
		e.setUnit(util.getExamtype());
		e.setClazz(clazz.getName());
		e.setTime(examInfo.getDate());
		e.setTeacherName(teacher.getName());
		e.setTempletId(examInfo.getTempletId());
    	return e;
    }

	public static List<ExamInfoToTeacher> changeToVo(List<ExamInfo> list, Teacher teacher, Clazz clazz) {
		List<ExamInfoToTeacher> es = new ArrayList<ExamInfoToTeacher>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				es.add(changeToVo(list.get(i), teacher, clazz));
			}
		}
		return es;
	}

	public String getTempletId() {
		return templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}
}
