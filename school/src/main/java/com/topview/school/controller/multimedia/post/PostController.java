package com.topview.school.controller.multimedia.post;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.message.service.JPushServiceImpl;
import com.topview.message.service.PushMsgService;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.util.NotEmptyString;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.multimedia.service.post.PostService;
import com.topview.multimedia.vo.PostVo;
import com.topview.multimedia.vo.PraiseVo;
import com.topview.multimedia.vo.ReplyVo;
import com.topview.multimedia.vo.result.PostVoResult;
import com.topview.multimedia.vo.result.PraiseVoResult;
import com.topview.multimedia.vo.result.ReplyVoResult;
import com.topview.push.service.PushService;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.clazz.curricula.CurriculaVariableService;
import com.topview.school.service.post.NewPostVoService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.PostPicturePathUtil;
import com.topview.school.util.PushThreadUtil;
import com.topview.school.util.ThumbnailUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.post.NewPostVo;
import com.topview.school.vo.post.result.NewPostVoResult;

/**
 * @Description 家长圈controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月16日 下午10:47:27
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/post", produces = "text/html;charset=UTF-8")
public class PostController {

	@Resource
	private PostService postService;
	
	@Resource
	private PushService pushService;
	
	@Resource
	private ClazzService clazzService;
	
	@Resource
	private NewPostVoService newPostVoService;
	
	@Resource
	private CurriculaVariableService curriculaVariableService;
	
	@Resource
	private PushMsgService pushMsgService;

	/**
	 * 保存主贴
	 * 
	 * @param vo
	 * @param session
	 * @param request
	 * @param files
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePost", method = RequestMethod.POST)
	@ResponseBody
	public String savePost(
			PostVo vo,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files)
			throws IllegalStateException, IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo == null || vo == null) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}

		vo.setId(UUIDUtil.getUUID());
		vo.setStatus(1); // 当前只开发教师端发表帖子功能，不需审核
		if (files != null && files.length > 0) {
			List<String> urls = new ArrayList<String>();
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				FileUploadVo fileUploadVo = FileUploadUtil.uploadFile(file,
						userInfo.getSchool_id() + "/post", request, false);
				if (fileUploadVo != null) {
					urls.add(fileUploadVo.getRelativePath());
				}
			}
			vo.setUrls(urls);
		}
		PostVoResult result = postService.savePost(vo);
		if (result.isSuccess()) {
			resultMap.put("success", true);
			resultMap.put("post", vo);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	
	
	/**
	 * 用于新版本的家长圈，能够将所有上传的家长圈图片压缩为200*200的缩略图。
	 * 
	 * @param vo
	 * @param session
	 * @param request
	 * @param files
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/newSavePost", method = RequestMethod.POST)
	@ResponseBody
	public String newSavePost(
			PostVo vo,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "files", required = false) MultipartFile[] files)
			throws IllegalStateException, IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (userInfo == null || vo == null) {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		vo.setId(UUIDUtil.getUUID());
		vo.setStatus(1); // 当前只开发教师端发表帖子功能，不需审核
		vo.setPublisherId(vo.getPublisherId());
		vo.setPublisherName(vo.getPublisherName());
		vo = PostPicturePathUtil.savePicturePath(vo, files, request, userInfo.getSchool_id(),session);
		List<PostVo> temp = new ArrayList<PostVo>();
		NewPostVo newPostVo = new NewPostVo();
		String[] classIdList = vo.gettMId().split(",");
		
		int count = 0;
		for(int i=0;i<classIdList.length;i++) {
			
			String tMId = classIdList[i];
			vo.settMId(tMId);
			vo.setId(UUIDUtil.getUUID());
			temp.add(vo);
			newPostVo.setVoList(temp);
			NewPostVoResult newResult = newPostVoService.getNewPostVo(newPostVo);
			vo = newResult.getResult().getVoList().get(0);
			PostVoResult result = postService.savePost(vo);
			if(result.isSuccess()) {	
				count++;
			}
		}
		if(count == classIdList.length) {
			sendPost(vo,classIdList);
			resultMap.put("success", true);
			resultMap.put("post", vo);
		} else {
			resultMap.put("success",false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 保存评论
	 * 
	 * @param replyVo
	 *            postId 所属帖子id; replyerId 回复者id; replyTime 回复时间 ; ownerId
	 *            被回复的人的id; replyerName 回复者姓名; ownerName 被回复者姓名; content 评论内容.
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveReply")
	@ResponseBody
	public String saveReply(ReplyVo replyVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		replyVo.setId(UUIDUtil.getUUID());
		replyVo.setReplyTime(df.format(new Date()));
		ReplyVoResult result = postService.saveReply(replyVo);
		if (result.isSuccess()) {
			resultMap.put("success", true);
			resultMap.put("reply", replyVo);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 保存点赞
	 * 
	 * @param praiseVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/savePraise")
	@ResponseBody
	public String savePraise(PraiseVo praiseVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		praiseVo.setId(UUIDUtil.getUUID());
		praiseVo.setPraiserTime(df.format(new Date()));

		PraiseVoResult result = postService.savePraise(praiseVo);
		if (result.isSuccess()) {
			resultMap.put("success", true);
			resultMap.put("praise", praiseVo);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据时间点、帖子状态获取指定班级的帖子
	 * 
	 * @param session
	 * @param clazzId
	 * @param lastUpdate
	 * @param status
	 *            0代表未审核，1代表通过审核可见状态，2代表已删除
	 * @return
	 */
	@RequestMapping("/seePost")
	@ResponseBody
	public String seePost(HttpSession session, String clazzId,
			String lastUpdate, Integer status, String start, String limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if(userInfo==null){
			throw new RuntimeException();
		}
		String praiserId = userInfo.getUser_id();
		if (status == null) {
			status = 1; // status不传默认获取可见状态帖子
		}
		PostVoResult temp = postService.seePostByTMid(session, clazzId, status,
				lastUpdate, start, limit);
		NewPostVo newPostVo = new NewPostVo();
		newPostVo.setVoList(temp.getPosts());
		NewPostVoResult result = newPostVoService.getNewPostVo(newPostVo);
		List<PostVo> posts = result.getResult().getVoList();
		if (status == 2) { // 如果是获取已删除的帖子直接返回已删除帖子的id
			String[] filter = { "context", "createTime", "praiseStatus",
					"praisesCount", "publisherId", "publisherName",
					"repliesCount", "status", "tMId", "title", "type", "urls" };
			resultMap.put("success", true);
			resultMap.put("hadDeletePost", posts);
			return JSONUtil.toObject(resultMap, filter).toString();
		}
		if (posts != null) {
			for (int i = 0; i < posts.size(); i++) {
				if (postService.judgePraise(praiserId, posts.get(i).getId())
						.isSuccess()) { // 判断当前用户是否已经点过赞
					posts.get(i).setPraiseStatus(true);
				}
			}
		}
		resultMap.put("success", temp.isSuccess());
		resultMap.put("posts", posts);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据帖子id和时间点获取评论
	 * 
	 * @param session
	 * @param postId
	 * @param lastUpdate
	 * @return
	 */
	@RequestMapping("/seeReply")
	@ResponseBody
	public String seeReply(HttpSession session, String postId, String lastUpdate) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ReplyVoResult result = postService.seeReplyByPostId(postId, lastUpdate);
		resultMap.put("success", result.isSuccess());
		resultMap.put("replies", result.getResult());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 查看点赞信息
	 * 
	 * @param session
	 * @param postId
	 * @param lastUpdate
	 * @return
	 */
	@RequestMapping("/seePraises")
	@ResponseBody
	public String seePraises(HttpSession session, String postId,
			String lastUpdate) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PraiseVoResult result = postService.seePraiseByPostId(postId,
				lastUpdate);
		resultMap.put("success", result.isSuccess());
		resultMap.put("praises", result.getResult());
		return JSONUtil.toObject(resultMap).toString();
	}
	
	
	/**
	 * 查看点赞和评论信息
	 * 
	 * @param session
	 * @param postId
	 * @param lastUpdate
	 * @return
	 */
	@RequestMapping("/seePraisesAndReplies")
	@ResponseBody
	public String getPraiseAndReply(HttpSession session, String postId, String lastUpdate){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ReplyVoResult replyResult = postService.seeReplyByPostId(postId, lastUpdate);
		PraiseVoResult praiseResult = postService.seePraiseByPostId(postId,
				lastUpdate);
		resultMap.put("replies", replyResult.getResult());
		resultMap.put("praises", praiseResult.getResult());
		resultMap.put("success", replyResult.isSuccess()&&praiseResult.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}
	

	/**
	 * 删除帖子或评论或取消赞
	 * 
	 * @param session
	 * @param postId
	 * @param praiseId
	 * @param replyId
	 * @return
	 */
	@RequestMapping("/deletePost")
	@ResponseBody
	public String deletePost(HttpSession session, String postId,
			String praiseId, String replyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (postId != null && !"".equals(postId)) {
			resultMap.put("success", postService.delete(postId, 1).isSuccess());
		} else if (replyId != null && !"".equals(replyId)) {
			resultMap
					.put("success", postService.delete(replyId, 2).isSuccess());
		} else if (praiseId != null && !"".equals(praiseId)) {
			resultMap.put("success", postService.delete(praiseId, 3)
					.isSuccess());
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
	private boolean sendPost(PostVo vo,String[] classIdList) {
		List<OfflineMessageVo> voList = new ArrayList<OfflineMessageVo>();
		OfflineMessageVo message = null;
		if(classIdList != null && classIdList.length >= 0) {
			for(int i = 0;i<classIdList.length;i++) {
				message = new OfflineMessageVo();
				message.setReceiverId(classIdList[i]);
				message.setContent("您有一条新的帖子，请点击查看");
				message.setType("9");
				message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
				message.setStatue("2");
				message.setSenderId(vo.getPublisherId());
				voList.add(message);
			}
			PushThreadUtil pushThreadUtil = new PushThreadUtil();
			pushThreadUtil.setPushMsgService(pushMsgService);
			pushThreadUtil.setVos(voList);
			Thread thread = new Thread(pushThreadUtil);
			thread.start();
		}
		return true;
	}
}
