/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:28:54 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.po.MultimediaCollect;
import com.topview.multimedia.vo.CollectInfo;

/**
 * @ClassName: CollectFindProcess
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:28:54
 * 
 */
@Service
public class CollectFindProcess implements CollectProcess {

	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;
	private static final Logger logger = Logger
			.getLogger(CollectFindProcess.class);

	public boolean doProcess(CollectProcessContext context) {
		try {
			List<MultimediaCollect> list = multimediaCollectMapper
					.findByUserId(context.getInfo().getUserid());
			List<CollectInfo> infos = CollectInfo.changeToVo(list);
			context.getResult().setSuccess(true);
			context.getResult().setInforesult(infos);
			return true;
		} catch (Exception e) {
			logger.error("collect find process fail");
			context.getResult().setSuccess(false);
			return false;
		}

	}

}
