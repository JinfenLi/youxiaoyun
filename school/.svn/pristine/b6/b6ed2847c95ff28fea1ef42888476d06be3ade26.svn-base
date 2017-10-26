package com.topview.school.dao.base;

import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：school<br>
 * 类名称：BaseDao <br>
 * 类描述： 基础dao层接口 <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月12日 下午9:16:55 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月12日 下午9:16:55 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
public interface BaseDao<T> {

	public int deleteByPrimaryKey(String id);

	public int insert(T t);

	public int insertSelective(T t);

	public T selectByPrimaryKey(String id);

	public List<T> selectByParameters(Map<String, Object> map);

	public int updateByPrimaryKeySelective(T t);

	public int updateByPrimaryKey(T t);

	public List<T> selectAll(int offset, int limit);

}
