package com.topview.school.controller.multimedia.file;

import java.io.File;
import java.io.IOException;
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
import com.topview.message.service.PushMsgService;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.file.FileService;
import com.topview.multimedia.util.FileSizeUtil;
import com.topview.multimedia.vo.FileInfo;
import com.topview.multimedia.vo.result.FileInfoResult;
import com.topview.school.service.school.SchoolService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月30日 上午11:33:29 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Controller
@RequestMapping(value = "/file", produces = "text/html;charset=UTF-8")
public class FileController {
	
	@Resource
	private FileService fileService;
	@Autowired
	private SchoolService schoolService;
	@Resource
	private PushMsgService pushMsgService;
	
	
	/**
	 * 上传文件
	 * 
	 * @param session
	 * @param request
	 * @param files
	 * @param tMId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public String uploadFile(HttpSession session, HttpServletRequest request,
			@RequestParam("file") MultipartFile[] files, 
			String clazzId) throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
        if(userInfo==null){
			resultMap.put("success", false);
			resultMap.put("code", 500);
		}else if(fileService.selectCount(clazzId)>30){
			resultMap.put("msg", "超过30个文件");
			resultMap.put("success", false);
		}else{
			FileUploadVo vo = new FileUploadVo();
			String suffix = null;
			MultipartFile file = null;
			boolean flag = false;
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					 file = files[i];
					 long size = file.getSize();
					 if(size>52428800){
						 resultMap.put("msg", "不能超过50M");
						 resultMap.put("success", false);
						 break;
					 }
					 vo = FileUploadUtil.uploadFile(file,"file"+"/"+schoolService.selectByPrimaryKey(userInfo.getSchool_id()).getName() ,
							request, true);
				    suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				    String[] ids = clazzId.split(",");
				    FileInfo fileInfo = null;
				    for(String t:ids){
				    	fileInfo = new FileInfo();
				    	fileInfo.settMId(t);
					fileInfo.setFilePath(vo.getRelativePath());
					fileInfo.setType(suffix);
		            fileInfo.setName(file.getOriginalFilename());
		            fileInfo.setUploader(userInfo.getUser_name());
		            fileInfo.setUploaderId(userInfo.getUser_id());
		            fileInfo.setSize(FileSizeUtil.convertFileSize(size));
		            flag=fileService.fileSave(fileInfo).isSuccess();
				    }
				    
				}
				//文件上传成功之后，推动到用户
				if(flag == true){
					sendShareFile(userInfo,files[0].getOriginalFilename(),1);
				}
				
				
			}
			resultMap.put("success", flag);
		}
		
		return JSONUtil.toObject(resultMap).toString();
	}
	
	
	/**
	 * 删除文件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public String deleteFile(HttpSession session,String id,HttpServletRequest request,String filePath) {
		Map<String, Object> resultMap = new HashMap<String ,Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String path = session.getServletContext().getRealPath("/");
		path = path.substring(0,path.lastIndexOf(File.separator+"school"));
		filePath = path + filePath;
		if(userInfo==null){
			resultMap.put("code", 500);
			resultMap.put("success", false);
		}else{
			String[] ids = id.split(",");
			FileInfo info = null;
			for (String t : ids) {
				info = new FileInfo();
				info.setId(t);
				info.setUploaderId(userInfo.getUser_id());
				info.setFilePath(filePath);
				if(fileService.fileJudgeOwner(info).isSuccess()){
					resultMap.put("success",fileService.fileDelete(info));
					sendShareFile(userInfo,filePath.substring(filePath.lastIndexOf("/")+1),2);
				}else{
					resultMap.put("message", "没有权限删除");
				}
			}
		}
		
		return JSONUtil.toObject(resultMap).toString();
	}
	/**
	 * 查看相册里的所有文件
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getAllFiles")
	@ResponseBody
	public String getAllFiles(HttpSession session,String clazzId,Integer start,Integer limit){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String filter[] = { "pager" };
		if(userInfo==null){
			resultMap.put("code", 500);
			resultMap.put("success", false);
		}else{
			FileInfo file = new FileInfo();
		Pager pager = new Pager();
		if (start != null && !"".equals(start) && limit != null && !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}
		file.settMId(clazzId);
		file.setPager(pager);
			FileInfoResult 	fileinfo =  fileService.fileFindAll(file);
			List<FileInfo>	info = fileinfo.getInfoResult();
		if(info!=null){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		resultMap.put("filelist", info);
		}
		
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	/**
	 * 查看某个文件信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getFile")
	@ResponseBody
	public String getFile(HttpSession session,String fileId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String filter[] = { "pager" };
		if(userInfo==null){
			resultMap.put("code", 500);
			resultMap.put("success", false);
		}else{
			FileInfo file = new FileInfo();
		file.setId(fileId);
		FileInfoResult result = fileService.fileFind(file);
		if(result!=null){
			file = result.getInfoResult().get(0);
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		
		resultMap.put("filelist", file);
		}
		
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	/**
	 * 更改文件
	 * @param fileId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/updateFile")
	@ResponseBody
	public String updateFile(String fileId,String name){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FileInfo file = new FileInfo();
		file.setId(fileId);
		file.setName(name);
		boolean result = fileService.fileUpdate(file).isSuccess();
		resultMap.put("success", result);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	/**
	 * 模糊查询文件名
	 * @param session
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/selectFileByName")
	@ResponseBody
	public String selectFileByName(HttpSession session,String name){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String filter[] = { "pager" };
		if(userInfo==null){
			resultMap.put("code", 500);
			resultMap.put("success", false);
		}else{
			FileInfo file = new FileInfo();
		file.setName(name);
		FileInfoResult result = fileService.fileFindByName(file);
		resultMap.put("filelist", result);
		}
		
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	/**
	 * 红点提示文件
	 * @param session
	 * @param clazzid
	 * @param time
	 * @return
	 */
	@RequestMapping(value="/selectFileByLatest")
	@ResponseBody
	public String selectFileByLatest(HttpSession session,String clazzid,String time){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String filter[] = { "pager" };
		if(userInfo==null){
			resultMap.put("code", 500);
			resultMap.put("success", false);
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("tmid", clazzid);
			map.put("time", time);
			FileInfo file = new FileInfo();
		file.settMId(clazzid);
		file.setUploadTime(time);
		int count = fileService.getMentionFile(map);
		resultMap.put("count", count);
		}
		
		return JSONUtil.toObject(resultMap,filter).toString();
	}
	
	
	private boolean sendShareFile(UserInfo userInfo,String fileName,int type) {
		OfflineMessageVo message = new OfflineMessageVo();
		String messageId = UUIDUtil.getUUID();
		message.setMessageId(messageId);
		message.setReceiverId(userInfo.getClass_id());
		if(type == 1) {
			message.setContent(userInfo.getUser_name()+"共享了" + fileName + "等文件");
		} else {
			message.setContent(userInfo.getUser_name()+"取消共享" + fileName);
		}
		message.setStatue("1");
		message.setSenderId(userInfo.getUser_id());
		message.setType("11");
		message.setMessageType("4");
		message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
		pushMsgService.saveOfflineMessage(message);
		pushMsgService.pushMessage(message); // 推送
		return true;
	}
}
