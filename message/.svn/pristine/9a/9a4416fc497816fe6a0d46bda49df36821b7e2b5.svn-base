package com.topview.message.dao.base;

import java.util.List;

/**
 * @Description 基础dao层接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午10:40:27
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public interface BaseDao<T> {

	 	public int deleteByPrimaryKey(String id);

	    public int insert(T t);

	    public int insertSelective(T t);

	    public T selectByPrimaryKey(String id);

	    public int updateByPrimaryKeySelective(T t);

	    public int updateByPrimaryKey(T t);
			
	    public List<T> selectAll(int offset, int limit);
}

