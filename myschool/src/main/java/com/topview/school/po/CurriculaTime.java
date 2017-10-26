package com.topview.school.po;

/**
 * @Description 上课时间PO
 */
public class CurriculaTime {

	private String id;
    private String tScCurriculaVariableId; //选课id
    private Integer week; //周几
    private Integer section; //节数
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String gettScCurriculaVariableId() {
        return tScCurriculaVariableId;
    }
    
    public void settScCurriculaVariableId(String tScCurriculaVariableId) {
        this.tScCurriculaVariableId = tScCurriculaVariableId;
    }
    
    public Integer getWeek() {
        return week;
    }
    
    public void setWeek(Integer week) {
        this.week = week;
    }
    
    public Integer getSection() {
        return section;
    }
    
    public void setSection(Integer section) {
        this.section = section;
    }
    
}