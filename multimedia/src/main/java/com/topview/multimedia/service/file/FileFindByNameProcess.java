package com.topview.multimedia.service.file;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.vo.FileInfo;

/** * @author  Rachel 
@date 创建时间：2016年8月12日 下午11:16:47 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileFindByNameProcess implements FileProcess {
	
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;
	private static final Logger logger = Logger
			.getLogger(FileUpdateProcess.class);

	
	public boolean doProcess(FileProcessContext context) {
		try {
			MultimediaFile file = FileInfo.changeToPo(context.getInfo());
			List<MultimediaFile> list = multimediaFileMapper.
					findByName(file.getName());
					
			if (list.size() == 0) {
				context.getResult().setCode(0);
				context.getResult().setSuccess(false);
			} else {
				context.getResult().setCode(1);
				context.getResult().setInfoResult(FileInfo.changeToVo(list));
			    context.getResult().setSuccess(true);
			}
			return true;

		} catch (Exception e) {
			logger.error("find file fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
