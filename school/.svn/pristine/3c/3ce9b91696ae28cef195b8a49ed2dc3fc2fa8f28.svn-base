package com.topview.school.vo.school;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.topview.multimedia.util.UUIDUtil;
import com.topview.school.po.Subject;
import com.topview.school.util.DateFormatUtil;

public class SubjectVo {

	private String id;
	private String tScSchoolId; // 学校id
	@Size(max=32)
	private String name; // 科目名称
	@Size(max=150)
	private String comment; // 科目简介
	private String createTime; // 创建时间
	private String tScTeacherId; //学科负责人id
	private String teacherName; //学科负责人姓名
	private boolean trunk; //是否是主干学科

	public static SubjectVo changeToVo(Subject subject) {
		SubjectVo vo = new SubjectVo();
		vo.setId(subject.getId());
		vo.setName(subject.getName());
		vo.setComment(subject.getComment());
		vo.settScSchoolId(subject.gettScSchoolId());
		vo.setCreateTime(DateFormatUtil.formatDateToSeconds(subject.getCreateTime()));
		vo.settScTeacherId(subject.gettScTeacherId());
		vo.setTrunk(subject.isTrunk());
		return vo;
	}

	public static List<SubjectVo> changeToVo(List<Subject> subjects) {
		List<SubjectVo> vos = new ArrayList<SubjectVo>();
		for (Subject s : subjects) {
			vos.add(SubjectVo.changeToVo(s));
		}
		return vos;
	}

	public static Subject changeToPo(SubjectVo vo) {
		Subject subject = new Subject();
		subject.setId(vo.getId() == null ? UUIDUtil.getUUID() : vo.getId());
		subject.setName(vo.getName());
		subject.settScSchoolId(vo.gettScSchoolId());
		subject.setComment(vo.getComment());
		subject.settScTeacherId(vo.gettScTeacherId());
		subject.setTrunk(vo.isTrunk());
		if (vo.getCreateTime() != null && !"".equals(vo.getCreateTime())) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				subject.setCreateTime(df.parse(vo.getCreateTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return subject;
	}

	public static List<Subject> changeToPo(List<SubjectVo> vos) {
		List<Subject> subjects = new ArrayList<Subject>();
		for (SubjectVo vo : vos) {
			subjects.add(SubjectVo.changeToPo(vo));
		}
		return subjects;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String gettScTeacherId() {
		return tScTeacherId;
	}

	public void settScTeacherId(String tScTeacherId) {
		this.tScTeacherId = tScTeacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public boolean isTrunk() {
		return trunk;
	}

	public void setTrunk(boolean trunk) {
		this.trunk = trunk;
	}

}
