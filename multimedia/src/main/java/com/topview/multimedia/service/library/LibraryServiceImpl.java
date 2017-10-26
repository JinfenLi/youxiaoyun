package com.topview.multimedia.service.library;

import java.util.List;

import javax.annotation.Resource;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.vo.LibraryInfo;
import com.topview.multimedia.vo.result.LibraryInfoResult;

public class LibraryServiceImpl implements LibraryService {

	private List<LibraryProcess> librarySaveProcesses;
	private List<LibraryProcess> libraryDeleteProcesses;
	private List<LibraryProcess> libraryUpdateProcesses;
	private List<LibraryProcess> libraryFindAllProcesses;
	private List<LibraryProcess> libraryFindNoPagerProcesses;
	@Resource
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;
	
	public LibraryInfoResult libraryCreate(LibraryInfo info) {
		return doProcess(info, librarySaveProcesses);
	}

	public LibraryInfoResult libraryDelete(LibraryInfo info) {
		return doProcess(info, libraryDeleteProcesses);
	}

	public LibraryInfoResult libraryUpdate(LibraryInfo info) {
		return doProcess(info, libraryUpdateProcesses);
	}

	public LibraryInfoResult libraryFindAll(LibraryInfo info) {
		return doProcess(info, libraryFindAllProcesses);
	}
	public LibraryInfoResult libraryFindNoPager(LibraryInfo info) {
		return doProcess(info, libraryFindNoPagerProcesses);
	}

	public List<LibraryProcess> getLibrarySaveProcesses() {
		return librarySaveProcesses;
	}

	public void setLibrarySaveProcesses(List<LibraryProcess> librarySaveProcesses) {
		this.librarySaveProcesses = librarySaveProcesses;
	}

	public List<LibraryProcess> getLibraryDeleteProcesses() {
		return libraryDeleteProcesses;
	}

	public void setLibraryDeleteProcesses(
			List<LibraryProcess> libraryDeleteProcesses) {
		this.libraryDeleteProcesses = libraryDeleteProcesses;
	}

	public List<LibraryProcess> getLibraryUpdateProcesses() {
		return libraryUpdateProcesses;
	}

	public void setLibraryUpdateProcesses(
			List<LibraryProcess> libraryUpdateProcesses) {
		this.libraryUpdateProcesses = libraryUpdateProcesses;
	}

	public List<LibraryProcess> getLibraryFindAllProcesses() {
		return libraryFindAllProcesses;
	}

	public void setLibraryFindAllProcesses(
			List<LibraryProcess> libraryFindAllProcesses) {
		this.libraryFindAllProcesses = libraryFindAllProcesses;
	}

	public List<LibraryProcess> getLibraryFindNoPagerProcesses() {
		return libraryFindNoPagerProcesses;
	}

	public void setLibraryFindNoPagerProcesses(
			List<LibraryProcess> libraryFindNoPagerProcesses) {
		this.libraryFindNoPagerProcesses = libraryFindNoPagerProcesses;
	}

	public LibraryInfoResult doProcess(LibraryInfo info,
			List<LibraryProcess> processes) {
		LibraryProcessContext context = new LibraryProcessContext();
		LibraryInfoResult result = new LibraryInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (LibraryProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public int selectCount(String zoneId) {
		return multimediaVideoLibraryMapper.selectCount(zoneId);
	}

}
