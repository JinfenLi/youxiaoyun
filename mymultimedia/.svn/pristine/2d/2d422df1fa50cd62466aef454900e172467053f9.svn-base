package com.topview.multimedia.dao.impl;

import java.util.List;

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

	public int selectCount(String folderId) {
		return template.selectOne(getFirstInterface() + ".selectCount", folderId);
	}

	@Override
	public List<MultimediaFile> findIn(List<String> ids,int offset, int limit) {
		
		return template.selectList(getFirstInterface() + ".findIn", ids,new RowBounds(offset, limit));
	}

}
