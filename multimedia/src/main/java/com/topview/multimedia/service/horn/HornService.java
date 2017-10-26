package com.topview.multimedia.service.horn;

import com.topview.multimedia.vo.HornInfo;
import com.topview.multimedia.vo.result.HornInfoResult;

public interface HornService {
	/**
	 * @dateTime 2016年10月14日上午11:05:44
	 * @author zjd
	 * @description 推送小喇叭
	 */
	public HornInfoResult hornPushProcess(HornInfo hornInfo);
	
	/**
	 * @dateTime 2016年10月14日上午11:06:19
	 * @author zjd
	 * @description 获取小喇叭
	 */
	public HornInfoResult hornGetProcess(HornInfo hornInfo);
	
	/**
	 * @dateTime 2016年11月28日下午7:01:55
	 * @author zjd
	 * @description 根据用户id获取小喇叭历史推送
	 */
	public HornInfoResult hornGetHistoryProcess(HornInfo hornInfo);

	/**
	 * @dateTime 2016年11月28日下午7:26:37
	 * @author zjd
	 * @description 删除小喇叭
	 */
	public boolean deleteHornById(String id);
}
