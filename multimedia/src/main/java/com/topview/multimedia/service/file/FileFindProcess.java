package com.topview.multimedia.service.file;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.vo.FileInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月30日 下午9:42:02 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileFindProcess implements FileProcess {

	private static final Logger logger = Logger
			.getLogger(FileFindProcess.class);
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;

	public boolean doProcess(FileProcessContext context) {
		try {
			MultimediaFile file = multimediaFileMapper.findById(context
					.getInfo().getId());
			List<MultimediaFile> l = new ArrayList<MultimediaFile>();
			l.add(file);
			List<FileInfo> list = FileInfo.changeToVo(l);
			context.getResult().setSuccess(true);
			context.getResult().setInfoResult(list);
			return true;
		} catch (Exception e) {
			logger.error("file find process fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
