package com.topview.multimedia.service.album;

import java.util.List;

import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;

public class AlbumServiceImpl implements AlbumService {

	private List<AlbumProcess> albumCreateProcesses;
	private List<AlbumProcess> albumDeleteProcesses;
	private List<AlbumProcess> albumUpdateProcesses;
	private List<AlbumProcess> albumFindAllProcesses;
	private List<AlbumProcess> albumFindByTypeProcesses;
	private List<AlbumProcess> albumFindNoPagerProcesses;
	private List<AlbumProcess> albumFindByNameProcesses;
	private List<AlbumProcess> albumSaveCoverProcesses;
	private List<AlbumProcess> albumDeletesProcesses;

	public AlbumInfoResult albumCreate(AlbumInfo info) {
		return doProcess(info, albumCreateProcesses);
	}

	public AlbumInfoResult albumDelete(AlbumInfo info) {
		return doProcess(info, albumDeleteProcesses);
	}
	
	public AlbumInfoResult albumDeletes(AlbumInfo info) {
		return doProcess(info, albumDeletesProcesses);
	}

	public AlbumInfoResult albumUpdate(AlbumInfo info) {
		return doProcess(info, albumUpdateProcesses);
	}

	public AlbumInfoResult albumFindAll(AlbumInfo info) {
		return doProcess(info, albumFindAllProcesses);
	}

	public AlbumInfoResult albumFindByType(AlbumInfo info){
		return doProcess(info, albumFindByTypeProcesses);
	}
	public AlbumInfoResult albumFindNoPager(AlbumInfo info){
		return doProcess(info, albumFindNoPagerProcesses);
	}
	public AlbumInfoResult albumSaveCover(AlbumInfo info) {
		return doProcess(info, albumSaveCoverProcesses);
	}

	public AlbumInfoResult albumFindByName(AlbumInfo info) {
		return doProcess(info,albumFindByNameProcesses);
	}
	
	public List<AlbumProcess> getAlbumSaveCoverProcesses() {
		return albumSaveCoverProcesses;
	}

	public void setAlbumSaveCoverProcesses(List<AlbumProcess> albumSaveCoverProcesses) {
		this.albumSaveCoverProcesses = albumSaveCoverProcesses;
	}

	public List<AlbumProcess> getAlbumCreateProcesses() {
		return albumCreateProcesses;
	}

	public void setAlbumCreateProcesses(List<AlbumProcess> albumCreateProcesses) {
		this.albumCreateProcesses = albumCreateProcesses;
	}

	public List<AlbumProcess> getAlbumDeleteProcesses() {
		return albumDeleteProcesses;
	}

	public void setAlbumDeleteProcesses(List<AlbumProcess> albumDeleteProcesses) {
		this.albumDeleteProcesses = albumDeleteProcesses;
	}

	public List<AlbumProcess> getAlbumUpdateProcesses() {
		return albumUpdateProcesses;
	}

	public void setAlbumUpdateProcesses(List<AlbumProcess> albumUpdateProcesses) {
		this.albumUpdateProcesses = albumUpdateProcesses;
	}

	public List<AlbumProcess> getAlbumFindAllProcesses() {
		return albumFindAllProcesses;
	}

	public void setAlbumFindAllProcesses(
			List<AlbumProcess> albumFindAllProcesses) {
		this.albumFindAllProcesses = albumFindAllProcesses;
	}

	public List<AlbumProcess> getAlbumFindByTypeProcesses() {
		return albumFindByTypeProcesses;
	}

	public void setAlbumFindByTypeProcesses(
			List<AlbumProcess> albumFindByTypeProcesses) {
		this.albumFindByTypeProcesses = albumFindByTypeProcesses;
	}
	
	public List<AlbumProcess> getAlbumFindNoPagerProcesses() {
		return albumFindNoPagerProcesses;
	}

	public void setAlbumFindNoPagerProcesses(
			List<AlbumProcess> albumFindNoPagerProcesses) {
		this.albumFindNoPagerProcesses = albumFindNoPagerProcesses;
	}

	public List<AlbumProcess> getAlbumFindByNameProcesses() {
		return albumFindByNameProcesses;
	}

	public void setAlbumFindByNameProcesses(
			List<AlbumProcess> albumFindByNameProcesses) {
		this.albumFindByNameProcesses = albumFindByNameProcesses;
	}

	public List<AlbumProcess> getAlbumDeletesProcesses() {
		return albumDeletesProcesses;
	}

	public void setAlbumDeletesProcesses(List<AlbumProcess> albumDeletesProcesses) {
		this.albumDeletesProcesses = albumDeletesProcesses;
	}

	public AlbumInfoResult doProcess(AlbumInfo info,
			List<AlbumProcess> processes) {
		AlbumProcessContext context = new AlbumProcessContext();
		AlbumInfoResult result = new AlbumInfoResult();
		context.setInfo(info);
		context.setResult(result);
		for (AlbumProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	




}
