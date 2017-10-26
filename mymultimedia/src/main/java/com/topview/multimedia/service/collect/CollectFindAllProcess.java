/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月28日 下午9:35:56 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.po.MultimediaCollect;
import com.topview.multimedia.vo.CollectInfo;

/** 
 * @ClassName: CollectFindAllProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月28日 下午9:35:56 
 *  
 */
@Service
public class CollectFindAllProcess implements CollectProcess{

	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;
	private static final Logger logger = Logger
			.getLogger(CollectFindAllProcess.class);
      
	
	
	public boolean doProcess(CollectProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userid", context.getInfo().getUserid());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);	
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<MultimediaCollect> list = multimediaCollectMapper.findByMulti(param);
			
			context.getResult().setSuccess(true);
			if(list!=null) {
			List<CollectInfo> infos = CollectInfo.changeToVo(list);
			context.getResult().setInforesult(infos);
			}
			return true;
		} catch (Exception e) {
			logger.error("find collect error");
			context.getResult().setSuccess(false);
			return false;
		}
	}
	
}
