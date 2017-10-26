package com.topview.multimedia.service.account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaZoneMapper;
import com.topview.multimedia.po.MultimediaZone;
import com.topview.multimedia.vo.AccountInfo;
/**
 * 更新账号信息
 * 项目名称:com.topview.multimedia.service.account<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class UpdateProcess implements AccountProcess {
	private static final Logger logger = Logger.getLogger(UpdateProcess.class);

	@Autowired
	private MultimediaZoneMapper multimediaZoneMapper;

	public boolean doProcess(AccountProcessContext context) {
		try {
			MultimediaZone zone = AccountInfo.changeToPo(context.getInfo());
			if(multimediaZoneMapper.updateByPrimaryKeySelective(zone) > 0) {
				context.getResult().setSuccess(true);
			} else {
				context.getResult().setSuccess(false);
			}
			return true;
		} catch (Exception e) {
			logger.error("update fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
