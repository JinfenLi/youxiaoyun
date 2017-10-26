package com.topview.school.service.system.base;

import java.util.List;
import java.util.Map;

/**
 * @author Sherlock-lee
 * @date 2015年9月30日 上午11:02:02
 * @see TODO
 * @since 1.0
 */
public interface BaseService<T> {
	
	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 
	 * @Title: insert
	 * @Description: TODO
	 * @param @param t
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int insert(T t);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: TODO
	 * @param @param t
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int insertSelective(T t);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @return T
	 * @throws
	 */
	public T selectByPrimaryKey(String id);

	/**
	 * 
	 * @Title: selectByParameters
	 * @Description: TODO
	 * @param @param map
	 * @param @return
	 * @return List<T>
	 * @throws
	 */
	public List<T> selectByParameters(Map<String, Object> map);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO
	 * @param @param t
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int updateByPrimaryKeySelective(T t);

	/**
	 * 
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param @param t
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int updateByPrimaryKey(T t);


}
