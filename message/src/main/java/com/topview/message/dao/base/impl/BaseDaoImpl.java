package com.topview.message.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.topview.message.dao.base.BaseDao;
import com.topview.message.util.NameSpaceUtil;

/**
 * @Description BaseDao实现层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午10:36:17
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name = "message_sqlSessionTemplate")
	protected SqlSessionTemplate template;

	private static final String DELETEBYPRIMARYKEY = "deleteByPrimaryKey";
	private static final String INSERT = "insert";
	private static final String INSERTSELECTIVE = "insertSelective";
	private static final String SELECTBYPRIMARYKEY = "selectByPrimaryKey";
	private static final String UPDATEBYPRIMARYKEYSELECTIVE = "updateByPrimaryKeySelective";
	private static final String UPDATEBYPRIMARYKEY = "updateByPrimaryKey";
	private static final String SELECTALL = "selectAll";

	public int deleteByPrimaryKey(String id) {
		return template.delete(getStatement(DELETEBYPRIMARYKEY), id);
	}

	public int insert(T t) {
		return template.insert(getStatement(INSERT), t);
	}

	public int insertSelective(T t) {
		return template.insert(getStatement(INSERTSELECTIVE), t);
	}

	public T selectByPrimaryKey(String id) {
		return template.selectOne(getStatement(SELECTBYPRIMARYKEY), id);
	}

	public int updateByPrimaryKeySelective(T t) {
		return template.update(getStatement(UPDATEBYPRIMARYKEYSELECTIVE), t);
	}

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
