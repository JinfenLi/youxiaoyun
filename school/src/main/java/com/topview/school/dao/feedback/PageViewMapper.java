package com.topview.school.dao.feedback;

import com.topview.school.dao.base.BaseDao;
import com.topview.school.po.PageView;

/**
 * @Description 页面访问量Dao
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年10月4日 下午2:27:48
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface PageViewMapper extends BaseDao<PageView> {

	/**
	 * 查询给定时间段内指定url的独立ip访问次数（相同ip只记1次）
	 * @param beginTime
	 * @param endTime
	 * @param url
	 * @param schoolId
	 * @return
	 */
	public int selectIpCount(String beginTime, String endTime, String url,String schoolId);
	
	/**
	 * 查询给定时间段内指定url的访问量（相同ip可记多次）
	 * @param beginTime
	 * @param endTime
	 * @param url
	 * @param schoolId
	 * @return
	 */
	public int selectVisitCount(String beginTime, String endTime, String url,String schoolId);
}