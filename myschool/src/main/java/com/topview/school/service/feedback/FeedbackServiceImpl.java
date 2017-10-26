package com.topview.school.service.feedback;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.feedback.FeedbackAccessoryMapper;
import com.topview.school.dao.feedback.FeedbackMapper;
import com.topview.school.po.Feedback;
import com.topview.school.po.FeedbackAccessory;
import com.topview.school.vo.feedback.FeedbackInfo;

/**
 * @Description 反馈service实现层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 上午11:19:44
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Resource
	private FeedbackMapper feedbackMapper;
	@Resource
	private FeedbackAccessoryMapper feedbackAccessoryMapper;

	@Override
	public boolean insertFeedback(FeedbackInfo info) {
		Feedback f = new Feedback();
		f.setContent(info.getContent());
		f.setEmail(info.getEmail());
		f.setPhone(info.getPhone());
		f.setStatus(false);
		feedbackMapper.insert(f);
		if (info.getUrls() != null) {
			if (info.getUrls().size() > 0) {
				for (int i = 0; i < info.getUrls().size(); i++) {
					FeedbackAccessory fa = new FeedbackAccessory();
					fa.setFeedbackId(f.getId());
					fa.setUrl(info.getUrls().get(i));
					feedbackAccessoryMapper.insert(fa);
				}
			}
		}
		return f.getId() > 0 ? true : false;
	}

}
