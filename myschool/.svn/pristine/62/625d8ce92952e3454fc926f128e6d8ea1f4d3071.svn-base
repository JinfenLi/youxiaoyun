package com.topview.school.controller.multimedia.album;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.PhotoInfo;
import com.topview.multimedia.vo.result.AlbumInfoResult;
import com.topview.school.service.clazz.album.ClazzAlbumInfo;
import com.topview.school.service.clazz.album.ClazzAlbumService;
import com.topview.school.service.clazz.album.photo.ClazzPhotoService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.ThumbnailUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;

@Controller
@RequestMapping(value = "/album", produces = "text/html;charset=UTF-8")
public class ClazzAlbumController {

	@Resource
	private ClazzAlbumService clazzAlbumService;
	@Resource
	private ClazzPhotoService clazzPhotoService;
	@Resource
	private AlbumService albumService;
	@Resource
	private PhotoService photoService;

	/**
	 * 创建班级时光相册或校园生活相册
	 * 
	 * @param clazzId
	 * @param name
	 * @return
	 */
	@RequestMapping("/createAlbum")
	@ResponseBody
	public String createAlbum(HttpSession session, String clazzId, String schoolId, AlbumInfo info) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);

		if (clazzId != null && !"".equals(clazzId)) {
			info.settMId(clazzId);
		} else if (schoolId != null && !"".equals(schoolId)) {
			info.settMId(schoolId);
		}
		if (info.getType() == null || "".equals(info.getType())) {
			info.setType("普通"); // 相册分为普通、ViewPager和未审核三类，ViewPager用于存放移动端首页轮播的图片,用户只能创建普通相册
		}
		info.setId(UUIDUtil.getUUID());
		AlbumInfoResult infoResult = clazzAlbumService.createAlbum(info);
		if (infoResult.isSuccess()) {
			resultMap.put("success", true);
			resultMap.put("albumId", info.getId());
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * @dateTime 2016年7月27日下午1:52:10
	 * @author zjd
	 * @description 创建带封面的相册
	 */
	@RequestMapping("/createAlbumWithCover")
	@ResponseBody
	public String createAlbumWithCover(HttpSession session, HttpServletRequest request, String clazzId, String schoolId,
			@RequestParam("file") MultipartFile file, AlbumInfo info) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		resultMap.put("success", false);

		if (clazzId != null && !"".equals(clazzId)) {
			info.settMId(clazzId);
		} else if (schoolId != null && !"".equals(schoolId)) {
			info.settMId(schoolId);
		}
		if (info.getType() == null || "".equals(info.getType())) {
			info.setType("普通"); // 相册分为普通、ViewPager和未审核三类，ViewPager用于存放移动端首页轮播的图片,用户只能创建普通相册
		}

		if (file != null) {
			FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/photo", request, true);
			info.setCoverPath(vo.getRelativePath());
		}
		info.setId(UUIDUtil.getUUID());

		AlbumInfoResult albumInfoResult = albumService.albumSaveCover(info);
		if (albumInfoResult.isSuccess()) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * @dateTime 2016年7月27日下午4:53:56
	 * @author zjd
	 * @description 更新相册封面
	 */
	@RequestMapping("/updateAlbumCover")
	@ResponseBody
	public String updateAlbumCover(HttpSession session, HttpServletRequest request,
			String schoolId,@RequestParam("file") MultipartFile file, String albumId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AlbumInfo albumInfo = new AlbumInfo();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		
		if(file != null) {
			FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/photo", request, true);
			albumInfo.setCoverPath(vo.getRelativePath());
		}
		albumInfo.setId(albumId);
		AlbumInfoResult albumInfoResult = albumService.albumSaveCover(albumInfo);
		
		if (albumInfoResult.isSuccess()) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 修改班级时光的基础信息，如名字，描述等
	 * 
	 * @param albumInfo
	 * @return boolean
	 */
	@RequestMapping("/updateAlbumName")
	@ResponseBody
	public String updateAlbumInfo(AlbumInfo albumInfo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		if (NotEmptyString.isNotNullString(albumInfo.getId()) && NotEmptyString.isNotNullString(albumInfo.getName())) {
			resultMap.put("success", this.albumService.albumUpdate(albumInfo).isSuccess());
		}
		return JSONUtil.toObject(resultMap).toString();
	}


	/**
	 * 删除相册
	 * 
	 * @param albumId
	 * @return
	 */
	@RequestMapping("/deleteAlbum")
	@ResponseBody
	public String deleteAlbum(String albumId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] ids = albumId.split(",");
		AlbumInfoResult result = null;
		for(String id: ids){
			 result = clazzAlbumService.deleteAlbum(id);
		}
		
		resultMap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * @dateTime 2016年7月28日下午9:46:55
	 * @author zjd
	 * @description 删除多个相册
	 */
	@RequestMapping("/deleteAlbums")
	@ResponseBody
	public String deleteAlbumS(String albumIds) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AlbumInfoResult result = clazzAlbumService.deleteAlbums(albumIds);
		resultMap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 移动端获取班级时光相册
	 * 
	 * @param session
	 * @param pager
	 * @return
	 */
	@RequestMapping("/getAlbum")
	@ResponseBody
	public String getAlbum(HttpSession session, Pager pager, String clazzId, HttpServletRequest request) {
		if (clazzId == null || "".equals(clazzId)) {
			UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
			clazzId = userInfo.getClass_id();
		}
		return getAlbumById(pager, clazzId, request);
	}

	/**
	 * 移动端获取校园生活相册
	 * 
	 * @param schoolId
	 * @param pager
	 * @return
	 */
	@RequestMapping("/getAlbumBySchoolId")
	@ResponseBody
	public String getAlbumBySchoolId(String schoolId, Pager pager, HttpServletRequest request) {
		if (schoolId == null) { // TODO
			schoolId = "1";
		}
		// TODO 应急：此处因为查到的相册要移除掉轮播相册所以会比预定的少一个相册
		pager.setPageSize(pager.getPageSize() + 1);
		return getAlbumById(pager, schoolId, request);
	}

	/**
	 * 移动端根据班级id或学校id分页获取所属相册信息
	 * 
	 * @param pager
	 * @param id
	 * @return
	 */
	private String getAlbumById(Pager pager, String id, HttpServletRequest request) {
		String[] filter = { "code", "description", "tMId", "type", "comment", "label", "status", "zoneId", "pager",
				"collectid", "demoid" };
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		AlbumInfoResult albumInfoResult = clazzAlbumService.getAlbum(id, pager);
		List<AlbumInfo> albumInfos = albumInfoResult.getInfoResult();
		if (albumInfos == null || albumInfos.size() <= 0) {
			resultMap.put("msg", "还没有任何相册");
			return JSONUtil.toObject(resultMap).toString();
		}
		// 移除掉属于首页轮播的相册和未审核相册
		Iterator<AlbumInfo> iterator = albumInfos.iterator();
		while (iterator.hasNext()) {
			String type = iterator.next().getType();
			if ("ViewPager".equals(type) || "未审核".equals(type)) {
				iterator.remove();
			}
		}
		List<ClazzAlbumInfo> clazzAlbumInfos = new ArrayList<ClazzAlbumInfo>();
		ClazzAlbumInfo clazzAlbumInfo = null;
		for (AlbumInfo info : albumInfos) {
			clazzAlbumInfo = new ClazzAlbumInfo();
			clazzAlbumInfo.setAlbumInfo(info);
			List<PhotoInfo> photoInfos = clazzPhotoService.getAllPhoto(info.getId(), new Pager()).getInfoResult(); // TODO此处不应该传new
																													// Pager()或pager
			if (photoInfos.size() > 0 && photoInfos != null) {
				clazzAlbumInfo.setPhotoInfo(photoInfos.get(0));
				transToThumbnail(photoInfos.get(0).getVideoPath(), request); // 生成相册封面缩略图
			}
			clazzAlbumInfos.add(clazzAlbumInfo);
		}
		if (clazzAlbumInfos.size() > 0) {
			resultMap.put("success", true);
		} else {
			resultMap.put("success", true);
			resultMap.put("msg", "还没有任何相册");
		}
		resultMap.put("clazzAlbumInfos", clazzAlbumInfos);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 教师端获取未审核相册和未审核图片，家长端仅获取未审核相册，web端共用
	 * 
	 * @param clazzId
	 * @param session
	 * @return
	 */
	@RequestMapping("getNoPassAlbum")
	@ResponseBody
	public String getNopassAlbum(String clazzId, HttpSession session, Pager pager, Integer limit, Integer start) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] filter = { "comment", "createTime", "description", "pager", "tMId", "type", "collectid", "demoid",
				"label", "zoneId" };
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		if (clazzId == null || "".equals(clazzId)) {
			resultMap.put("success", false);
			resultMap.put("msg", "很抱歉您没有相应权限查看");
			return JSONUtil.toObject(resultMap).toString();
		}

		AlbumInfo info = new AlbumInfo();
		info.settMId(clazzId);
		info.setType("未审核");
		List<AlbumInfo> albums = albumService.albumFindByType(info).getInfoResult(); // 查询未审核相册，一定存在
		resultMap.put("album", albums.get(0));
		resultMap.put("success", true);
		if (userInfo.getUser_type().ordinal() == 1) { // 如果是教师返回相册和未审核的照片
			if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
				pager.setPageSize(limit);
				pager.setPageNumber(start / limit + 1);
			}
			PhotoInfo photo = new PhotoInfo();
			photo.settMId(albums.get(0).getId());
			photo.setPager(pager);
			resultMap.put("photos", photoService.photoFindAll(photo).getInfoResult());
			resultMap.put("totalCount", photoService.selectCount(albums.get(0).getId()));
		}
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 获取班级时光用于上传的默认相册
	 * 
	 * @param session
	 * @param tmid
	 * @param name
	 * @return
	 */
	@Deprecated
	@RequestMapping("/getAlbumByName")
	@ResponseBody
	public String getAlbumByName(HttpSession session, String tmid, String name) {

		AlbumInfoResult result = clazzAlbumService.getAlbumByName(tmid, name);
		int code = result.getCode();
		if (code == 0) {
			AlbumInfo info = new AlbumInfo();
			info.setName(name);
			return createAlbum(session, tmid, null, info); // 班级默认相册上传的相册
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("albumId", result.getInfoResult().get(0).getId());
		return JSONUtil.toObject(resultMap).toString();
	}

	// 由原图的相对路径找到 生成缩略图
	private void transToThumbnail(String relativePath, HttpServletRequest request) {

		relativePath = relativePath.substring(8); // 截掉数据库中相对路径前的”/school/“
		String realPath = request.getSession().getServletContext().getRealPath("/");
		realPath = realPath + relativePath; // 拼成原图的绝对路径
		File file = new File(realPath);
		if (!file.exists()) {
			return;
		}

		int index = realPath.lastIndexOf("/");
		String prefix = realPath.substring(0, index); // 截取到文件夹
		String fileName = realPath.substring(index); // 截取文件名
		String thumbnailPath = prefix + "\\thumb" + fileName; // 缩略图的绝对路径

		File thumbnail = new File(thumbnailPath);
		// 如果缩略图不存在则生成
		if (!thumbnail.exists()) {
			ThumbnailUtil.thumbnailImage(file.getAbsolutePath());
		}
	}

	/**
	 * web端不分页获取相册
	 * 
	 * @param session
	 * @param schoolId
	 *            查询校园相册时使用
	 * @param clazzId
	 *            查询班级时光相册时使用
	 * @param type
	 *            等于1时查询普通相册, 等于0时查询轮播相册
	 * @return
	 */
	@RequestMapping("/getAlbumNoPager")
	@ResponseBody
	public String getAlbumNoPager(HttpSession session, String schoolId, String clazzId, Integer type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String zoneId;
		if (schoolId != null && !"".equals(schoolId)) {
			zoneId = schoolId;
		} else if (clazzId != null && !"".equals(clazzId)) {
			zoneId = clazzId;
		} else {
			resultMap.put("success", false);
			return JSONUtil.toObject(resultMap).toString();
		}
		String[] filter = { "description", "tMId", "type", "comment", "label", "status", "zoneId", "createTime",
				"pager" };
		resultMap.put("success", true);
		clazzAlbumService.getClass();
		List<AlbumInfo> albumInfos = clazzAlbumService.getAlbumNoPager(zoneId, type).getInfoResult();
		resultMap.put("albums", albumInfos);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * web端分页获取全部相册，包括轮播相册和普通相册
	 * 
	 * @param start
	 * @param limit
	 * @param clazzId
	 * @param schoolId
	 * @param type
	 * @return
	 */
	@RequestMapping("/getAlbumWithPager")
	@ResponseBody
	public String getAlbumWithPager(Integer start, Integer limit, String clazzId, String schoolId, Integer type,
			Pager pager) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		String[] filter = { "code", "description", "tMId", "comment", "label", "status", "zoneId", "pager", "collectid",
				"demoid" };
		if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		if (clazzId != null && !"".equals(clazzId)) {
			schoolId = clazzId;
		}

		AlbumInfoResult albumInfoResult = clazzAlbumService.getAlbum(schoolId, pager);
		List<AlbumInfo> albumInfos = albumInfoResult.getInfoResult();
		if (albumInfos.size() <= 0) {
			resultMap.put("msg", "还没有任何相册");
			return JSONUtil.toObject(resultMap).toString();
		}
		List<ClazzAlbumInfo> clazzAlbumInfos = new ArrayList<ClazzAlbumInfo>();
		ClazzAlbumInfo clazzAlbumInfo = null;
		for (AlbumInfo info : albumInfos) {
			clazzAlbumInfo = new ClazzAlbumInfo();
			clazzAlbumInfo.setAlbumInfo(info);
			List<PhotoInfo> photoInfos = clazzPhotoService.getAllPhoto(info.getId(), pager).getInfoResult();
			if (photoInfos.size() > 0 && photoInfos != null) {
				clazzAlbumInfo.setPhotoInfo(photoInfos.get(0));
			}
			clazzAlbumInfos.add(clazzAlbumInfo);
		}
		if (clazzAlbumInfos.size() > 0) {
			resultMap.put("success", true);
		} else {
			resultMap.put("msg", "还没有任何相册");
		}
		resultMap.put("clazzAlbumInfos", clazzAlbumInfos);
		return JSONUtil.toObject(resultMap, filter).toString();
	}
}
