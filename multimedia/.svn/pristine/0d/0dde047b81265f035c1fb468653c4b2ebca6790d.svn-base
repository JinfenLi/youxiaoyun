package com.topview.multimedia.service.section;

import java.util.List;

import com.topview.multimedia.vo.SectionInfo;
import com.topview.multimedia.vo.result.SectionInfoResult;

public class SectionServiceImpl implements SectionService {

	private List<SectionProcess> sectionCreateProcesses;
	private List<SectionProcess> sectionUpdateProcesses;
	private List<SectionProcess> sectionDeleteProcesses;
	private List<SectionProcess> sectionFindAllProcesses;
	private List<SectionProcess> sectionFindByTypeProcesses;

	public SectionInfoResult sectionCreate(SectionInfo info) {
		return doProcess(info, sectionCreateProcesses);
	}

	public SectionInfoResult sectionUpdate(SectionInfo info) {
		return doProcess(info, sectionUpdateProcesses);
	}

	public SectionInfoResult sectionDelete(SectionInfo info) {
		return doProcess(info, sectionDeleteProcesses);
	}

	public SectionInfoResult sectionFindAll(SectionInfo info) {
		return doProcess(info, sectionFindAllProcesses);
	}

	public SectionInfoResult sectionFindByType(SectionInfo info){
		return doProcess(info, sectionFindByTypeProcesses);
	}
	public List<SectionProcess> getSectionCreateProcesses() {
		return sectionCreateProcesses;
	}

	public void setSectionCreateProcesses(
			List<SectionProcess> sectionCreateProcesses) {
		this.sectionCreateProcesses = sectionCreateProcesses;
	}

	public List<SectionProcess> getSectionUpdateProcesses() {
		return sectionUpdateProcesses;
	}

	public void setSectionUpdateProcesses(
			List<SectionProcess> sectionUpdateProcesses) {
		this.sectionUpdateProcesses = sectionUpdateProcesses;
	}

	public List<SectionProcess> getSectionDeleteProcesses() {
		return sectionDeleteProcesses;
	}

	public void setSectionDeleteProcesses(
			List<SectionProcess> sectionDeleteProcesses) {
		this.sectionDeleteProcesses = sectionDeleteProcesses;
	}

	public List<SectionProcess> getSectionFindAllProcesses() {
		return sectionFindAllProcesses;
	}

	public void setSectionFindAllProcesses(
			List<SectionProcess> sectionFindAllProcesses) {
		this.sectionFindAllProcesses = sectionFindAllProcesses;
	}

	public List<SectionProcess> getSectionFindByTypeProcesses() {
		return sectionFindByTypeProcesses;
	}

	public void setSectionFindByTypeProcesses(
			List<SectionProcess> sectionFindByTypeProcesses) {
		this.sectionFindByTypeProcesses = sectionFindByTypeProcesses;
	}

	public SectionInfoResult doProcess(SectionInfo info,
			List<SectionProcess> processes) {
		SectionProcessContext context = new SectionProcessContext();
		SectionInfoResult result = new SectionInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (SectionProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}


}
