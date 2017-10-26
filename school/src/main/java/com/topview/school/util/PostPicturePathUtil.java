package com.topview.school.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.topview.multimedia.vo.PostVo;
import com.topview.school.vo.FileUploadVo;

public class PostPicturePathUtil {

	public static PostVo savePicturePath(PostVo vo,MultipartFile[] files,HttpServletRequest request,String schoolId,HttpSession session) {
		
		//当上传的图片只有一张时，将该图片按照原图的比例压缩
				if (files != null && files.length == 1){
					List<String> urls = new ArrayList<String>();
					MultipartFile file = files[0];
					FileUploadVo fileUploadVo = FileUploadUtil.uploadFile(file,
							schoolId + "/post/", request, false);
					ThumbnailUtil.thumbnailImage(fileUploadVo.getRealPath()+"/"+file.getOriginalFilename(), 0.3);
					if (fileUploadVo != null) {
						if(FileUploadUtil.setThumbnailPath(schoolId + "/post", fileUploadVo.getFileName()).equals("error")){
							urls.add(fileUploadVo.getRelativePath());
						}
						urls.add(FileUploadUtil.setThumbnailPath(schoolId + "/post", fileUploadVo.getFileName()));
					}
					vo.setUrls(urls);
					String path = session.getServletContext().getRealPath("/");
					String separator = File.separator;
					path = path.substring(0, path.lastIndexOf(separator + "school" + separator)) + urls.get(0);
					if(!separator.equals("/")) {
						path = path.replaceAll("/", "\\\\");
					}
					File temp = new File(path);
					BufferedImage image = null;
					try {
						image = ImageIO.read(temp);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (image != null) {
						Map<String, Object> resolution = new HashMap<String, Object>();
						resolution.put("width", image.getWidth());
						resolution.put("height", image.getHeight());
						vo.setResolution(resolution);
					}		
				}
				//上传多张图片时，采用压缩成方形的缩略图
				else if (files != null && files.length > 0) {
						List<String> urls = new ArrayList<String>();
						for(int i=0;i<files.length;i++) {
							MultipartFile file = files[i];
							FileUploadVo fileUploadVo = FileUploadUtil.uploadFile(file,
									schoolId + "/post/", request, false);
							File imageFile = new File(fileUploadVo.getRealPath()+"/"+file.getOriginalFilename());
							ThumbnailUtil.squareThumbnailGenerate(imageFile, 200);
							if (fileUploadVo != null) {
								if(FileUploadUtil.setThumbnailPath(schoolId + "/post", fileUploadVo.getFileName()).equals("error")){
									urls.add(fileUploadVo.getRelativePath());
								}
								urls.add(FileUploadUtil.setThumbnailPath(schoolId + "/post", fileUploadVo.getFileName()));
							}
							vo.setUrls(urls);
						}
				}
		return vo;
	}
	
	
}
