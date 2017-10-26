package com.topview.school.po;

import java.util.Date;

/**
 * @Description 年级PO
 */
public class Grade {

	private String id;
    private String tScSchoolId; //学校id
    private String name; //年级名称
    private String sortName; //年级简称
    private Integer level; //年级级别
    private String comment; //年级简介
    private String info; //年级备注
    private Date year; //入学年份
    private Boolean graduate; //是否毕业

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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Boolean getGraduate() {
        return graduate;
    }

    public void setGraduate(Boolean graduate) {
        this.graduate = graduate;
    }
    
}