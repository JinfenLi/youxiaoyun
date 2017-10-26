package com.topview.multimedia.service.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
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
public class LibraryFindNoPagerProcess implements LibraryProcess {

	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(LibraryProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<MultimediaVideoLibrary> l = multimediaVideoLibraryMapper.findByMulti0(param);
			context.getResult().setResult(LibraryInfo.changeToVo(l));
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
