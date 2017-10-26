package com.topview.multimedia.service.horn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.HornMapper;
import com.topview.multimedia.po.Horn;
import com.topview.multimedia.vo.HornInfo;

@Service
public class HornGetHistoryProcess implements HornProcess{
	
	@Autowired
	private HornMapper hornMapper;
	
	@Override
	public boolean doProcess(HornProcessContext context) {
		HornInfo hornInfo = context.getHornInfo();
		if(hornInfo != null) {
			String senderId = hornInfo.getSendId();
			List<Horn> result = hornMapper.getHornBySenderId(senderId);
			context.getResult().setResult(HornInfo.changToVo(result));
			context.getResult().setSuccess(true);
		}else {
			context.getResult().setSuccess(false);
			return false;
		}
		return true;
	}
}
