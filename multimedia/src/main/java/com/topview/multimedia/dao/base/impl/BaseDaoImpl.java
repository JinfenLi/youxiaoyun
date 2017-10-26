package com.topview.multimedia.dao.base.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.topview.multimedia.dao.base.BaseDao;

/**
 * 
 * 项目名称：news<br>
 * 类名称：BaseDaoImpl <br>
 * 类描述： 基础dao层的实现类 <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月12日 下午9:23:00 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月12日 下午9:23:00 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name = "sqlSessionTemplate_multimedia")
	protected SqlSessionTemplate template;
	
	/**
	 * 按主键删除
	 */
	public int deleteByPrimaryKey(String id) {
		return template.delete(getFirstInterface() + ".deleteByPrimaryKey", id);
	}

	/**
	 * 插入记录
	 */
	public int insert(T record) {
		return template.insert(getFirstInterface() + ".insert", record);
	}

	/**
	 * 插入缺省字段的记录，在缺省字段无默认值时报错，插入失败
	 */
	public int insertSelective(T record) {
		return template
				.insert(getFirstInterface() + ".insertSelective", record);
	}

	/**
	 * 按主键查找（单条记录）
	 */
	public T selectByPrimaryKey(String id) {
		return template.selectOne(getFirstInterface() + ".selectByPrimaryKey",
				id);
	}

	/**
	 * 按主键更新（单条记录）
	 */
	public int updateByPrimaryKey(T record) {
		return template.update(getFirstInterface() + ".updateByPrimaryKey",
				record);
	}

	/**
	 * 按主键更新缺省字段的记录，在缺省字段无默认值时报错，更新失败
	 */
	public int updateByPrimaryKeySelective(T record) {
		return template.update(getFirstInterface() + ".updateByPrimaryKeySelective",
				record);
	}

	public List<T> findByMulti(Map<String, Object> params) {
		return template
				.selectList(getFirstInterface() + ".findByMulti", params);
	}

	public List<T> findByMulti0(Map<String, Object> params) {
		return template.selectList(getFirstInterface() + ".findByMulti0",
				params);
	}

	public T findById(String id) {
		return template.selectOne(getFirstInterface() + ".findById", id);
	}

	public String getFirstInterface() {
		// ***************************************************************
		// 接口的名称是按照类定义时的书写顺序
		// ***************************************************************
		// String string = this.getClass().toString();
		return this.getClass().getInterfaces()[0].toString()
				.split(" ")[1].toString();
	}
}
