package com.topview.multimedia.service.horn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.topview.multimedia.dao.HornMapper;
import com.topview.multimedia.vo.HornInfo;
import com.topview.multimedia.vo.result.HornInfoResult;

public class HornServiceImpl implements HornService {

	private List<HornProcess> hornPushProcesses;
	private List<HornProcess> hornGetProcesses;
	private List<HornProcess> hornGetHistoryProcesses;
	
	@Autowired
	private HornMapper hornMapperImpl;

	public List<HornProcess> getHornGetHistoryProcesses() {
		return hornGetHistoryProcesses;
	}

	public void setHornGetHistoryProcesses(List<HornProcess> hornGetHistoryProcesses) {
		this.hornGetHistoryProcesses = hornGetHistoryProcesses;
	}

	public List<HornProcess> getHornPushProcesses() {
		return hornPushProcesses;
	}

	public void setHornPushProcesses(List<HornProcess> hornPushProcesses) {
		this.hornPushProcesses = hornPushProcesses;
	}

	public List<HornProcess> getHornGetProcesses() {
		return hornGetProcesses;
	}

	public void setHornGetProcesses(List<HornProcess> hornGetProcesses) {
		this.hornGetProcesses = hornGetProcesses;
	}

	@Override
	public HornInfoResult hornPushProcess(HornInfo hornInfo) {
		HornProcessContext context = new HornProcessContext();
		context.setHornInfo(hornInfo);
		context.setResult(new HornInfoResult());
		for (HornProcess process : hornPushProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	@Override
	public HornInfoResult hornGetProcess(HornInfo hornInfo) {
		HornProcessContext context = new HornProcessContext();
		context.setHornInfo(hornInfo);
		context.setResult(new HornInfoResult());
		for (HornProcess process : hornGetProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	@Override
	public HornInfoResult hornGetHistoryProcess(HornInfo hornInfo) {
		HornProcessContext context = new HornProcessContext();
		context.setHornInfo(hornInfo);
		context.setResult(new HornInfoResult());
		for (HornProcess process : hornGetHistoryProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	@Override
	public boolean deleteHornById(String id) {
		return hornMapperImpl.deleteByPrimaryKey(id) == 1? true: false;
	}
	
}
