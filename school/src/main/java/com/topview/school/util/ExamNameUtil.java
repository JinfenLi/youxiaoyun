package com.topview.school.util;

/**
 * @Title: ExamexamNameUtil.java
 * @Package com.topview.school.service.score.util
 * @Description: 考试信息的截取
 * @author Sherlock-lee
 * @date 2015年4月17日 上午12:08:51
 * @version V1.0
 */
public class ExamNameUtil {
	private String examName = "";// 考试名称

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public ExamNameUtil(String examName) {
		super();
		this.examName = examName;
	}

	public String getSubject() {

		String target = "";
		if (examName.contains("语文")) {
			target = "语文";
		}
		if (examName.contains("数学")) {
			target = "数学";
		}
		if (examName.contains("英语")) {
			target = "英语";
		}
		return target;
	}

	public String getExamtype() {

		String target = "";
		if (examName.contains("期中")) {
			target = "期中";
		}
		if (examName.contains("期末")) {
			target = "期末";
		}
		if (examName.contains("第一单元")) {
			target = "一";
		}
		if (examName.contains("第二单元")) {
			target = "二";
		}
		if (examName.contains("第三单元")) {
			target = "三";
		}
		if (examName.contains("第四单元")) {
			target = "四";
		}
		if (examName.contains("第五单元")) {
			target = "五";
		}
		if (examName.contains("第六单元")) {
			target = "六";
		}
		if (examName.contains("第七单元")) {
			target = "七";
		}
		if (examName.contains("第八单元")) {
			target = "八";
		}
		if (examName.contains("第九单元")) {
			target = "九";
		}
		if (examName.contains("第十单元")) {
			target = "十";
		}
		if (examName.contains("第十一单元")) {
			target = "十一";
		}
		if (examName.contains("第十二单元")) {
			target = "十二";
		}
		if (examName.contains("第十三单元")) {
			target = "十三";
		}
		if (examName.contains("第十四单元")) {
			target = "十四";
		}
		if (examName.contains("第十五单元")) {
			target = "十五";
		}
		if (examName.contains("第十六单元")) {
			target = "十六";
		}
		return target;
	}

}
