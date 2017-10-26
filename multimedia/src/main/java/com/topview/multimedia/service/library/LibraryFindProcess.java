package com.topview.multimedia.service.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaVideoLibraryMapper;
import com.topview.multimedia.po.MultimediaVideoLibrary;
import com.topview.multimedia.vo.LibraryInfo;
/**
 * 查找视频库
 * 项目名称:com.topview.multimedia.service.library<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class LibraryFindProcess implements LibraryProcess{

	@Autowired
	private MultimediaVideoLibraryMapper multimediaVideoLibraryMapper;

	public boolean doProcess(LibraryProcessContext context) {

		try {
			MultimediaVideoLibrary library = multimediaVideoLibraryMapper.findById(context.getInfo().getId());
			List<LibraryInfo> infos = new ArrayList<LibraryInfo>();
			infos.add(LibraryInfo.changeToVo(library));
			context.getResult().setResult(infos);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
