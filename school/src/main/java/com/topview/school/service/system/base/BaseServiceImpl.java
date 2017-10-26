package com.topview.school.service.system.base;

import java.util.List;
import java.util.Map;

import com.topview.school.dao.base.SinglePrimaryKeyBaseMapper;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 上午11:03:31
 * @see TODO
 * @since 1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private SinglePrimaryKeyBaseMapper<T> baseDao;

	public void setBaseDao(SinglePrimaryKeyBaseMapper<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return baseDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T t) {
		return baseDao.insert(t);
	}

	@Override
	public int insertSelective(T t) {
		return baseDao.insertSelective(t);
	}

	@Override
	public T selectByPrimaryKey(String id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public List<T> selectByParameters(Map<String, Object> map) {
		return baseDao.selectByParameters(map);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) {
		return baseDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByPrimaryKey(T t) {
		return baseDao.updateByPrimaryKey(t);
	}

}
