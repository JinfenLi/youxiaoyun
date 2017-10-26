package com.topview.multimedia.service.horn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.HornMapper;
import com.topview.multimedia.po.Horn;
import com.topview.multimedia.vo.HornInfo;

/**
 * @author hcdn
 * 获取小喇叭
 */
@Service
public class HornGetProcess implements HornProcess {

	@Autowired
	private HornMapper hornMapper;
	
	@Override
	public boolean doProcess(HornProcessContext context) {
		HornInfo hornInfo = context.getHornInfo();
		if(hornInfo != null) {
			String userId = hornInfo.getUserIds().get(0);
			List<Horn> result = hornMapper.getHornByUserId(userId);
			context.getResult().setResult(HornInfo.changToVo(result));
			context.getResult().setSuccess(true);
		}else {
			context.getResult().setSuccess(false);
			return false;
		}
		return true;
	}
}
