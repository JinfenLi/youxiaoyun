package com.topview.school.vo.curricula;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.Curricula;

public class CurriculaVo {

	private String id;
	private String tScSubjectId; // 所属科目id
	private String name; // 课程名称
	private String comment; // 课程描述
	private String adaptiveGrade; // 适应年级
	private String adaptiveTerm; // 适应学期
	private String createTime; // 创建时间
	private String tScTeacherId; //课程负责老师id
	private String teacherName; //课程负责老师姓名
	
	
	public static Curricula changeToPo(CurriculaVo vo){
		Curricula curricula = new Curricula();
		curricula.setId(vo.getId());
		curricula.setName(vo.getName());
		curricula.setAdaptiveGrade(vo.getAdaptiveGrade());
		curricula.setAdaptiveTerm(vo.getAdaptiveTerm());
		curricula.setComment(vo.getComment());
		curricula.settScTeacherId(vo.gettScTeacherId());
		return curricula;
	}

	public static CurriculaVo changeToVo(Curricula curricula) {
		CurriculaVo vo = new CurriculaVo();
		vo.setId(curricula.getId());
		vo.setName(curricula.getName());
		vo.settScSubjectId(curricula.gettScSubjectId());
		vo.setComment(curricula.getComment());
		vo.setAdaptiveGrade(curricula.getAdaptiveGrade());
		vo.setAdaptiveTerm(curricula.getAdaptiveTerm());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		vo.setCreateTime(df.format(curricula.getCreateTime()));
		vo.settScTeacherId(curricula.gettScTeacherId());
		return vo;
	}

	public static List<CurriculaVo> changeToVo(List<Curricula> curriculas) {
		List<CurriculaVo> vos = new ArrayList<CurriculaVo>();
		for (Curricula c : curriculas) {
			vos.add(CurriculaVo.changeToVo(c));
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

	public String getAdaptiveGrade() {
		return adaptiveGrade;
	}

	public void setAdaptiveGrade(String adaptiveGrade) {
		this.adaptiveGrade = adaptiveGrade;
	}

	public String getAdaptiveTerm() {
		return adaptiveTerm;
	}

	public void setAdaptiveTerm(String adaptiveTerm) {
		this.adaptiveTerm = adaptiveTerm;
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

}
