package com.topview.multimedia.service.section.draft;

import java.util.List;
import java.util.Map;

import com.topview.multimedia.po.MultimediaRichText;

public class DraftServiceImpl implements DraftService {

	private List<DraftProcess> draftSaveProcesses;
	private List<DraftProcess> draftDeleteProcesses;
	private List<DraftProcess> draftUpdateProcesses;
	private List<DraftProcess> draftFindProcesses;
	private List<DraftProcess> draftSendProcesses;

	public Map<String, Object> draftSave(MultimediaRichText richText) {
		return doProcess(richText, draftSaveProcesses);
	}

	public Map<String, Object> draftDelete(MultimediaRichText richText) {
		return doProcess(richText, draftDeleteProcesses);
	}

	public Map<String, Object> draftUpdate(MultimediaRichText richText) {
		return doProcess(richText, draftUpdateProcesses);
	}

	public Map<String, Object> draftFind(MultimediaRichText richText) {
		return doProcess(richText, draftFindProcesses);
	}

	public Map<String, Object> draftSend(MultimediaRichText richText,
			String type) {
		DraftProcessContext context = new DraftProcessContext();
		context.setRichText(richText);
		context.setRichTextType(type);
		for (DraftProcess process : draftSendProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}


	public List<DraftProcess> getDraftSaveProcesses() {
		return draftSaveProcesses;
	}

	public void setDraftSaveProcesses(List<DraftProcess> draftSaveProcesses) {
		this.draftSaveProcesses = draftSaveProcesses;
	}

	public List<DraftProcess> getDraftDeleteProcesses() {
		return draftDeleteProcesses;
	}

	public void setDraftDeleteProcesses(List<DraftProcess> draftDeleteProcesses) {
		this.draftDeleteProcesses = draftDeleteProcesses;
	}

	public List<DraftProcess> getDraftUpdateProcesses() {
		return draftUpdateProcesses;
	}

	public void setDraftUpdateProcesses(List<DraftProcess> draftUpdateProcesses) {
		this.draftUpdateProcesses = draftUpdateProcesses;
	}

	public List<DraftProcess> getDraftFindProcesses() {
		return draftFindProcesses;
	}

	public void setDraftFindProcesses(List<DraftProcess> draftFindProcesses) {
		this.draftFindProcesses = draftFindProcesses;
	}

	public List<DraftProcess> getDraftSendProcesses() {
		return draftSendProcesses;
	}

	public void setDraftSendProcesses(List<DraftProcess> draftSendProcesses) {
		this.draftSendProcesses = draftSendProcesses;
	}

	public Map<String, Object> doProcess(MultimediaRichText richText,
			List<DraftProcess> processes) {
		DraftProcessContext context = new DraftProcessContext();
		context.setRichText(richText);
		for (DraftProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

}
