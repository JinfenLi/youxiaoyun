package com.topview.school.vo.curricula;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.topview.school.po.CurriculaVariable;

public class CurriculaVariableInfo {
	private String id;
	private String tScCurriculaId;
	private String tScTeacherId;
	private String tScClassId;
	private String tScSemesterId;
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettScCurriculaId() {
		return tScCurriculaId;
	}

	public void settScCurriculaId(String tScCurriculaId) {
		this.tScCurriculaId = tScCurriculaId;
	}

	public String gettScTeacherId() {
		return tScTeacherId;
	}

	public void settScTeacherId(String tScTeacherId) {
		this.tScTeacherId = tScTeacherId;
	}

	public String gettScClassId() {
		return tScClassId;
	}

	public void settScClassId(String tScClassId) {
		this.tScClassId = tScClassId;
	}

	public String gettScSemesterId() {
		return tScSemesterId;
	}

	public void settScSemesterId(String tScSemesterId) {
		this.tScSemesterId = tScSemesterId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static CurriculaVariableInfo changeToVo(
			CurriculaVariable curriculaVariable) {
		CurriculaVariableInfo info = new CurriculaVariableInfo();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setCreateTime(df.format(curriculaVariable.getCreateTime()));
		info.setId(curriculaVariable.getId());
		info.settScClassId(curriculaVariable.gettScClassId());
		info.settScCurriculaId(curriculaVariable.gettScCurriculaId());
		info.settScSemesterId(curriculaVariable.gettScSemesterId());
		info.settScTeacherId(curriculaVariable.gettScTeacherId());
		return info;
	}

	public static List<CurriculaVariableInfo> changeToVo(
			List<CurriculaVariable> list) {
		List<CurriculaVariableInfo> infos = new ArrayList<CurriculaVariableInfo>();
		if (list.size() > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				infos.add(changeToVo(list.get(i)));
			}
		}
		return infos;
	}
}
