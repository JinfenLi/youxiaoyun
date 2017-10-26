package com.topview.school.controller.feedback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.service.feedback.FeedbackService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.feedback.FeedbackInfo;

/**
 * @Description 反馈controller层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月20日 上午11:28:27
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "feedback", produces = "text/html;charset=UTF-8")
public class FeedbackController {

	@Resource
	private FeedbackService feedbackService;

	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	@ResponseBody
	public String saveFeedback(FeedbackInfo info, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (info.getFiles() != null && info.getFiles().length > 0) {
			List<FileUploadVo> vos = FileUploadUtil.uploadFile(info.getFiles(),
					"feedback", request, true);
			List<String> urls = new ArrayList<String>();
			for (FileUploadVo vo : vos) {
				urls.add(vo.getRelativePath());
			}
			info.setUrls(urls);
		}
		resultMap.put("success", feedbackService.insertFeedback(info));
		return JSONUtil.toObject(resultMap).toString();
	}
}
