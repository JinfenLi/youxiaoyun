package com.topview.message.dao;

import java.util.List;

import com.topview.message.dao.base.BaseDao;
import com.topview.message.po.Message;
import com.topview.message.vo.OfflineMessageVo;

/**
 * @Description Message（消息）基础dao接口
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月9日 上午11:28:41
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public interface MessageMapper extends BaseDao<Message>{
	/**
	 * 查询未发送离线消息
	 * @param receiverId
	 * @return
	 */
	List<OfflineMessageVo> getOfflineMessages(String receiverId);
	
	/**
	 * 查询消息历史消息
	 * @param senderId
	 * @param beginTime
	 * @param endTime
	 * @return 分页
	 */
	List<Message> getHistoryMessage(String senderId, String beginTime, String endTime, int limit, int offset);
	
	/**
	 * @dateTime 2016年7月15日下午9:08:10
	 * @author zjd
	 * @description 获取消息总条数
	 */
	int getHisoryMessageCounut(String senderId, String beginTime, String endTime);
	
	
	/**
	 * 根据消息的id来查询消息
	 * @param messageId		消息的id
	 * @return
	 */
	OfflineMessageVo getMessageById(String messageId);
}