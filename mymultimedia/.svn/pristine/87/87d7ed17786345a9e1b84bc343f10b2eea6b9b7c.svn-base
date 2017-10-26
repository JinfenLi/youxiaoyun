/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:28:05 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.po.MultimediaCollect;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.CollectInfo;

/** 
 * @ClassName: CollectSaveProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:28:05 
 *  
 */
@Service
public class CollectSaveProcess implements CollectProcess{
	
	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;
	private static final Logger logger = Logger
			.getLogger(CollectSaveProcess.class);
	
	
	
	public boolean doProcess(CollectProcessContext context) {
		try{
		MultimediaCollect collect = CollectInfo.changeToPo(context.getInfo());
	
		List<MultimediaCollect> list = multimediaCollectMapper.findByPointId(collect.getPointid());
		if(list.size()==0) {
		String id = UUIDUtil.getUUID();
		collect.setId(id);
		collect.setCreattime(new Date());
		multimediaCollectMapper.insert(collect);
		context.getResult().setSuccess(true);
		context.getResult().setCollectid(id);
		return true;
		}
		context.getResult().setSuccess(false);
		return false;
		}catch(Exception e) {
			logger.error("collect process fail");
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
