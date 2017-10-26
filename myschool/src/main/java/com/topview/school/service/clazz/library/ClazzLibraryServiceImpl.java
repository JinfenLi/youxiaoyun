package com.topview.school.service.clazz.library;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.LibraryInfo;
import com.topview.multimedia.vo.result.LibraryInfoResult;

public class ClazzLibraryServiceImpl implements ClazzLibraryService{

	private List<ClazzLibraryProcess> clazzLibraryFindProcesses;
	private List<ClazzLibraryProcess> clazzLibraryFindNoPagerProcesses;
	private List<ClazzLibraryProcess> clazzLibraryCreateProcesses;
	
	/**
	 * 获取视频库
	 */
	@Override
	public LibraryInfoResult getLibrary(String schoolId,Pager pager) {
		ClazzLibraryProcessContext context = new ClazzLibraryProcessContext();
		LibraryInfoResult result = new LibraryInfoResult();
		LibraryInfo info = new LibraryInfo();
		info.setPager(pager);
		info.settMId(schoolId);
		context.setInfo(info);
		context.setResult(result);
		for (ClazzLibraryProcess process : clazzLibraryFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public LibraryInfoResult getLibraryNoPager(String schoolId) {
		ClazzLibraryProcessContext context = new ClazzLibraryProcessContext();
		LibraryInfoResult result = new LibraryInfoResult();
		LibraryInfo info = new LibraryInfo();
		info.settMId(schoolId);
		context.setInfo(info);
		context.setResult(result);
		for (ClazzLibraryProcess process : clazzLibraryFindNoPagerProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	/**
	 * 创建视频库
	 */
	public LibraryInfoResult createLibrary(LibraryInfo info) {
		ClazzLibraryProcessContext context = new ClazzLibraryProcessContext();
		LibraryInfoResult result = new LibraryInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (ClazzLibraryProcess process : clazzLibraryCreateProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	
	public List<ClazzLibraryProcess> getClazzLibraryFindProcesses() {
		return clazzLibraryFindProcesses;
	}
	public void setClazzLibraryFindProcesses(
			List<ClazzLibraryProcess> clazzLibraryFindProcesses) {
		this.clazzLibraryFindProcesses = clazzLibraryFindProcesses;
	}
	public List<ClazzLibraryProcess> getClazzLibraryCreateProcesses() {
		return clazzLibraryCreateProcesses;
	}
	public void setClazzLibraryCreateProcesses(
			List<ClazzLibraryProcess> clazzLibraryCreateProcesses) {
		this.clazzLibraryCreateProcesses = clazzLibraryCreateProcesses;
	}

	public List<ClazzLibraryProcess> getClazzLibraryFindNoPagerProcesses() {
		return clazzLibraryFindNoPagerProcesses;
	}

	public void setClazzLibraryFindNoPagerProcesses(
			List<ClazzLibraryProcess> clazzLibraryFindNoPagerProcesses) {
		this.clazzLibraryFindNoPagerProcesses = clazzLibraryFindNoPagerProcesses;
	}

}
