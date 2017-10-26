package com.topview.multimedia.service.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;

/** * @author  Rachel 
@date 创建时间：2016年8月6日 下午12:12:08 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileJudgeOwnerProcess implements FileProcess {
	
	private static final Logger logger = Logger
			.getLogger(FileJudgeOwnerProcess.class);
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;

	
	public boolean doProcess(FileProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uploader_id", context.getInfo().getUploaderId());
			params.put("id", context.getInfo().getId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<MultimediaFile> l = multimediaFileMapper.findByMulti(param);
			if(l.size()>0){
				context.getResult().setSuccess(true);
				return true;
			}else{
				context.getResult().setSuccess(false);
				return false;
			}
		} catch (Exception e) {
			logger.error("no permission to delete file fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
