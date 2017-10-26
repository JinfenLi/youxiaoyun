package com.topview.multimedia.service.file;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午8:54:55 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileDeleteProcess implements FileProcess {

	
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;

	private static final Logger logger = Logger
			.getLogger(FileDeleteProcess.class);

	public boolean doProcess(FileProcessContext context) {
		
		context.getResult().setSuccess(false);
		try { 

			int flag =multimediaFileMapper.deleteByPrimaryKey(context.getInfo().getId()); 
			if(flag>0){
				
				File file =  new File(context.getInfo().getFilePath());
				 if (file.exists() ) {
			            if (file.delete()) {
			                context.getResult().setSuccess(true);
			                return true;
			            } else {
			                context.getResult().setSuccess(false);
			                return false;
			            }
			        } else {
			        	context.getResult().setSuccess(false);
			            return false;
			        }
			}
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("delete file fail" + e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	
	}

	

}
