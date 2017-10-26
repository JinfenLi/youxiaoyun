/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月10日 下午9:09:05 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.po.Appraise;
import com.topview.school.po.Student;
import com.topview.school.service.base.Context;
import com.topview.school.service.base.Process;
import com.topview.school.util.ExcelUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.appraise.AppraiseInfo;

/** 
 * @ClassName: AppraiseSaveByExcelProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月10日 下午9:09:05 
 *  
 */
@Service
public class AppraiseSaveByExcelProcess extends Process {
	
	@Autowired
	private AppraiseMapper appraiseMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	private static final Logger logger = Logger
			.getLogger(AppraiseSaveByExcelProcess.class);

	@Override
	public boolean doProcess(Context context) {
		AppraiseSavaRequest saverequest = (AppraiseSavaRequest)context.getRequest();
	try{
		
		List<String> headList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		ExcelUtil poi = new ExcelUtil();
		File file = null;
		List<AppraiseInfo> result;
		headList.add("学生姓名");
		headList.add("评价星数");
		headList.add("评价内容");
//		headList.add("班主任还是任课老师");

		map.put("学生姓名", "name");
		map.put("评价星数", "star");
		map.put("评价内容", "word");
//		map.put("班主任还是任课老师", "type");
		System.out.println(saverequest.getPath());
		try {
			result = poi.importExcel("Sheet1", saverequest.getPath(), map,
					headList, AppraiseInfo.class); // 解析Excel内容
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		for(AppraiseInfo info : result) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name",info.getName());
			params.put("t_sc_class_id",saverequest.getClass_id());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);	
			List<Student> students = studentMapper.findByMulti(param);
			Student student = students.size()!=0?students.get(0):null;
	//		Appraise appraise = appraiseMapper.findByStudentidAndSemester(student.getId(),saverequest.getSemester());
//		if(appraise==null) {
			Appraise appraise = new Appraise();
			appraise.setId(UUIDUtil.getUUID());
			appraise.setStudentId(student.getId());
			appraise.setTeacherId(saverequest.getTeacherId());
			appraise.setTemplateId(saverequest.getTemplate_id());
			appraise.setSemesterId(saverequest.getSemester());
			appraise.setStar(info.getStar());
			appraise.setWord(info.getWord());
			appraise.setType(saverequest.getType());
			appraise.setClassId(saverequest.getClass_id());
			appraise.setStage(saverequest.getStage());
			appraise.setTime(new Date());
 		try {
 			appraiseMapper.insert(appraise);

     		} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(); // 事务回滚
			}
		}
	//	}
		
		file = new File(saverequest.getPath());
		file.delete();// 数据读取完后删除掉文件
		saverequest.setSuccess(true);
		return true;
	}catch(Exception e) {
		logger.error("save appraise fail");
		saverequest.setSuccess(false);
		return false;
	}
		
	
	}

}
