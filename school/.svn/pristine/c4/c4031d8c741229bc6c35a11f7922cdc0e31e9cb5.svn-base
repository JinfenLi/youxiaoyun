package com.topview.school.service.school.grade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.service.account.AccountService;
import com.topview.multimedia.service.section.SectionService;
import com.topview.multimedia.vo.AccountInfo;
import com.topview.multimedia.vo.SectionInfo;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.dao.school.DepartmentsMapper;
import com.topview.school.dao.school.GradeMapper;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Clazz;
import com.topview.school.po.Grade;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.school.GradeVo;
import com.topview.school.vo.school.SemesterVo;

@Service
public class GradeServiceImpl implements GradeService {

	@Resource 
	private GradeMapper gradeMapper;
	@Resource
	private DepartmentsMapper departmentsMapper;
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private AccountService accountService;
	@Resource
	private SectionService sectionService;
	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private SemesterMapper semesterMapper;
	
	@Override
	public boolean createGrade(Grade grade) {
		//注册多媒体空间
		AccountInfo info = new AccountInfo();
		info.setId(grade.getId());
		info.setAccountStatus(1);
		info.setComment(grade.getName()+"多媒体空间");
		accountService.register(info);
		//创建教子学园的信息板块 
		SectionInfo sectionInfo = new SectionInfo();
		sectionInfo = new SectionInfo();
		sectionInfo.setId(UUIDUtil.getUUID());
		sectionInfo.setDescription2(grade.getName() + "教子学园板块");
		sectionInfo.setIcon("");
		sectionInfo.setName2("educate");
		sectionInfo.settMId(grade.getId());
		sectionInfo.setType("educate");
		sectionService.sectionCreate(sectionInfo);
		return gradeMapper.insert(grade) > 0 ? true : false;
	}
	
	/**
	 * 删除年级，首先删除年级组
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean delectGrade(String gradeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gradeId", gradeId);
		try {
			teacherMapper.cancelPosition(map); //取消已有的年级级长任命
			departmentsMapper.deleteByPrimaryKey(gradeId); //删除年级组
			return gradeMapper.deleteByPrimaryKey(gradeId) > 0 ? true : false;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public List<Grade> getAllGrade(String schoolId) {
		return gradeMapper.getAllGrade(schoolId);
	}
	@Override
	public boolean updateGrade(GradeVo gradeVo) {
		Grade grade = GradeVo.changeToPo(gradeVo);
		return gradeMapper.updateByPrimaryKeySelective(grade) > 0 ? true : false;
	}

	@Override
	public Grade selectByPrimaryKey(String id) {
		return gradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Grade> getMyGrade(UserInfo userInfo) {
		List<Grade> grades = new ArrayList<Grade>();
		String userType = userInfo.getUser_type().name();
		//家长类型
		if("Parent".equals(userType)) {
			Clazz c = clazzMapper.selectByPrimaryKey(userInfo.getClass_id());
			Grade g = gradeMapper.selectByPrimaryKey(c.gettScGradeId());
			grades.add(g);
		} else { //教师类型
			SemesterVo vo = semesterMapper.getCurrentSemester(userInfo.getSchool_id());
			List<Clazz> cs = clazzMapper.selectTeacherClazzs(userInfo.getUser_id(), vo.getId());
			for(Clazz c : cs) {
				Grade g = gradeMapper.selectByPrimaryKey(c.gettScGradeId());
				grades.add(g);
			}
			//剔除属性值重复的对象
			for(int i = 0; i < grades.size() - 1; i++) {
				for(int j = grades.size() - 1; j > i ; j--) {
					if(grades.get(j).getId().equals(grades.get(i).getId())) {
						grades.remove(j);
					}
				}
			}
		}
		return grades;
	}
	
	/**
	 * 
	* @Title: levelUpGrade
	* @Description: 年级升级
	* @param @param gradeId
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean levelUpGrade(String gradeId) {
		Grade grade = gradeMapper.selectByPrimaryKey(gradeId);
		String gradeName = grade.getName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gradeId", gradeId);
		boolean flag = true;
		if (gradeName.contains("一年")) {
			grade.setName(gradeName.replace("一年", "二年"));
			map.put("from", "一年");
			map.put("to", "二年");
		} else if (gradeName.contains("二年")) {
			grade.setName(gradeName.replace("二年", "三年"));
			map.put("from", "二年");
			map.put("to", "三年");
		} else if (gradeName.contains("三年")) {
			grade.setName(gradeName.replace("三年", "四年"));
			map.put("from", "三年");
			map.put("to", "四年");
		} else if (gradeName.contains("四年")) {
			grade.setName(gradeName.replace("四年", "五年"));
			map.put("from", "四年");
			map.put("to", "五年");
		} else if (gradeName.contains("五年")) {
			grade.setName(gradeName.replace("五年", "六年"));
			map.put("from", "五年");
			map.put("to", "六年");
		} else if (gradeName.contains("六年")) {
			grade.setName(Calendar.getInstance().get(Calendar.YEAR) + "届小学毕业");
			map.put("from", "六年");
			map.put("to", Calendar.getInstance().get(Calendar.YEAR) + "届小学毕业");
		} else if (gradeName.contains("初一")) {
			grade.setName(gradeName.replace("初一", "初二"));
			map.put("from", "初一");
			map.put("to", "初二");
		} else if (gradeName.contains("初二")) {
			grade.setName(gradeName.replace("初二", "初三"));
			map.put("from", "初二");
			map.put("to", "初三");
		} else if (gradeName.contains("初三")) {
			grade.setName(Calendar.getInstance().get(Calendar.YEAR) + "届初中毕业");
			map.put("from", "初三");
			map.put("to", Calendar.getInstance().get(Calendar.YEAR) + "届初中毕业");
		} else if (gradeName.contains("高一")) {
			grade.setName(gradeName.replace("高一", "高二"));
			map.put("from", "高一");
			map.put("to", "高二");
		} else if (gradeName.contains("高二")) {
			grade.setName(gradeName.replace("高二", "高三"));
			map.put("from", "高二");
			map.put("to", "高三");
		} else if (gradeName.contains("高三")) {
			grade.setName(Calendar.getInstance().get(Calendar.YEAR) + "届高中毕业");
			map.put("from", "高三");
			map.put("to", Calendar.getInstance().get(Calendar.YEAR) + "届高中毕业");
		} else {
			flag = false;
		}
		if (flag) {
			gradeMapper.updateByPrimaryKeySelective(grade);
			clazzMapper.levelUpClazzesByGradeId(map);
		}
		return true;
	}

	@Override
	public List<Grade> isExist(String schoolId, String name) {
		return gradeMapper.isExist(schoolId, name);
	}

}
