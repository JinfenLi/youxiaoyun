package com.topview.school.po;

import java.util.Date;

public class Subject {

	private String id;
    private String tScSchoolId; //学校id
    private String name; //科目名称
    private String comment; //科目简介
    private Date createTime; //创建时间
    private String tScTeacherId; //学科负责人id
    private boolean trunk; // 是否主干学科

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

	public boolean isTrunk() {
		return trunk;
	}

	public void setTrunk(boolean trunk) {
		this.trunk = trunk;
	}
    
}