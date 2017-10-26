package com.topview.school.po;

import java.util.Date;

/**
 * @Description 课程PO
 */
public class Curricula {
    
    private String id;
    private String tScSubjectId; //所属科目id
    private String name; //课程名称
    private String comment; //课程描述
    private String adaptiveGrade; //适应年级
    private String adaptiveTerm; //适应学期
    private Date createTime; //创建时间
    private String tScTeacherId; //课程负责老师id

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

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String gettScTeacherId() {
		return tScTeacherId;
	}

	public void settScTeacherId(String tScTeacherId) {
		this.tScTeacherId = tScTeacherId;
	}
    
}