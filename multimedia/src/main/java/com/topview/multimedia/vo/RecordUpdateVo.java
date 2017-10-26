package com.topview.multimedia.vo;

import java.util.ArrayList;
import java.util.List;

import com.topview.multimedia.po.RecordUpdate;
import com.topview.multimedia.util.DateFormatUtil;

public class RecordUpdateVo {

	private String id;
	private String tMId;
	private String lastUpdate;
	private String module;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettMId() {
		return tMId;
	}

	public void settMId(String tMId) {
		this.tMId = tMId;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public static RecordUpdateVo changeToVo(RecordUpdate record) {
		RecordUpdateVo vo = new RecordUpdateVo();
		vo.setId(record.getId());
		vo.settMId(record.gettMId());
		vo.setLastUpdate(DateFormatUtil.formatDateToSeconds(record.getLastUpdate()));
		vo.setModule(record.getModule());
		return vo;
	}
	
	public static List<RecordUpdateVo> changeToVo(List<RecordUpdate> records) {
		List<RecordUpdateVo> vos = new ArrayList<RecordUpdateVo>();
		for(RecordUpdate record: records) {
			vos.add(RecordUpdateVo.changeToVo(record));
		}
		return vos;
	}
}
