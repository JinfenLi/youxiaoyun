package com.topview.school.service.school.grade;

import java.util.ArrayList;
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

}
