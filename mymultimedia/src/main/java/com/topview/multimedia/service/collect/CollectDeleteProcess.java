/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:28:18 
 * @version V1.0
 */
package com.topview.multimedia.service.collect;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.po.MultimediaCollect;
import com.topview.multimedia.vo.CollectInfo;

/**
 * @ClassName: CollectDeleteProcess
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:28:18
 * 
 */
@Service
public class CollectDeleteProcess implements CollectProcess {
	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;
	private static final Logger logger = Logger
			.getLogger(CollectDeleteProcess.class);

	public boolean doProcess(CollectProcessContext context) {

		try {

			MultimediaCollect collect = CollectInfo.changeToPo(context
					.getInfo());
			// multimediaCollectMapper.deleteByPrimaryKey(context.getInfo().getId());
			multimediaCollectMapper.deleteByUseridAndCid(collect);
			context.getResult().setSuccess(true);
		} catch (Exception e) {
			logger.error("collect delete fail");
			context.getResult().setSuccess(false);
			return false;
		}

		return false;
	}

}
