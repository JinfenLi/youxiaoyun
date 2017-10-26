package com.topview.message.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.message.dao.PushKeyMapper;
import com.topview.message.po.PushKey;

@Service
public class PushKeyServiceImpl implements PushKeyService{

	@Resource
	private PushKeyMapper PushKeyMapper;
	
	public PushKey selectByApikey(String apikey) {
		return PushKeyMapper.selectByApikey(apikey);
	}

	public boolean insert(PushKey PushKey) {
		if(PushKeyMapper.insert(PushKey) == 0) {
			return false;
		}
		return true;
	}

	public boolean update(PushKey iOSPushKey) {
		if(PushKeyMapper.updateByPrimaryKey(iOSPushKey) != 0) {
			return true;
		}
		return false;
	}

	public boolean delete(String id) {
		if(PushKeyMapper.deleteByPrimaryKey(id) != 0) {
			return true;
		}
		return false;
	}

}
