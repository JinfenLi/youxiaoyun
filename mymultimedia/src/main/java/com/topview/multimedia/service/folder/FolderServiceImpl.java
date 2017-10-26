package com.topview.multimedia.service.folder;

import java.util.List;

import javax.annotation.Resource;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.vo.FolderInfo;
import com.topview.multimedia.vo.result.FolderInfoResult;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午1:35:25 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */

public class FolderServiceImpl implements FolderService {
	
	List<FolderProcess> folderCreateProcesses;
	List<FolderProcess> folderDeleteProcesses;
	List<FolderProcess> folderFindAllProcesses;
	List<FolderProcess> folderUpdateProcesses;
	List<FolderProcess> folderFindProcesses;
	@Resource
	private MultimediaFolderMapper multimediaFolderMapper;

	@Override
	public FolderInfoResult folderCreate(FolderInfo folder) {
		return doProcess(folder, folderCreateProcesses);
	}

	@Override
	public FolderInfoResult folderDelete(FolderInfo folder) {
		return doProcess(folder, folderDeleteProcesses);
	}

	@Override
	public FolderInfoResult folderFindAll(FolderInfo folder) {
		return doProcess(folder, folderFindAllProcesses);
	}

	@Override
	public FolderInfoResult folderUpdate(FolderInfo folder) {
		return doProcess(folder, folderUpdateProcesses);
	}

	@Override
	public FolderInfoResult folderFind(FolderInfo folder) {
		return doProcess(folder, folderFindProcesses);
	}

	
	
	public List<FolderProcess> getFolderCreateProcesses() {
		return folderCreateProcesses;
	}

	public void setFolderCreateProcesses(List<FolderProcess> folderCreateProcesses) {
		this.folderCreateProcesses = folderCreateProcesses;
	}

	public List<FolderProcess> getFolderDeleteProcesses() {
		return folderDeleteProcesses;
	}

	public void setFolderDeleteProcesses(List<FolderProcess> folderDeleteProcesses) {
		this.folderDeleteProcesses = folderDeleteProcesses;
	}

	public List<FolderProcess> getFolderFindAllProcesses() {
		return folderFindAllProcesses;
	}

	public void setFolderFindAllProcesses(List<FolderProcess> folderFindAllProcesses) {
		this.folderFindAllProcesses = folderFindAllProcesses;
	}

	public List<FolderProcess> getFolderUpdateProcesses() {
		return folderUpdateProcesses;
	}

	public void setFolderUpdateProcesses(List<FolderProcess> folderUpdateProcesses) {
		this.folderUpdateProcesses = folderUpdateProcesses;
	}

	public List<FolderProcess> getFolderFindProcesses() {
		return folderFindProcesses;
	}

	public void setFolderFindProcesses(List<FolderProcess> folderFindProcesses) {
		this.folderFindProcesses = folderFindProcesses;
	}

	public MultimediaFolderMapper getMultimediaFolderMapper() {
		return multimediaFolderMapper;
	}

	public void setMultimediaFolderMapper(
			MultimediaFolderMapper multimediaFolderMapper) {
		this.multimediaFolderMapper = multimediaFolderMapper;
	}

	public FolderInfoResult doProcess(FolderInfo folder,
			List<FolderProcess> processes) {
		FolderProcessContext context = new FolderProcessContext();
		FolderInfoResult result = new FolderInfoResult();
		context.setResult(result);
		context.setInfo(folder);
		for (FolderProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public int selectCount(String tmId) {
		return multimediaFolderMapper.selectCount(tmId);
	}

}
