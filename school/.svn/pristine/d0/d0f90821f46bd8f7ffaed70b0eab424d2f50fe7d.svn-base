package com.topview.school.controller.multimedia.album.photo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.topview.message.service.JPushServiceImpl;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.album.AlbumService;
import com.topview.multimedia.service.album.photo.PhotoService;
import com.topview.multimedia.util.DateFormatUtil;
import com.topview.multimedia.vo.AlbumInfo;
import com.topview.multimedia.vo.PhotoInfo;
import com.topview.multimedia.vo.result.PhotoInfoResult;
import com.topview.school.service.clazz.ClazzService;
import com.topview.school.service.clazz.album.ClazzAlbumService;
import com.topview.school.service.clazz.album.photo.ClazzPhotoService;
import com.topview.school.service.school.SchoolService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;

@Controller
@RequestMapping(value = "/photo", produces = "text/html;charset=UTF-8")
public class PhotoController {

	@Autowired
	private ClazzPhotoService clazzPhotoService;
	@Autowired
	private ClazzAlbumService clazzAlbumService;
	@Resource
	private PhotoService photoService;
	@Resource
	private AlbumService albumService;
	@Resource
	private SchoolService schoolService;
	@Resource
	private ClazzService clazzService;
 
	private String[] filter = { "code", "comment", "label", "status", "tMId", "zoneId", "pager",
			"collectid" };
	
	/**
	 * TODO 照片审核,相册不提供审核
	 */

	/**
	 * web端校园模块不分页查询未审核图片
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getNotPassPhoto")
	@ResponseBody
	public String getNotPassPhoto(HttpSession session) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String schoolId = userInfo.getSchool_id();
		List<AlbumInfo> albumInfos = clazzAlbumService.getAlbumNoPager(schoolId, 1).getInfoResult();
		if (albumInfos == null || albumInfos.size() == 0) {
			return null;
		}
		List<PhotoInfo> list = new ArrayList<PhotoInfo>();
		List<PhotoInfo> l = null;
		List<PhotoInfo> remove = new ArrayList<PhotoInfo>();
		for (AlbumInfo info : albumInfos) {
			l = clazzPhotoService.getPhotoNoPager(info.getId()).getInfoResult();
			if (l != null && l.size() != 0) {
				for (PhotoInfo i : l) {
					if (i.getStatus() == 1) {
						remove.add(i);
					}
				}
			}
			list.addAll(l);
		}
		list.removeAll(remove);
		resultMap.put("photos", list);
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 获取相册中已通过审核的图片
	 * 
	 * @param albumId
	 * @param pager
	 * @return
	 */
	@RequestMapping("/getPassPhoto")
	@ResponseBody
	public String getPassPhoto(String albumId, Pager pager, Integer limit, Integer start) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		resultMap.put("photos", getAllPhoto(albumId, pager, 1).getInfoResult());
		resultMap.put("success", true);
		resultMap.put("totalCount", photoService.selectCount(albumId));
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	private PhotoInfoResult getAllPhoto(String albumId, Pager pager, int status) {
		PhotoInfoResult result = clazzPhotoService.getAllPhoto(albumId, pager);
		List<PhotoInfo> list = result.getInfoResult();
		result.setInfoResult(judgeStatus(status, list));
		return result;
	}

	@ResponseBody
	@RequestMapping("/getPhoto")
	public String getPhoto(String photoId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("photos", clazzPhotoService.getPhoto(photoId));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 获取轮播图片
	 * 
	 * @param schoolId
	 * @param session
	 * @param pager
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getViewPager")
	public String getViewPager(String schoolId, HttpSession session, Pager pager, Integer limit, Integer start) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		List<AlbumInfo> album = clazzAlbumService.getAlbumByType(schoolId, "ViewPager").getInfoResult(); // 获取轮播相册
		if (album.size() > 0) {
			AlbumInfo info = album.get(0);
			PhotoInfoResult photoInfoResult = clazzPhotoService.getAllPhoto(info.getId(), pager);
			resultMap.put("photos", photoInfoResult.getInfoResult());
			resultMap.put("totalCount", photoService.selectCount(info.getId()));
		} else {
			resultMap.put("msg", "不存在轮播相册请及时创建");
		}
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 修改相册名称等
	 * 
	 * @param photoInfo
	 * @return
	 */
	@RequestMapping("/updatePhotoName")
	@ResponseBody
	public String udpatePhotoInfo(PhotoInfo photoInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		if (NotEmptyString.isNotNullString(photoInfo.getId()) && NotEmptyString.isNotNullString(photoInfo.getName())) {
			resultMap.put("success", this.photoService.photoUpdateName(photoInfo).isSuccess());
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * @dateTime 2016年7月29日下午5:45:34
	 * @author zjdf
	 * @description 
	 */
	@RequestMapping("/updatePhotoSortAndDescription")
	@ResponseBody
	public String updatePhotoSort(String id, String sort, String description, String name) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		if (NotEmptyString.isNotEmpty(new String[]{id, sort}) && sort.matches("^[1-9]\\d*$")) {
			PhotoInfo photoInfo = new PhotoInfo();
			photoInfo.setId(id);
			photoInfo.setSort(Integer.parseInt(sort));
			photoInfo.setDescription(description);	
			photoInfo.setName(name);
			this.photoService.photoUpdateSortAndDescription(photoInfo);
			resultMap.put("success", true);
		}
		return JSONUtil.toObject(resultMap).toString();
	}
	

	/**
	 * 把审核过后的照片转移
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatePhoto")
	@ResponseBody
	public String updatePhoto(String id, String tmId, String demoId) {
		if (id == null || tmId == null) {
			return null;
		}

		String[] ids = id.split(",");
		PhotoInfo info = null;
		PhotoInfoResult result = null;
		for (String t : ids) {
			info = new PhotoInfo();
			info.settMId(tmId);
			info.setId(t);
			info.setDemoid(demoId);
			info.setStatus(1); // 审核通过状态要改变
			result = clazzPhotoService.updatePhoto(info);
		}
		if(result.isSuccess()){
			String type = null;
			String TMId = null;
			AlbumInfo albumInfo = new AlbumInfo();
			albumInfo.setId(tmId);
			try{
				TMId = this.albumService.getAlubmById(albumInfo).getInfoResult().get(0).gettMId();
				if(this.schoolService.selectByPrimaryKey(TMId) != null){
					 type = "校园生活";
				}
				else if(this.clazzService.clazzFind(TMId) != null){
					type = "班级时光";
				}
			}
			catch(Exception e)
			{
						e.printStackTrace();
						return JSONUtil.toObject(result).toString();
			}
			if(NotEmptyString.isNotNull(type)){
			
				OfflineMessageVo message = new OfflineMessageVo();
				message.setReceiverId(TMId);
				message.setContent("您有一条新的优校云推送，请点击查看");
				
				message.setType(type);
				message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
				message.setStatue("2");//群发消息作为已发送，不保存到数据库，不关心是否收到
				JPushServiceImpl pushService = new JPushServiceImpl();
				
				//向ios端发送通知
				pushService.sendNoticeToTag(TMId, "您有一条新的" + type + "更新，请点击查看", "ios");
				
				//向所有平台发送推送
				pushService.sendMultiMessageToTag(message.toJsonString(), TMId, null);
				System.out.println(message.toJsonString());
			}
		}
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 上传照片
	 * 
	 * @param session
	 * @param request
	 * @param files
	 * @param tMId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadPhoto")
	@ResponseBody
	public String uploadPhoto(HttpSession session, HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, PhotoInfo photoInfo)
					throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		photoInfo.setZoneId(userInfo.getSchool_id()); // 用于后边记录模块更新时间 TODO
		boolean flag = true;
		int userType = userInfo.getUser_type().ordinal(); // 教师userType.ordinal为1,家长为2
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/photo", request, true);
				photoInfo.setVideoPath(vo.getRelativePath());
				photoInfo.setUploadTime(DateFormatUtil.formatDateToSeconds(new Date()));
				photoInfo.setSort(0);
				if (userType == 0) {
					photoInfo.setStatus(0); // 需要审核
				} else {
					photoInfo.setStatus(1); // 不需要审核
				}
				if (!clazzPhotoService.savePhoto(photoInfo).isSuccess()) {
					flag = false;
				}
				//如果照片需要审核则不推送，审核通过时再推送
				else if(photoInfo.getStatus()==0){
					resultMap.put("success", flag);
					return JSONUtil.toObject(resultMap).toString();
				}
				else{
					//照片上传成功，推送到对应的班级
					//照片可能是校园相册，也可能是班级相册，此处需要区分
					if(NotEmptyString.isNotNull(photoInfo.gettMId())){
						String type = null;
						String TMId = null;
						AlbumInfo info = new AlbumInfo();
						info.setId(photoInfo.gettMId());
						try{
							TMId = this.albumService.getAlubmById(info).getInfoResult().get(0).gettMId();
							if(this.schoolService.selectByPrimaryKey(TMId) != null){
								 type = "校园生活";
							}
							else if(this.clazzService.clazzFind(TMId) != null){
								type = "班级时光";
							}
						}
						catch(Exception e)
						{
									e.printStackTrace();
									resultMap.put("success", flag);
									return JSONUtil.toObject(resultMap).toString();
						}
						if(NotEmptyString.isNotNull(type)){
						
							OfflineMessageVo message = new OfflineMessageVo();
							message.setReceiverId(TMId);
							message.setContent("您有一条新的优校云推送，请点击查看");
							
							message.setType(type);
							message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
							message.setStatue("2");//群发消息作为已发送，不保存到数据库，不关心是否收到
							JPushServiceImpl pushService = new JPushServiceImpl();
							
							//向ios端发送通知
							pushService.sendNoticeToTag(TMId, "您有一条新的" + type + "更新，请点击查看", "ios");
							
							//向所有平台发送推送
							pushService.sendMultiMessageToTag(message.toJsonString(), TMId, null);
							System.out.println(message.toJsonString());
						}
					}
				}
			}
		}
		resultMap.put("success", flag);
		return JSONUtil.toObject(resultMap).toString();
	}

	private List<PhotoInfo> judgeStatus(int status, List<PhotoInfo> list) {
		List<PhotoInfo> remove = new ArrayList<PhotoInfo>();
		for (PhotoInfo info : list) {
			if (info.getStatus() != status) {
				remove.add(info);
			}
		}
		list.removeAll(remove);
		return list;
	}

	/**
	 * 删除照片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deletephoto")
	@ResponseBody
	public String deletephoto(String id, String tmId) {
		if (id == null || tmId == null) {
			return null;
		}
		String[] ids = id.split(",");
		PhotoInfo info = null;
		PhotoInfoResult result = null;
		for (String t : ids) {
			info = new PhotoInfo();
			info.setId(t);
			info.settMId(tmId);
			result = clazzPhotoService.deletePhoto(info);
		}
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 更新照片的name属性
	 * 
	 * @param id
	 *            name
	 * @return
	 */
	@RequestMapping("/updateName")
	@ResponseBody
	public String updateName(String id, String name) {
		if (id == null || name == null) {
			return null;
		}
		PhotoInfo info = new PhotoInfo();
		PhotoInfoResult result = null;
		info.setId(id);
		info.setName(name);
		result = clazzPhotoService.updatePhotoName(info);
		return JSONUtil.toObject(result).toString();
	}

}
