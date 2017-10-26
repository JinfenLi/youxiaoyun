package com.topview.school.vo.school;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.Grade;
import com.topview.school.util.UUIDUtil;

/**
 * @Description 年级VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月26日 下午10:34:03
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class GradeVo {

	private String id; // 年级id
	private String tScSchoolId; // 学校id
	private String name; // 年级名称
	private String sortName; // 年级简称
	private Integer level; // 年级级别
	private String comment; // 年级简介
	private String info; // 年级备注
	private String year; // 入学年份
	private Boolean graduate; // 是否毕业
	private String gradeTeacher; //年级级长

	public static Grade changeToPo(GradeVo vo) {
		Grade grade = new Grade();
		grade.setId(vo.getId() == null ? UUIDUtil.getUUID() : vo.getId());
		grade.settScSchoolId(vo.gettScSchoolId());
		grade.setName(vo.getName());
		grade.setSortName(vo.getSortName());
		grade.setLevel(vo.getLevel());
		grade.setComment(vo.getComment());
		grade.setInfo(vo.getInfo());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			grade.setYear(df.parse(vo.getYear()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		grade.setGraduate(vo.getGraduate());
		return grade;
	}

	public static List<Grade> changeToPo(List<GradeVo> vos) {
		List<Grade> grades = new ArrayList<Grade>();
		for (GradeVo vo : vos) {
			grades.add(GradeVo.changeToPo(vo));
		}
		return grades;
	}

	public static GradeVo changeToVo(Grade po) {
		GradeVo vo = new GradeVo();
		vo.setId(po.getId());
		vo.settScSchoolId(po.gettScSchoolId());
		vo.setName(po.getName());
		vo.setSortName(po.getSortName());
		vo.setLevel(po.getLevel());
		vo.setComment(po.getComment());
		vo.setInfo(po.getInfo());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		vo.setYear(df.format(po.getYear()));
		vo.setGraduate(po.getGraduate());
		return vo;
	}

	public static List<GradeVo> changeToVo(List<Grade> pos) {
		List<GradeVo> vos = new ArrayList<GradeVo>();
		for(Grade grade : pos) {
			vos.add(GradeVo.changeToVo(grade));
		}
		return vos;
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

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getGraduate() {
		return graduate;
	}

	public void setGraduate(Boolean graduate) {
		this.graduate = graduate;
	}

	public String getGradeTeacher() {
		return gradeTeacher;
	}

	public void setGradeTeacher(String gradeTeacher) {
		this.gradeTeacher = gradeTeacher;
	}

}
