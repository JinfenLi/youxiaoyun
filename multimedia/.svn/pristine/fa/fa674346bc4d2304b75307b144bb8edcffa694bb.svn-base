package com.topview.multimedia.service.horn;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.HornMapper;
import com.topview.multimedia.dao.HornUserMapper;
import com.topview.multimedia.po.Horn;
import com.topview.multimedia.po.HornUser;
import com.topview.multimedia.util.NotEmptyString;
import com.topview.multimedia.vo.HornInfo;

/**
 * @author hcdn
 * 发送小喇叭
 */
@Service
public class HornPushProcess implements HornProcess{
	private static final Logger logger = Logger.getLogger(HornPushProcess.class);
	
	@Autowired
	private HornMapper hornMapper;
	
	@Autowired
	private HornUserMapper hornUserMapper;

	@Override
	public boolean doProcess(HornProcessContext context) {
		HornInfo hornInfo = context.getHornInfo();
		Horn horn = new Horn();
		if(NotEmptyString.isNotNull(hornInfo)) {
			try {
				horn = HornInfo.changeToPo(hornInfo);
				hornMapper.insert(horn);
			}catch(Exception e) {
				logger.error("小喇叭发送失败");

			}
			for(String userId : hornInfo.getUserIds()) {
				HornUser hornUser = new HornUser();
				hornUser.setHornId(horn.getId());
				hornUser.setUserId(userId);
				hornUserMapper.insert(hornUser);
			}
			context.getResult().setSuccess(true);
			return true;
		}else {
			context.getResult().setSuccess(false);
			return false;
		}
	}
	
}
