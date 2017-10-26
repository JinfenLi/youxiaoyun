package com.topview.multimedia.service.file;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.FileInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午8:36:37 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileSaveProcess implements FileProcess {

	@Resource
	private RecordUpdateService recordUpdateService;
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;
	
	private static final Logger logger = Logger
			.getLogger(FileSaveProcess.class);

	/**
	 * 保存照片、相册照片数量加一、更新照片模块的最后更新时间
	 */
	public boolean doProcess(FileProcessContext context) {
		try {
			MultimediaFile file = FileInfo.changeToPo(context.getInfo());
			file.setId(UUIDUtil.getUUID());
			file.setUploadTime(new Date());
			if(file.getType()==-1){
				context.getResult().setSuccess(false);
				context.getResult().setCode(400);
				return false;
			}else{
				multimediaFileMapper.insert(file);
				context.getResult().setSuccess(true);
			return true;
			}
		} catch (Exception e) {
			logger.error("save file fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
