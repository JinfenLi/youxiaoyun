package com.topview.school.service.feedback;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.feedback.RecordUrlMapper;
import com.topview.school.po.RecordUrl;

@Service
public class RecordUrlServiceImpl implements RecordUrlService{
		
	@Resource
	private RecordUrlMapper recordUrlMapper;

	@Override
	public String selectUrlByKey(String key) {
		return recordUrlMapper.selectUrlByKey(key);
	}

	@Override
	public List<RecordUrl> selectAllKeysAndUrl() {
		return recordUrlMapper.selectAllKeysAndUrl();
	}

}
