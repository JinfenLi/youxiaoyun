package com.topview.multimedia.service.folder;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.po.MultimediaFolder;
import com.topview.multimedia.vo.FolderInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月30日 下午9:53:24 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FolderFindProcess implements FolderProcess {

	private static final Logger logger = Logger
			.getLogger(FolderFindProcess.class);
	@Autowired
	private MultimediaFolderMapper multimediaFolderMapper;

	public boolean doProcess(FolderProcessContext context) {
		try {
			MultimediaFolder folder = multimediaFolderMapper.findById(context
					.getInfo().getId());
			List<MultimediaFolder> l = new ArrayList<MultimediaFolder>();
			l.add(folder);
			List<FolderInfo> list = FolderInfo.changeToVo(l);
			context.getResult().setSuccess(true);
			context.getResult().setInfoResult(list);
			return true;
		} catch (Exception e) {
			logger.error("folder find process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
