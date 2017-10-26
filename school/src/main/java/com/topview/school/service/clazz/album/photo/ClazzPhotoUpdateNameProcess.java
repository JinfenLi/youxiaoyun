/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月5日 上午10:02:22 
 * @version V1.0
 */
package com.topview.school.service.clazz.album.photo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.vo.result.PhotoInfoResult;

/** 
 * @ClassName: ClazzPhotoUpdateNameProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月5日 上午10:02:22 
 *  
 */
@Service
public class ClazzPhotoUpdateNameProcess implements ClazzPhotoProcess{
	private static final Logger logger = Logger
			.getLogger(ClazzPhotoUpdateNameProcess.class);
	@Autowired
	private PhotoService photoService;
	
	@Override
	public boolean doProcess(ClazzPhotoProcessContext context) {
		try{
			
			PhotoInfoResult result = photoService.photoUpdateName(context.getInfo());
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(true);
			return true;
			
		}catch(Exception e) {
			logger.error("clazz update photo fail");
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
