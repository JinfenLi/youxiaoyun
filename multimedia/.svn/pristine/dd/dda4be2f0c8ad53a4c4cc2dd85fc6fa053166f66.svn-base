package com.topview.multimedia.service.record;

import java.util.Date;
import java.util.List;

import com.topview.multimedia.po.RecordUpdate;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.result.RecordUpdateResult;

public class RecordUpdateServiceImpl implements RecordUpdateService{

	private List<RecordUpdateProcess> saveOrUpdateRecordProcesses;
	private List<RecordUpdateProcess> findRecordUpdateProcesses;
	
	public RecordUpdateResult saveOrUpdateRecord(String tMId, String module) {
		RecordUpdate record = new RecordUpdate();
		record.setId(UUIDUtil.getUUID());
		record.setLastUpdate(new Date());
		record.settMId(tMId);
		record.setModule(module);
		return doProcess(record, saveOrUpdateRecordProcesses);
	}
	
	public RecordUpdateResult findRecordUpdate(String tMId) {
		RecordUpdate record = new RecordUpdate();
		record.settMId(tMId);
		return doProcess(record, findRecordUpdateProcesses);
	}

	public RecordUpdateResult doProcess(RecordUpdate record,
			List<RecordUpdateProcess> processes) {
		RecordUpdateProcessContext context = new RecordUpdateProcessContext();
		context.setRecord(record);
		RecordUpdateResult result = new RecordUpdateResult();
		context.setResult(result);
		for (RecordUpdateProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	//---------------------------------Get/Set----------------------------
	public List<RecordUpdateProcess> getSaveOrUpdateRecordProcesses() {
		return saveOrUpdateRecordProcesses;
	}


	public void setSaveOrUpdateRecordProcesses(
			List<RecordUpdateProcess> saveOrUpdateRecordProcesses) {
		this.saveOrUpdateRecordProcesses = saveOrUpdateRecordProcesses;
	}

	public List<RecordUpdateProcess> getFindRecordUpdateProcesses() {
		return findRecordUpdateProcesses;
	}

	public void setFindRecordUpdateProcesses(
			List<RecordUpdateProcess> findRecordUpdateProcesses) {
		this.findRecordUpdateProcesses = findRecordUpdateProcesses;
	}

}
