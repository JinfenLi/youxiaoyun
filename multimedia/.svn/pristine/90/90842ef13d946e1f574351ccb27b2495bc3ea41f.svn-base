package com.topview.multimedia.service.record;

import com.topview.multimedia.vo.result.RecordUpdateResult;


/**
 * @Description 多媒体记录更新service
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年11月11日 上午10:32:12
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface RecordUpdateService {

	/**
	 * 保存模块最近更新时间
	 * @param tMId 学校/年级/班级id
	 * @param module 模块类型
	 * @return
	 */
	public RecordUpdateResult saveOrUpdateRecord(String tMId, String module);
	
	/**
	 * 根据学校/年级/班级id查询各个模块的最新更新时间
	 * @param tMId
	 * @return
	 */
	public RecordUpdateResult findRecordUpdate(String tMId);
}
