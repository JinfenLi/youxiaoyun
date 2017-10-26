/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月1日 上午10:39:44 
 * @version V1.0
 */
package com.topview.school.service.clazz.album;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;

/** 
 * @ClassName: ClazzAlbumFindByNameProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月1日 上午10:39:44 
 *  
 */
@Service
public class ClazzAlbumFindByNameProcess implements ClazzAlbumProcess{

	
	@Autowired
	private AlbumService albumService;
	private static final Logger logger = Logger
			.getLogger(ClazzAlbumFindByNameProcess.class);
	
	@Override
	public boolean doProcess(ClazzAlbumProcessContext context) {
		
		try {
			AlbumInfo info = context.getInfo();
			AlbumInfoResult result = albumService.albumFindByName(info);
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setCode(result.getCode());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("clazz find album by type fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}
  
	
	
	
}
