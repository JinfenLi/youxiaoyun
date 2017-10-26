package com.topview.school.service.clazz.album.photo;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.PhotoInfo;
import com.topview.multimedia.vo.result.PhotoInfoResult;

public class ClazzPhotoServiceImpl implements ClazzPhotoService {

	private List<ClazzPhotoProcess> clazzPhotoFindAllProcesses;
	private List<ClazzPhotoProcess> clazzPhotoFindProcesses;
	private List<ClazzPhotoProcess> clazzPhotoFindNoPagerProcesses;
	private List<ClazzPhotoProcess> clazzPhotoSaveProcesses;
	private List<ClazzPhotoProcess> clazzPhotoUpdateProcesses;
	private List<ClazzPhotoProcess> clazzPhotoDeleteProcesses;
	private List<ClazzPhotoProcess> clazzPhotoUpdateNameProcesses;

	
	
	@Override
	public PhotoInfoResult getAllPhoto(String albumId,Pager pager) {
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		PhotoInfo info = new PhotoInfo();
		info.setPager(pager);
		info.settMId(albumId);
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoFindAllProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	public PhotoInfoResult getPhotoNoPager(String albumId) {
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		PhotoInfo info = new PhotoInfo();
		info.settMId(albumId);
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoFindNoPagerProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public PhotoInfoResult getPhoto(String photoId){
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		PhotoInfo info = new PhotoInfo();
		info.setId(photoId);
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public PhotoInfoResult savePhoto(PhotoInfo info){
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoSaveProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	public PhotoInfoResult updatePhoto(PhotoInfo info){
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoUpdateProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	
	public PhotoInfoResult updatePhotoName(PhotoInfo info) {
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (ClazzPhotoProcess process : clazzPhotoUpdateNameProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	
	public PhotoInfoResult deletePhoto(PhotoInfo info) {
		ClazzPhotoProcessContext context = new ClazzPhotoProcessContext();
		PhotoInfoResult result = new PhotoInfoResult();
		context.setInfo(info);
		context.setResult(result);
		
		
		for (ClazzPhotoProcess process : clazzPhotoDeleteProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	public List<ClazzPhotoProcess> getClazzPhotoFindProcesses() {
		return clazzPhotoFindProcesses;
	}

	public void setClazzPhotoFindProcesses(
			List<ClazzPhotoProcess> clazzPhotoFindProcesses) {
		this.clazzPhotoFindProcesses = clazzPhotoFindProcesses;
	}

	public List<ClazzPhotoProcess> getClazzPhotoFindAllProcesses() {
		return clazzPhotoFindAllProcesses;
	}

	public void setClazzPhotoFindAllProcesses(
			List<ClazzPhotoProcess> clazzPhotoFindAllProcesses) {
		this.clazzPhotoFindAllProcesses = clazzPhotoFindAllProcesses;
	}

	public List<ClazzPhotoProcess> getClazzPhotoSaveProcesses() {
		return clazzPhotoSaveProcesses;
	}

	public void setClazzPhotoSaveProcesses(
			List<ClazzPhotoProcess> clazzPhotoSaveProcesses) {
		this.clazzPhotoSaveProcesses = clazzPhotoSaveProcesses;
	}

	public List<ClazzPhotoProcess> getClazzPhotoUpdateProcesses() {
		return clazzPhotoUpdateProcesses;
	}

	public void setClazzPhotoUpdateProcesses(
			List<ClazzPhotoProcess> clazzPhotoUpdateProcesses) {
		this.clazzPhotoUpdateProcesses = clazzPhotoUpdateProcesses;
	}
	public List<ClazzPhotoProcess> getClazzPhotoFindNoPagerProcesses() {
		return clazzPhotoFindNoPagerProcesses;
	}
	public void setClazzPhotoFindNoPagerProcesses(
			List<ClazzPhotoProcess> clazzPhotoFindNoPagerProcesses) {
		this.clazzPhotoFindNoPagerProcesses = clazzPhotoFindNoPagerProcesses;
	}

	public List<ClazzPhotoProcess> getClazzPhotoDeleteProcesses() {
		return clazzPhotoDeleteProcesses;
	}
	public void setClazzPhotoDeleteProcesses(
			List<ClazzPhotoProcess> clazzPhotoDeleteProcesses) {
		this.clazzPhotoDeleteProcesses = clazzPhotoDeleteProcesses;
	}


	public List<ClazzPhotoProcess> getClazzPhotoUpdateNameProcesses() {
		return clazzPhotoUpdateNameProcesses;
	}
	public void setClazzPhotoUpdateNameProcesses(
			List<ClazzPhotoProcess> clazzPhotoUpdateNameProcesses) {
		this.clazzPhotoUpdateNameProcesses = clazzPhotoUpdateNameProcesses;
	}

}
