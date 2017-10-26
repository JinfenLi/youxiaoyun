package com.topview.multimedia.dao.impl;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.StudentGroupMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.StudentGroup;

/** * @author  Rachel 
@date 创建时间：2016年9月25日 下午1:55:09 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Repository
public class StudentGroupMapperImpl extends BaseDaoImpl<StudentGroup> implements
		StudentGroupMapper {

	@Override
	public int deleteByPo(StudentGroup sg) {
		return template.delete(getFirstInterface() + ".deleteByPo", sg);
	}

}
