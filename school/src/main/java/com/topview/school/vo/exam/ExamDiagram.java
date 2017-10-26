package com.topview.school.vo.exam;

import java.util.List;

/**
 * @author hcdn
 * 学习成绩图表
 */
public class ExamDiagram {
	private String category;//类型
	private List<Float> value;//成绩列表
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<Float> getValue() {
		return value;
	}
	
	public void setValue(List<Float> value) {
		this.value = value;
	}
	
}
