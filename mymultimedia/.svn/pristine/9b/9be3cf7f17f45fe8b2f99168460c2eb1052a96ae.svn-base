package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaFolder;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午12:54:19 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Repository
public class MultimediaFolderMapperImpl extends BaseDaoImpl<MultimediaFolder> implements
		MultimediaFolderMapper {

	@Override
	public int selectCount(String tmId) {
		return template.selectOne(getFirstInterface() + ".selectCount", tmId);
	}


}
