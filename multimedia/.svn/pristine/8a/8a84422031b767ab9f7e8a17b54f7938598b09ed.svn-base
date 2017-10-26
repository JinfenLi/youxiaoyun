package com.topview.multimedia.service.file;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.vo.FileInfo;

/**
 * 修改文件
 *  * @author  Rachel 
@date 创建时间：2016年8月12日 下午7:39:52 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileUpdateProcess implements FileProcess {
	
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;
	private static final Logger logger = Logger
			.getLogger(FileUpdateProcess.class);

	
	public boolean doProcess(FileProcessContext context) {
		try {
			MultimediaFile file = FileInfo.changeToPo(context.getInfo());
			
				multimediaFileMapper.updateByPrimaryKeySelective(file);
				context.getResult().setSuccess(true);
			return true;
			
		} catch (Exception e) {
			logger.error("update file fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
