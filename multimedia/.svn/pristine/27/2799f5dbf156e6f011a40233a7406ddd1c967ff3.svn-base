package com.topview.multimedia.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaFile;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午12:50:55 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */

@Repository
public class MultimediaFileMapperImpl extends BaseDaoImpl<MultimediaFile> implements
		MultimediaFileMapper {
	public boolean deleteByTmid(String tMId) {
		return template.delete(getFirstInterface() + ".deleteByTmid", tMId) > 0 ? true : false;
	}

	public int selectCount(String clazzId) {
		return template.selectOne(getFirstInterface() + ".selectCount", clazzId);
	}

	
	public List<MultimediaFile> findIn(List<String> ids,int offset, int limit) {
		
		return template.selectList(getFirstInterface() + ".findIn", ids,new RowBounds(offset, limit));
	}


	@Override
	public List<MultimediaFile> findByName(String name) {
		
		return template.selectList(getFirstInterface() + ".findLike", name);
	}

	@Override
	public int findByLatest(Map<String, Object> map) {
		return template.selectOne(getFirstInterface() + ".selectByLatest", map);
	}


}
