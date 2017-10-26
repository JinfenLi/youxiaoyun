package com.topview.school.controller.multimedia.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.library.LibraryService;
import com.topview.multimedia.service.library.video.VideoService;
import com.topview.multimedia.vo.LibraryInfo;
import com.topview.multimedia.vo.VideoInfo;
import com.topview.multimedia.vo.result.LibraryInfoResult;
import com.topview.multimedia.vo.result.VideoInfoResult;
import com.topview.school.po.ValidationResult;
import com.topview.school.service.clazz.library.ClazzLibraryService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.util.UUIDUtil;
import com.topview.school.util.ValidationUtil;

/**
 * @Description 视频库Controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月17日 下午8:29:26
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/library", produces = "text/html;charset=UTF-8")
public class LibraryController {

	@Autowired
	private ClazzLibraryService clazzLibraryService;
	@Resource
	private LibraryService libraryService;
	@Autowired
	private VideoService videoService;

	/**
	 * 班级时光、校园生活分页获取视频库接口
	 * 
	 * @param session
	 * @param pager
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLibrary")
	public String getLibrary(HttpSession session, Pager pager, String schoolId,
			String clazzId, Integer limit, Integer start) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		LibraryInfoResult result = new LibraryInfoResult();
		if (start != null && !"".equals(start) && limit != null
				&& !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		if (schoolId != null && !"".equals(schoolId)) {
			result = clazzLibraryService.getLibrary(schoolId, pager);
			resultMap.put("totalCount", libraryService.selectCount(schoolId));
		} else if (clazzId != null && !"".equals(clazzId)) {
			result = clazzLibraryService.getLibrary(clazzId, pager);
			resultMap.put("totalCount", libraryService.selectCount(clazzId));
		} else {
			return JSONUtil.toObject(resultMap).toString();
		}
		List<LibraryInfo> infos = result.getResult();
		result.setResult(infos);
		if (result.isSuccess()) {
			resultMap.put("librarys", result.getResult());
			resultMap.put("success", true);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 班级时光、校园生活不分页获取视频库接口
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLibraryNoPager")
	public String getLibraryNoPager(HttpSession session, String schoolId,
			String clazzId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] filter = { "comment", "createTime", "description2", "pager",
				"photoCount2", "tMId", "type" };
		if (clazzId != null && !"".equals(clazzId)) {
			schoolId = clazzId;
		}
		LibraryInfoResult result = clazzLibraryService
				.getLibraryNoPager(schoolId);
		List<LibraryInfo> infos = result.getResult();
		resultMap.put("success", result.isSuccess());
		resultMap.put("infos", infos);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 创建视频库
	 * 
	 * @param session
	 * @param info
	 * @param name
	 * @param schoolId
	 * @param clazzId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createLibrary")
	public String createLibrary(HttpSession session, LibraryInfo info,
			String schoolId, String clazzId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(info);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		info.setId(UUIDUtil.getUUID());
		if (schoolId != null && !"".equals(schoolId)) {
			info.settMId(schoolId);
		} else if (clazzId != null && !"".equals(clazzId)) {
			info.settMId(clazzId);
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		info.setType(0);
		if(clazzLibraryService.createLibrary(info)
				.isSuccess()) {
		resultMap.put("success", true);
		resultMap.put("libraryId", info.getId());
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改视频库信息
	 * 
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateLibrary")
	public String updateLibrary(LibraryInfo info) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(info);
		if(res.isHasErrors()) {
			resultMap.put("success", false);
			resultMap.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(resultMap).toString();
		}
		resultMap
				.put("success", libraryService.libraryUpdate(info).isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除视频库
	 * 
	 * @param libraryId
	 * @return
	 */
	@RequestMapping("/deleteLibrary")
	@ResponseBody
	public String deleteLibrary(String libraryId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		LibraryInfo info = new LibraryInfo();
		info.setId(libraryId);
		resultMap
				.put("success", libraryService.libraryDelete(info).isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * @dateTime 2016年8月4日下午2:05:39
	 * @author zjd
	 * @description 删除多个视频库
	 */
	@RequestMapping("/deleteLibrarys")
	@ResponseBody
	public String deleteLibrarys(String libraryIds) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] libraryIds_ = libraryIds.split(",");
		int total = 0;
		for(String libraryId: libraryIds_) {
			LibraryInfo info = new LibraryInfo();
			info.setId(libraryId);
			
			VideoInfo videoInfo = new VideoInfo();
			videoInfo.settMId(libraryId);
			
			
			VideoInfoResult videoInfoResult = videoService.videoFindNoPager(videoInfo);
			if(NotEmptyString.isNotNull(videoInfoResult)) {
				if(NotEmptyString.isNotNull(videoInfoResult.getResult())) {
					for(VideoInfo video: videoInfoResult.getResult()) {
						videoService.videoDelete(video);
					}
				}
			}
			
			libraryService.libraryDelete(info);
			total++;
		}
		if(total == libraryIds_.length) {
			resultMap.put("success", true);
		}else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	
}
