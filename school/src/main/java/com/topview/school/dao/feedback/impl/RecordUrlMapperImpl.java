package com.topview.school.dao.feedback.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.feedback.RecordUrlMapper;
import com.topview.school.po.RecordUrl;

@Repository
public class RecordUrlMapperImpl extends BaseDaoImpl<RecordUrl> implements RecordUrlMapper{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stu
		return 0;
	}

	@Override
	public int insert(RecordUrl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RecordUrl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RecordUrl selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RecordUrl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RecordUrl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectUrlByKey(String key) {
		return template.selectOne(getStatement("selectUrlByKey"), key);
	}

	@Override
	public List<RecordUrl> selectAllKeysAndUrl() {
		
		return template.selectList(getStatement("selectAllKeysAndUrl"));
	}

}
