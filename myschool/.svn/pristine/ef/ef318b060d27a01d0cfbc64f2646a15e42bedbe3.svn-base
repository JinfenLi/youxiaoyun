/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年7月27日 下午9:37:49 
 * @version V1.0
 */
package com.topview.school.controller.multimedia.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.service.collect.CollectService;
import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.CollectInfo;
import com.topview.multimedia.vo.result.CollectInfoResult;
import com.topview.school.service.collect.PersonCollectService;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * @ClassName: CollectController
 * @Description: TODO
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年7月27日 下午9:37:49
 * 
 */
@Controller
@RequestMapping(value = "/collect", produces = "text/html;charset=UTF-8")
public class CollectController {

	@Autowired
	private CollectService collectService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private PersonCollectService personCollectService;

	@RequestMapping("/collectsave")
	@ResponseBody
	public String collectSave(HttpSession session, String pointId, String type) {
		CollectInfo info = new CollectInfo();
		Map<String, Object> resultmap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		info.setPointid(pointId);
		info.setPointtype(type);
		info.setUserid(userInfo.getUser_id());
		CollectInfoResult result = new CollectInfoResult();
		result = collectService.collectSave(info);
		resultmap.put("collectid", result.getCollectid());
		resultmap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultmap).toString();
	}

	@RequestMapping("/getAllBypage")
	@ResponseBody
	public String getAllBypage(HttpSession session, Pager pager) {
		UserInfo userinfo = (UserInfo) session.getAttribute("currentUser");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userid = userinfo.getUser_id();
		Map<String, List<Object>> resultmap = new HashMap<String, List<Object>>();
		resultmap = personCollectService.collectFindAll(userid, pager);
		resultMap.put("success", true);
		resultMap.put("resultmap", resultmap);
		return JSONUtil.toObject(resultMap).toString();
	}

}
