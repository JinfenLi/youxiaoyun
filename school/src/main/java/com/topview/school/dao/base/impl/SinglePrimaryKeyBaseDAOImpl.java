package com.topview.school.dao.base.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;

import com.topview.school.dao.base.SinglePrimaryKeyBaseMapper;

/**
 * @author Sherlock-lee
 * @version 创建时间：2015年3月20日 上午8:56:12
 * @param <T>
 * @Tel 15622358381
 * @state 单主键基础DAO实现类
 */
public class SinglePrimaryKeyBaseDAOImpl<T> implements
		SinglePrimaryKeyBaseMapper<T> {

	@Resource(name = "sqlSessionTemplate")
	protected SqlSessionTemplate template;

	private static final Logger logger = Logger
			.getLogger(SinglePrimaryKeyBaseMapper.class);

	@Override
	public int deleteByPrimaryKey(String id) {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteByPrimaryKey(String) - start");
		}
		int sign = 0;
		try {

			sign = template.insert(getNamespace("deleteByPrimaryKey"), id);
		} catch (Exception e) {
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteByPrimaryKey(String) - end");
		}
		return sign;
	}

	@Override
	public int insert(T record) {

		if (logger.isDebugEnabled()) {
			logger.debug("insert(T) - start");
		}
		int sign = 0;
		try {

			sign = template.insert(getNamespace("insert"), record);
		} catch (Exception e) {
		}
		if (logger.isDebugEnabled()) {
			logger.debug("insert(T) - end");
		}
		return sign;
	}

	@Override
	public int insertSelective(T record) {

		if (logger.isDebugEnabled()) {
			logger.debug("insertSelective(T) - start");
		}
		int sign = 0;
		try {

			sign = template.insert(getNamespace("insertSelective"), record);
		} catch (Exception e) {
		}
		if (logger.isDebugEnabled()) {
			logger.debug("insertSelective(T) - end");
		}
		return sign;
	}

	@Override
	public T selectByPrimaryKey(String id) {

		if (logger.isDebugEnabled()) {
			logger.debug("selectByPrimaryKey(String) - start");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("selectByPrimaryKey(String) - end");
		}
		return template.selectOne(getNamespace("selectByPrimaryKey"), id);
	}

	@Override
	public List<T> selectByParameters(Map<String, Object> map) {

		if (logger.isDebugEnabled()) {
			logger.debug("selectByParamsters(Map<String, Object>) - start");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("selectByParamsters(Map<String, Object>) - end");
		}
		return template.selectList(getNamespace("selectByParameters"), map);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateByPrimaryKey(T) - start");
		}
		int sign = 0;
		try {
			sign = template.update(getNamespace("updateByPrimaryKey"), record);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateByPrimaryKey(T) - end");
		}
		return sign;
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateByPrimaryKeySelective(T) - start");
		}
		int sign = 0;
		try {
			sign = template.update(getNamespace("updateByPrimaryKeySelective"), record);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateByPrimaryKeySelective(T) - end");
		}
		return sign;
	}

	/**
	 * 
	 * @author Sherlock-lee 功能: 返回类实现的第一个接口名称
	 * @return 第一个接口名称
	 */
	private String getFirstInterface() {
		if (logger.isDebugEnabled()) {
			logger.debug("getFirstInterface() - start");
		}
		String returnString = this.getClass().getInterfaces()[0].toString()
				.split(" ")[1].toString();

		if (logger.isDebugEnabled()) {
			logger.debug("getFirstInterface() - end");
		}
		return returnString;
	}

	/**
	 * 
	 * @author Sherlock-lee 功能:为获取namespace特别提供的方法
	 * @return namespace
	 */
	protected String getNamespace(String methodName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace(int) - start");
		}

		String returnString = getFirstInterface() + "." + methodName;
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace(int) - end");
		}
		return returnString;
	}

}
