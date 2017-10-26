/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 上午10:48:47 
 * @version V1.0
 */
package com.topview.school.service.clazz.album.photo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.vo.result.PhotoInfoResult;

/** 
 * @ClassName: ClazzPhotoDeleteProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 上午10:48:47 
 *  
 */
@Service
public class ClazzPhotoDeleteProcess implements ClazzPhotoProcess{

	private static final Logger logger = Logger
			.getLogger(ClazzPhotoDeleteProcess.class);
	@Autowired
	private PhotoService photoService;


	
	@Override
	public boolean doProcess(ClazzPhotoProcessContext context) {
		try {
			PhotoInfoResult  result = photoService.photoDelete(context.getInfo());
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e) {
			logger.error("clazz delete photo fail");
			context.getResult().setSuccess(false);
			return false;
		}
		
	}

}
