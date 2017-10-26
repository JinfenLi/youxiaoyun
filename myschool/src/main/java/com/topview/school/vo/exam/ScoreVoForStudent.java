package com.topview.school.vo.exam;

/**
 * @Description 安卓家长端显示考试成绩VO
 */
public class ScoreVoForStudent {

	private String examId; // 考试id
	private String examName; // 考试名称
	private int type; // 考试类型
	private String examTime; // 考试时间
	private String subjectName; // 所属学科名称
	private String score; // 学生成绩
	private String highestScore; // 最高分
	private String lowestScore; // 最低分
	private String averageScore; // 平均分
	private String rankInClass; // 班内排名

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(String highestScore) {
		this.highestScore = highestScore;
	}

	public String getLowestScore() {
		return lowestScore;
	}

	public void setLowestScore(String lowestScore) {
		this.lowestScore = lowestScore;
	}

	public String getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}

	public String getRankInClass() {
		return rankInClass;
	}

	public void setRankInClass(String rankInClass) {
		this.rankInClass = rankInClass;
	}

}
