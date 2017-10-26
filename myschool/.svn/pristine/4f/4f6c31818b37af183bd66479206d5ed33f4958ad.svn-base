package com.topview.school.controller.multimedia.folder.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.multimedia.service.folder.file.FileService;
import com.topview.multimedia.vo.FileInfo;
import com.topview.multimedia.vo.result.FileInfoResult;
import com.topview.school.service.school.SchoolService;
import com.topview.school.service.school.grade.GradeService;
import com.topview.school.service.system.authc.RoleService;
import com.topview.school.util.DownloadAndUploadUtil;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
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
	@Autowired
	private GradeService gradeService;
	@Autowired
	private RoleService roleService;
	
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
			@RequestParam("files") MultipartFile[] files, 
			FileInfo fileInfo,String clazzId) throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		FileUploadVo vo = new FileUploadVo();
		String suffix = null;
		MultipartFile file = null;
		boolean flag = false;
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				 file = files[i];
				 vo = FileUploadUtil.uploadFile(file,"/file"+"/"+schoolService.selectByPrimaryKey(userInfo.getSchool_id()).getName() ,
						request, true);
			    suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				fileInfo.settMId(clazzId);
				fileInfo.setFilePath(vo.getRelativePath());
				fileInfo.setType(suffix);
	            fileInfo.setName(file.getOriginalFilename());
	            fileInfo.setUploader(userInfo.getUser_name());
	            fileInfo.setUploaderId(userInfo.getUser_id());
	            fileInfo.setSize(fileInfo.getSize());
	            flag=fileService.fileSave(fileInfo).isSuccess();
			}
			
		}
		resultMap.put("success", flag);
		return JSONUtil.toObject(resultMap).toString();
	}
	/**
	 * 下载文件
	 * @param response
	 * @param fileName
	 * @param fileInputStream
	 * @return
	 */
	@RequestMapping(value="/downloadFile")
	@ResponseBody
	public String downloadFile(HttpServletResponse response,
			String fileName, InputStream fileInputStream){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String flag = DownloadAndUploadUtil.ajaxdownLoad(response, fileName, fileInputStream);
		if(flag!=null){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
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
	public String deleteFile(HttpSession session,String id) {
		Map<String, Object> resultMap = new HashMap<String ,Object>();
		if (id == null) {
			return null;
		}
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		String[] ids = id.split(",");
		FileInfo info = null;
		for (String t : ids) {
			info = new FileInfo();
			info.setId(t);
			info.setUploaderId(userInfo.getUser_id());
			if(fileService.fileJudgeOwner(info).isSuccess()){
				resultMap.put("success",fileService.fileDelete(info));
			}else{
				resultMap.put("message", "没有权限删除");
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
	public String getAllFiles(HttpSession session,String clazzId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FileInfo file = new FileInfo();
		file.settMId(clazzId);
			FileInfoResult 	fileinfo =  fileService.fileFindAll(file);
			List<FileInfo>	info = fileinfo.getInfoResult();
		if(info!=null){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		resultMap.put("filelist", info);
		return JSONUtil.toObject(resultMap).toString();
	}
	/**
	 * 查看某个文件信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getFile")
	@ResponseBody
	public String getFile(String fileId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
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
		return JSONUtil.toObject(resultMap).toString();
	}

}
