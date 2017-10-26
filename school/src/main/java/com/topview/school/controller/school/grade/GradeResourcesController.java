package com.topview.school.controller.school.grade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.GradeResources;
import com.topview.school.po.ResourceType;
import com.topview.school.po.ValidationResult;
import com.topview.school.service.school.grade.GradeResourcesService;
import com.topview.school.service.school.grade.ResourceTypeService;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.ImageUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.util.ValidationUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.school.GradeResourcesVO;




//待完成模块

/**
 *年级资源控制层
* @ClassName: GradeResourcesController 
* @author cx  <747184616@qq.com>
* @date 2015年8月13日 下午7:44:57 
* @version V1.0
 */
@Controller
@RequestMapping(value = "/grade_resources", produces = "text/html;charset=UTF-8")
public class GradeResourcesController {

	@Resource
	private GradeResourcesService gradeResourcesService;
	@Resource
	private ResourceTypeService resourceTypeService;

	@RequestMapping("/getAllResoureces")
	@ResponseBody
	public String getAllResoureces(HttpSession session,String schoolId,String page,String typeId){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String, Object>();
	/*	if(gradeId != null || !"".equals(gradeId)){
			params.put("gradeId", gradeId);
		}*/
		
		if(schoolId!=null||!"".equals(schoolId)){
			params.put("schoolId",schoolId);
		}
		if(typeId != null || !"".equals(typeId)){
			params.put("typeId", typeId);
		}
		
		if(page==null||"".equals(page)){
			page="1";
		}
		
		Pager	pager = new Pager();
		try{
		pager.setPageNumber(Integer.parseInt(page));
		}
		catch(Exception e){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		params.put("limit", pager.getPageSize());
		params.put("offset", (pager.getPageNumber() - 1)*pager.getPageSize());
		List<GradeResources> grs = gradeResourcesService.getAllResources(params);
		List<GradeResourcesVO> gvs = new ArrayList<GradeResourcesVO>();
		//String tId = null;
		ResourceType type = null;
		for(GradeResources g : grs){
			type = resourceTypeService.getById(g.getResourceTypeId());
			gvs.add(GradeResourcesVO.changeToVo(g, type));
		}
		int count = gradeResourcesService.getCountBySchool(params);
		result.put("result", gvs);
		result.put("totalCount", count);
		result.put("success", true);
		return JSONUtil.toObject(result).toString();
	}
	
	
	@RequestMapping(value ="/uploadResource", method=RequestMethod.POST)
	public String uploadResource(HttpSession session, HttpServletRequest request,
			@RequestParam("file") MultipartFile file,@RequestParam(value="image") CommonsMultipartFile image, 
			GradeResources resources){
		Map<String, Object> result = new HashMap<String, Object>();
		ImageUtil imageUtil=new ImageUtil();
		if(resources == null || "".equals(resources)){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if(resources.getResourceTypeId()== null || "".equals(resources.getResourceTypeId())){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if(image==null){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		/*if(image.getSize()>4000000||image.equals(null)){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		System.out.println(image.getOriginalFilename());*/
			if(!imageUtil.isImage(image.getOriginalFilename())){
				result.put("success", false);
				return JSONUtil.toObject(result).toString();
			}
		resources.setId(UUIDUtil.getUUID());
		resources.setUploadTime(new Date(System.currentTimeMillis()));
		resources.setIsLink(true);
		try {
			if(file.getBytes().length > 0){
				FileUploadVo f = FileUploadUtil.uploadFile(file, "grade_resource", request,true);
				resources.setResourcepath(f.getRelativePath());
				resources.setIsLink(false);
			}
			if(image.getBytes().length>0){
				FileUploadVo p=FileUploadUtil.uploadFile(image, "resource_thumbnail", request, true);
				resources.setThumbnailPath(p.getRelativePath());
			}
		} catch (IOException e) {
			result.put("success", false);

			return JSONUtil.toObject(result).toString();
		}
		if(gradeResourcesService.saveResource(resources) > 0){
			result.put("success", true);

		}else{
			result.put("success", false);

		}
		return JSONUtil.toObject(result).toString();
	}
	
	
	@RequestMapping(value ="/updateResource", method=RequestMethod.POST)
	@ResponseBody
	public String updateResource(GradeResources resources){
		Map<String, Object> result = new HashMap<String, Object>();
		ValidationResult res = ValidationUtil.validateEntity(resources);
		if(res.isHasErrors()) {
			result.put("success", false);
			result.put("errorMsg", res.getErrorMsg());
			return JSONUtil.toObject(result).toString();
		}		
		if(resources == null || "".equals(resources)){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if(resources.getResourceTypeId() == null || "".equals(resources.getResourceTypeId())){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if(resources.getId() == null || "".equals(resources.getId())){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("success", gradeResourcesService.updateResource(resources));
		return JSONUtil.toObject(result).toString();
		
	}
	
	@RequestMapping(value ="/deleteResource", method=RequestMethod.POST)
	@ResponseBody
	public String deleteResources(String resourcesId){
			GradeResources resources=gradeResourcesService.getResourcesById(resourcesId);
			Map<String, Object> result = new HashMap<String, Object>();
			if(null==resources.getId() || "".equals(resources.getId())){
				result.put("success", false);
				return JSONUtil.toObject(result).toString();
			}
			if(resources == null || "".equals(resources)){
				result.put("success", false);
				return JSONUtil.toObject(result).toString();
			}
			if(resources.getResourceTypeId() == null || "".equals(resources.getResourceTypeId())){
				result.put("success", false);
				return JSONUtil.toObject(result).toString();
			}
/*			if(!resources.getIsLink()){
				String realPath=null;
				String fileName=null;
				System.out.println(resources.getResourcepath());
				fileName=resources.getResourcepath().split("/")[3];
				realPath=Thread.currentThread().getContextClassLoader()
	                    .getResource("").getPath();
				  realPath=realPath.replaceAll("myschool/WEB-INF/classes/","myschoolUpload/grade_resource/"+fileName);
				File f=new File(realPath);
				if(!f.delete()){
					result.put("success", false);
					System.out.println(realPath);
					return JSONUtil.toObject(result).toString();
				}
			}*/
			result.put("success", gradeResourcesService.deleteResource(resources.getId()));
			return JSONUtil.toObject(result).toString();
		
	}
	
	@RequestMapping(value="/downloadResources",method=RequestMethod.POST)
	@ResponseBody
	public String DownloadFile(HttpServletRequest requset,HttpServletResponse response,String fileName){
		Map<String,Object> result=new HashMap<String, Object>();
		InputStream inputStream;
		if("".equals(fileName)||fileName==null){
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		 response.setCharacterEncoding("utf-8");
	     response.setContentType("multipart/form-data");
	     response.setHeader("Content-Disposition", "attachment;fileName="
	                + fileName);
	     try {
	            String path = Thread.currentThread().getContextClassLoader()
	                    .getResource("").getPath();
	            path=path.replaceAll("myschool/WEB-INF/classes/","myschoolUpload/grade_resource");
	   
	            System.out.println(path);
	            if("".equals(path)||path==null){
	            	result.put("success", false);
	            	return JSONUtil.toObject(result).toString();
	            }
	            inputStream = new FileInputStream(new File(path
	                    + File.separator + fileName));		
	            OutputStream os = response.getOutputStream();
	            byte[] b = new byte[2048];
	            int length;
	            while ((length = inputStream.read(b)) > 0) {
	                os.write(b, 0, length);
	            }		 
	            os.close();			 
	            inputStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        	result.put("success", false);
            	return JSONUtil.toObject(result).toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        	result.put("success", false);
            	return JSONUtil.toObject(result).toString();
	        }
	     	result.put("success", "true");
	        return JSONUtil.toObject(result).toString();	
	}
	
	/** 
     * form表单提交 Date类型数据绑定 
     * <功能详细描述> 
     * @param binder 
     * @see [类、类#方法、类#成员] 
     */  
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	        dateFormat.setLenient(false);    
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	}  
	
}
