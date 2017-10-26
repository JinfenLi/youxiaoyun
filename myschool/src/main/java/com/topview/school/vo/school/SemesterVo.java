package com.topview.school.vo.school;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.topview.school.po.Semester;
import com.topview.school.util.UUIDUtil;

/**
 * @Description 学期VO
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月23日 下午3:23:38
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class SemesterVo {

	private String id;
	private String tScSchoolId; // 所属学校id
	private String name; // 学期名称：如'2015年春'
	private String startTime; // 学期开始时间
	private String endTime; // 学期结束时间
	private int week; // 总周数
	private String createTime; // 创建时间
	private int currentSemester; // 是否是当前学期

	public static Semester changeToPo(SemesterVo vo) {
		Semester semester = new Semester();

		semester.setId(vo.getId() == null ? UUIDUtil.getUUID() : vo.getId());
		semester.settScSchoolId(vo.gettScSchoolId());
		semester.setName(vo.getName());
		semester.setWeek(vo.getWeek());
		semester.setCurrentSemester(vo.getCurrentSemester());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			semester.setCreateTime(df.parse(vo.getCreateTime()));
			semester.setStartTime(df.parse(vo.getStartTime()));
			semester.setEndTime(df.parse(vo.getEndTime()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return semester;
	}

	public static SemesterVo changeToVo(Semester semester) {
		SemesterVo vo = new SemesterVo();
		vo.setId(semester.getId());
		vo.setName(semester.getName());
		vo.settScSchoolId(semester.gettScSchoolId());
		vo.setWeek(vo.getWeek());
		vo.setCurrentSemester(semester.getCurrentSemester());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		vo.setCreateTime(df.format(semester.getCreateTime()));
		vo.setStartTime(df.format(semester.getStartTime()));
		vo.setEndTime(df.format(semester.getEndTime()));
		return vo;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}

}
