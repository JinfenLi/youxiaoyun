package com.topview.school.service.clazz.album;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;

public class ClazzAlbumServiceImpl implements ClazzAlbumService {

	private List<ClazzAlbumProcess> clazzAlbumFindProcesses;
	private List<ClazzAlbumProcess> clazzAlbumFindNoPagerProcesses;
	private List<ClazzAlbumProcess> clazzAlbumFindByTypeProcesses;
	private List<ClazzAlbumProcess> clazzAlbumCreateProcesses;
	private List<ClazzAlbumProcess> clazzAlbumFindByNameProcesses;
	private List<ClazzAlbumProcess> clazzAlbumDeleteProcesses;

	/**
	 * 获取相册
	 */
	@Override
	public AlbumInfoResult getAlbum(String schoolId, Pager pager) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfo info = new AlbumInfo();
		AlbumInfoResult result = new AlbumInfoResult();
		info.settMId(schoolId);
		context.setResult(result);
		info.setPager(pager);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();

	}

	/**
	 * 根据多媒体空间id和相册类型查询所有相册（类型为1是普通相册，类型为0是轮播相册）
	 */
	public AlbumInfoResult getAlbumNoPager(String zoneId, Integer type) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfo info = new AlbumInfo();
		AlbumInfoResult result = new AlbumInfoResult();
		info.settMId(zoneId);
		if (type != null) {
			if (type == 0) {
				info.setType("ViewPager");
			} else if (type == 1) {
				info.setType("普通");
			}
		}
		context.setResult(result);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumFindNoPagerProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();

	}

	/**
	 * 根据类型获取相册
	 */
	public AlbumInfoResult getAlbumByType(String schoolId, String type) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfo info = new AlbumInfo();
		AlbumInfoResult result = new AlbumInfoResult();
		info.settMId(schoolId);
		info.setType(type);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumFindByTypeProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 创建相册
	 */
	public AlbumInfoResult createAlbum(AlbumInfo info) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfoResult result = new AlbumInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumCreateProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	@Override
	public AlbumInfoResult deleteAlbum(String albumId) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfo info = new AlbumInfo();
		AlbumInfoResult result = new AlbumInfoResult();
		info.setId(albumId);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumDeleteProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public AlbumInfoResult getAlbumByName(String id, String name) {
		ClazzAlbumProcessContext context = new ClazzAlbumProcessContext();
		AlbumInfo info = new AlbumInfo();
		AlbumInfoResult result = new AlbumInfoResult();
		info.settMId(id);
		info.setName(name);
		context.setResult(result);
		context.setInfo(info);
		for (ClazzAlbumProcess process : clazzAlbumFindByNameProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public List<ClazzAlbumProcess> getClazzAlbumFindProcesses() {
		return clazzAlbumFindProcesses;
	}

	public void setClazzAlbumFindProcesses(
			List<ClazzAlbumProcess> clazzAlbumFindProcesses) {
		this.clazzAlbumFindProcesses = clazzAlbumFindProcesses;
	}

	public List<ClazzAlbumProcess> getClazzAlbumFindByTypeProcesses() {
		return clazzAlbumFindByTypeProcesses;
	}

	public void setClazzAlbumFindByTypeProcesses(
			List<ClazzAlbumProcess> clazzAlbumFindByTypeProcesses) {
		this.clazzAlbumFindByTypeProcesses = clazzAlbumFindByTypeProcesses;
	}

	public List<ClazzAlbumProcess> getClazzAlbumCreateProcesses() {
		return clazzAlbumCreateProcesses;
	}

	public void setClazzAlbumCreateProcesses(
			List<ClazzAlbumProcess> clazzAlbumCreateProcesses) {
		this.clazzAlbumCreateProcesses = clazzAlbumCreateProcesses;
	}

	public List<ClazzAlbumProcess> getClazzAlbumFindNoPagerProcesses() {
		return clazzAlbumFindNoPagerProcesses;
	}

	public void setClazzAlbumFindNoPagerProcesses(
			List<ClazzAlbumProcess> clazzAlbumFindNoPagerProcesses) {
		this.clazzAlbumFindNoPagerProcesses = clazzAlbumFindNoPagerProcesses;
	}

	public List<ClazzAlbumProcess> getClazzAlbumFindByNameProcesses() {
		return clazzAlbumFindByNameProcesses;
	}

	public void setClazzAlbumFindByNameProcesses(
			List<ClazzAlbumProcess> clazzAlbumFindByNameProcesses) {
		this.clazzAlbumFindByNameProcesses = clazzAlbumFindByNameProcesses;
	}

	public List<ClazzAlbumProcess> getClazzAlbumDeleteProcesses() {
		return clazzAlbumDeleteProcesses;
	}

	public void setClazzAlbumDeleteProcesses(
			List<ClazzAlbumProcess> clazzAlbumDeleteProcesses) {
		this.clazzAlbumDeleteProcesses = clazzAlbumDeleteProcesses;
	}

}
