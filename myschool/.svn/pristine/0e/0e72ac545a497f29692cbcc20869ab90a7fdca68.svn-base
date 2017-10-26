package com.topview.school.controller.school.healthy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.topview.school.po.Healthy;
import com.topview.school.po.Student;
import com.topview.school.service.healthy.HealthyService;
import com.topview.school.service.user.student.StudentService;
import com.topview.school.util.ClassLoaderUtil;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.DownloadAndUploadUtil;
import com.topview.school.util.ExcelUtil;
import com.topview.school.util.FileUploadUtil;
import com.topview.school.util.JSONUtil;
import com.topview.school.vo.FileUploadVo;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.exam.HealthyInfo;

/**
 * 健康的控制层
 * 
 * @ClassName: HealthyController
 * @author lxd <836696016@qq.com>
 * @date 2015年8月18日 下午10:36:21
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/healthy", produces = "text/html;charset=UTF-8")
public class HealthyController {

	@Autowired
	private HealthyService healthyService;
	@Autowired
	private StudentService studentService;

	/**
	 * 单独给某个学生填写健康数据
	 * 
	 * @param healthy
	 * @return
	 */
	@RequestMapping(value = "/addHealthy", method = RequestMethod.POST)
	@ResponseBody
	public String addHealthy(Healthy healthy) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (healthy == null || "".equals(healthy)) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if (healthy.getStudentId() == null || "".equals(healthy.getStudentId())) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("success", healthyService.addHealthy(healthy));
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 删除健康数据
	 * 
	 * @param healthyId
	 * @return
	 */
	@RequestMapping("/deleteHealthy")
	@ResponseBody
	public String deleteHealthy(String healthyId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (healthyId == null || "".equals(healthyId)) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("success", healthyService.deleteHealthy(healthyId));
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 获取整个班的健康数据
	 * 
	 * @param classId
	 * @return
	 */
	@RequestMapping("/selectByClass")
	@ResponseBody
	public String selectByClass(String classId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (classId == null || "".equals(classId)) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		List<Healthy> hs = healthyService.selectByClass(classId);
		List<HealthyInfo> healthyInfos = new ArrayList<HealthyInfo>();
		for (Healthy h : hs) {
			HealthyInfo info = new HealthyInfo();
			Student s = studentService.selectByPrimaryKey(h.getStudentId());
			info.setHealthy(h);
			info.setStudentName(s.getName());
			info.setPicUrl(s.getPicurl());
			if (s.getBirthday() != null) {
				info.setFeteday(DateFormatUtil.formatDateToDay(s.getBirthday()));
			}
			healthyInfos.add(info);
		}
		result.put("result", healthyInfos);
		result.put("success", true);
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 单独获取某个学生的健康数据
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping("/selectByStudent")
	@ResponseBody
	public String selectByStudent(String studentId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (studentId == null || "".equals(studentId)) {
			result.put("result", null);
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		Healthy h = healthyService.selectByStudent(studentId);
		HealthyInfo info = new HealthyInfo();
		if (h != null) {
			info.setHealthy(h);
			Student s = studentService.selectByPrimaryKey(studentId);
			info.setStudentName(s.getName());
			info.setPicUrl(s.getPicurl());
			if (s.getBirthday() != null) {
				info.setFeteday(DateFormatUtil.formatDateToDay(s.getBirthday()));
			}
		}
		result.put("result", info);
		result.put("success", true);
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 修改学生健康
	 * 
	 * @param healthy
	 * @return
	 */
	@RequestMapping(value = "/updateHealthy", method = RequestMethod.POST)
	@ResponseBody
	public String updateHealthy(Healthy healthy) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (healthy == null || "".equals(healthy)) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		if (healthy.getId() == null || "".equals(healthy.getId())) {
			result.put("success", false);
			return JSONUtil.toObject(result).toString();
		}
		result.put("success", healthyService.updateHealthy(healthy));
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 上传学生健康数据
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadHealthy", method = RequestMethod.POST)
	@ResponseBody
	public String uploadHealthy(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		FileUploadVo vo = FileUploadUtil.uploadFile(file, userInfo.getSchool_id() + "/healthyUpload",
				request, true); // 保存上传文件
		try {
			boolean flag = healthyService.saveHealthyByExcel(vo.getFileName(),
					vo.getRealPath() + "\\" + vo.getFileName());
			result.put("success", flag);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return JSONUtil.toObject(result).toString();
	}

	/**
	 * 下载整个班级的健康数据
	 * 
	 * @param request
	 * @param classId
	 * @param className
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/downClassHealthy", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downClassHealthy(HttpServletRequest request,
			String classId, String className) throws IOException {
		// 根据班级id获取整个班级的id
		List<Student> students = studentService.selectByClazzId(classId);
		List<HealthyInfo> hs = new ArrayList<HealthyInfo>();
		for (Student s : students) {
			HealthyInfo hi = new HealthyInfo();
			hi.setStudentId(s.getIdcard());
			hi.setStudentName(s.getName());
			Healthy h = healthyService.selectByStudent(s.getId());
			if (h != null && !"".equals(h)) {
				hi.setHealthy(h);
			}
			hs.add(hi);
		}

		StringBuilder fileName = new StringBuilder(className);
		fileName.append("健康数据");
		String relativePath = ClassLoaderUtil.getExtendResource(
				"../" + "schoolUpload/healthyDownLoad", "school").toString(); // 获取相对于项目外的路径
		String realPath = relativePath.replace("/", "\\"); // 转换成绝对路径
		File file0 = new File(realPath);
		if (!file0.exists()) {
			file0.mkdirs();
		}
		String filePath = realPath + "\\" + fileName; // 目标excel文件绝对路径
		createHealthyExcel(filePath, hs);
		File file = new File(filePath);
		return DownloadAndUploadUtil.download(request, file,
				fileName.toString());
	}

	private void createHealthyExcel(String filePath, List<HealthyInfo> hs) {
		ExcelUtil poi = new ExcelUtil();
		List<String> headList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();

		headList.add("学生学号");
		headList.add("学生姓名");
		headList.add("身高");
		headList.add("体重");
		headList.add("血型");
		headList.add("血压");
		headList.add("左视力");
		headList.add("右视力");
		headList.add("左听力");
		headList.add("右听力");
		headList.add("口腔");
		headList.add("既往病史");
		headList.add("过敏反应");
		headList.add("体检体温");
		headList.add("备注");

		map.put("学生学号", "studentId");
		map.put("学生姓名", "studentName");
		map.put("身高", "height");
		map.put("体重", "weight");
		map.put("血型", "bloodType");
		map.put("血压", "bloodPressure");
		map.put("左视力", "leftVision");
		map.put("右视力", "rightVision");
		map.put("左听力", "leftListen");
		map.put("右听力", "rightListen");
		map.put("口腔", "oral");
		map.put("既往病史", "historyMedical");
		map.put("过敏反应", "allergy");
		map.put("体检体温", "bodyTem");
		map.put("备注", "remarks");

		try {
			poi.exportExcel("Sheet1", filePath, map, headList, hs, 1,
					HealthyInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
