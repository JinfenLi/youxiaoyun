package com.topview.school.service.healthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.school.dao.healthy.HealthyMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.po.Healthy;
import com.topview.school.po.Student;
import com.topview.school.util.ExcelUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.exam.HealthyInfo;

/**
 * 健康的service层接口类
 * @ClassName: HealthyServiceImpl 
 * @author lxd  <836696016@qq.com>
 * @date 2015年8月18日 下午10:23:35 
 * @version V1.0
 */
@Service
public class HealthyServiceImpl implements HealthyService{

	@Resource
	private HealthyMapper healthyMapper;
	@Resource
	private StudentMapper studentMapper;

	@Override
	public boolean addHealthy(Healthy healthy) {
		Healthy h = healthyMapper.selectByStudent(healthy.getStudentId());
		if(h != null){
			healthyMapper.deleteByPrimaryKey(h.getId());
		}
		healthy.setId(UUIDUtil.getUUID());
		return healthyMapper.insert(healthy) > 0 ? true : false;
	}

	@Override
	public boolean deleteHealthy(String healthyId) {
		
		return healthyMapper.deleteByPrimaryKey(healthyId) > 0 ? true : false;
	}

	@Override
	public List<Healthy> selectByClass(String classId) {
		return healthyMapper.selectByClass(classId);
	}

	@Override
	public Healthy selectByStudent(String studentId) {
		return healthyMapper.selectByStudent(studentId);
	}

	@Override
	public boolean updateHealthy(Healthy healthy) {
		return healthyMapper.updateByPrimaryKeySelective(healthy) > 0 ? true : false;
	}

	@Transactional
	@Override
	public boolean saveHealthyByExcel(String fileName, String realPath) {
		List<String> headList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		ExcelUtil poi = new ExcelUtil();
		List<HealthyInfo> hs = new ArrayList<HealthyInfo>();
		
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
		
		// 读出excel内容
		try {
			hs = poi.importExcel("sheet1", realPath, map, headList,
					HealthyInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for(HealthyInfo hi : hs){
			if(hi.getStudentId() == null || "".equals(hi.getStudentId())){
				return false;
			}
			Student student = studentMapper.findByNameAndIdCard(
					hi.getStudentName(), hi.getStudentId());
			if (student != null){
				Healthy view = new Healthy();
				view = hi.getHealthy(hi);
				view.setStudentId(student.getId());
				view.setId(UUIDUtil.getUUID());
				Healthy h = healthyMapper.selectByStudent(student.getId());
				if(h != null){
					healthyMapper.deleteByPrimaryKey(h.getId());
				}
				healthyMapper.insert(view);
			}
		}
		return true;
	}
}
