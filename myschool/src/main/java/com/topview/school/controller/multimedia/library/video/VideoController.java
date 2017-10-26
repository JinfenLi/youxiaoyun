package com.topview.school.controller.multimedia.library.video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.VideoInfoResult;
import com.topview.school.service.clazz.library.video.ClazzVideoService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;

@Controller
@RequestMapping(value = "/video", produces = "text/html;charset=UTF-8")
public class VideoController {

	@Autowired
	private ClazzVideoService clazzVideoService;
	@Resource
	private VideoService videoService;

	private String[] filter = { "collectid", "pager", "comment", "format", "description", "label", "size", "zoneId"};

	/**
	 * 查询未审核视频
	 * @param libraryId
	 * @param pager
	 * @return
	 */
	@RequestMapping("/getNotPassVideo")
	@ResponseBody
	public String getNotPassVideo(String libraryId, Pager pager) {
		return getAllVideo(libraryId, pager, 0);
	}

	/**
	 * 分页获取已审核视频
	 * @param libraryId
	 * @param pager
	 * @return
	 */
	@RequestMapping("/getPassVideo")
	@ResponseBody
	public String getPassVideo(String libraryId, Pager pager, Integer limit, Integer start) {
		if (start != null && !"".equals(start) && limit != null
				&& !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		return getAllVideo(libraryId, pager, 1);
	}

	@ResponseBody
	@RequestMapping("/getVideo")
	public String getVideo(String videoId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("video", clazzVideoService.getVideo(videoId).getResult().get(0));
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 上传视频
	 * 
	 * @param session
	 * @param request
	 * @param files
	 * @param info
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadVideo")
	@ResponseBody
	public String uploadPhoto(HttpSession session, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, VideoInfo videoInfo,
			String schoolId, String clazzId) throws IllegalStateException,
			IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		videoInfo.setZoneId(userInfo.getSchool_id()); //用于后边记录模块更新时间 TODO
		
		FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/video", request, true);

		videoInfo.setVideoPath(vo.getRelativePath());
		if (userInfo.getUser_type().ordinal() == 1) { // 教师不需要审核
			videoInfo.setStatus(1);
		} else {
			videoInfo.setStatus(0);
		}
		resultMap.put("success", clazzVideoService.saveVideo(videoInfo)
				.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除视频
	 * 
	 * @param videoId 视频id
	 * @param tMId 视频库id
	 * @return
	 */
	@RequestMapping("/deleteVideo")
	@ResponseBody
	public String deleteVideo(String videoId, String tMId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		VideoInfo info = new VideoInfo();
		info.setId(videoId);
		info.settMId(tMId);
		resultMap.put("success", videoService.videoDelete(info).isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}
	
	private String getAllVideo(String libraryId, Pager pager, int status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		VideoInfoResult result = clazzVideoService
				.getAllVideo(libraryId, pager);
		List<VideoInfo> list = result.getResult();
		result.setResult(judgeStatus(status, list));
		resultMap.put("videos", list);
		resultMap.put("success", true);
		resultMap.put("totalCount", videoService.selectCount(libraryId));
		return JSONUtil.toObject(resultMap, filter).toString();
	}
	
	private List<VideoInfo> judgeStatus(int status, List<VideoInfo> list) {
		List<VideoInfo> remove = new ArrayList<VideoInfo>();
		for (VideoInfo info : list) {
			if (info.getStatus() != status) {
				remove.add(info);
			}
		}
		list.removeAll(remove);
		return list;
	}

}
