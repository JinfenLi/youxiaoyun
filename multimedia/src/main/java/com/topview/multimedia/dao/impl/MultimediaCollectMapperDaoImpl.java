/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 上午11:16:57 
 * @version V1.0
 */
package com.topview.multimedia.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaCollect;

/**
 * @ClassName: MultimediaCollectMapperDaoImpl
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 上午11:16:57
 * 
 */
@Repository
public class MultimediaCollectMapperDaoImpl extends
		BaseDaoImpl<MultimediaCollect> implements MultimediaCollectMapper {

	public List<MultimediaCollect> findByUserId(String userid) {

		return template.selectList(getFirstInterface() + ".findByUserId",
				userid);
	}

	public boolean deleteByUseridAndCid(MultimediaCollect collect) {
		return template.delete(getFirstInterface() + ".deleteByUseridAndCid", collect) > 0 ? true : false;
	}

	public int count(String pointid) {
		return template.selectOne(getFirstInterface() + ".count", pointid);
	}

	public List<MultimediaCollect> findByPointId(String pointid) {
		return template.selectList(getFirstInterface() + ".findByPointId",
				pointid);
	}


	public boolean deleteByMultiId(String pointid) {
		return template.delete(getFirstInterface() + ".deleteByMultiId", pointid) > 0 ? true : false;
	}

}
