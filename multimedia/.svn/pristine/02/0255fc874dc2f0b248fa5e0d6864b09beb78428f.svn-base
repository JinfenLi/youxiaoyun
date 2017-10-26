package com.topview.multimedia.service.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.dao.MultimediaVideoMapper;
import com.topview.multimedia.po.MultimediaVideo;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.vo.LibraryInfo;
/**
 * 查找全部视频库
 * 项目名称:com.topview.multimedia.service.library<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class LibraryFindAllProcess implements LibraryProcess {

	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;
	@Autowired
	private MultimediaVideoMapper multimediaVideoMapper;

	public boolean doProcess(LibraryProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<MultimediaVideoLibrary> l = multimediaVideoLibraryMapper.findByMulti(param);
			List<LibraryInfo> result = LibraryInfo.changeToVo(l);
			//设置视频库封面
			params = new HashMap<String, Object>();
			param = new HashMap<String, Object>();
			param.put("params", params);
			for (LibraryInfo info : result) {
				params.put("t_m_id", info.getId());
				List<MultimediaVideo> videos = multimediaVideoMapper.findByMulti(param);
				for (MultimediaVideo video : videos) {
					if (video.getCoverPath() != null && !"".equals(video.getCoverPath())) {
						info.setCoverPath(video.getCoverPath());
						break;
					}
				}
			}
			context.getResult().setResult(result);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
