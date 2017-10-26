package com.topview.school.service.feedback;

import com.topview.school.vo.feedback.FeedbackInfo;

/**
 * @Description 反馈service层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 上午11:19:16
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public interface FeedbackService {

	public boolean insertFeedback(FeedbackInfo info);
	
}
