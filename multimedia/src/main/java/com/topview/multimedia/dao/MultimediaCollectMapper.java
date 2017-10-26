package com.topview.multimedia.dao;

import java.util.List;

import com.topview.multimedia.dao.base.BaseDao;
import com.topview.multimedia.po.MultimediaCollect;

public interface MultimediaCollectMapper extends BaseDao<MultimediaCollect> {

	public List<MultimediaCollect> findByUserId(String userid);

	public boolean deleteByUseridAndCid(MultimediaCollect collect);

	public boolean deleteByMultiId(String pointid);
	
	public int count(String pointid);

	public List<MultimediaCollect> findByPointId(String pointid);

}