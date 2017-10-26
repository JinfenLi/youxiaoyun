package com.topview.school.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.util.NameSpaceUtil;

/**
 * 
 * 项目名称：school <br>
 * 类名称：BaseDaoImpl <br>
 * 类描述： <br>
 * 创建人：luozhangjie <br>
 * 创建时间：2015年3月26日 上午11:35:26 <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 上午11:35:26 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 <br>
 *
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name = "sqlSessionTemplate")
	protected SqlSessionTemplate template;

	private static final String DELETEBYPRIMARYKEY = "deleteByPrimaryKey";
	private static final String INSERT = "insert";
	private static final String INSERTSELECTIVE = "insertSelective";
	private static final String SELECTBYPRIMARYKEY = "selectByPrimaryKey";
	private static final String UPDATEBYPRIMARYKEYSELECTIVE = "updateByPrimaryKeySelective";
	private static final String UPDATEBYPRIMARYKEY = "updateByPrimaryKey";
	private static final String SELECTALL = "selectAll";

	@Override
	public int deleteByPrimaryKey(String id) {
		return template.delete(getStatement(DELETEBYPRIMARYKEY), id);
	}

	@Override
	public int insert(T t) {
		return template.insert(getStatement(INSERT), t);
	}

	@Override
	public int insertSelective(T t) {
		return template.insert(getStatement(INSERTSELECTIVE), t);
	}

	@Override
	public T selectByPrimaryKey(String id) {
		return template.selectOne(getStatement(SELECTBYPRIMARYKEY), id);
	}

	@Override
	public List<T> selectByParameters(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return template.selectList(getStatement("selectByParameters"), map);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) {
		return template.update(getStatement(UPDATEBYPRIMARYKEYSELECTIVE), t);
	}

	@Override
	public int updateByPrimaryKey(T t) {
		return template.update(getStatement(UPDATEBYPRIMARYKEY), t);
	}

	public List<T> selectAll(int offset, int limit) {
		return template.selectList(getStatement(SELECTALL), null,
				new RowBounds(offset, limit));
	}

	protected String getStatement(String xmlId) {
		
		return getNameSpace() + "." + xmlId;
	}

	protected String getNameSpace() {
		return NameSpaceUtil.getNameSpace(getEntityClass().getName());
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		
		return (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
}
