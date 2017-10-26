package com.topview.school.service.school.semester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.po.Semester;
import com.topview.school.vo.school.SemesterVo;

/**
 * @Description 学期service实现层
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年7月23日 下午3:19:57
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@Service
public class SemesterServiceImpl implements SemesterService {

	@Resource
	private SemesterMapper semesterMapper;

	/**
	 * 保存学期(添加学期时保存)
	 */
	@Override
	public boolean createSemester(SemesterVo vo) {
		Semester semester = SemesterVo.changeToPo(vo);
		if (semesterMapper.selectByName(semester.getName()) != null) {
			return false;
		}
		return semesterMapper.insert(semester) > 0;
	}
	
	/**
	 * 保存学期(添加学期时保存)
	 */
	@Override
	public boolean createSemesterForSchool(SemesterVo vo) {
		Semester semester = SemesterVo.changeToPo(vo);
		return semesterMapper.insert(semester) > 0;
	}

	/**
	 * 获取一个学校的所有学期
	 */
	@Override
	public List<SemesterVo> findAll(String schoolId) {
		List<SemesterVo> result = semesterMapper.findAll(schoolId);
		for (SemesterVo vo : result) {
			vo.setStartTime(vo.getStartTime().substring(0, vo.getStartTime().indexOf(" ")));
			vo.setEndTime(vo.getEndTime().substring(0, vo.getEndTime().indexOf(" ")));
		}
		return result;
	}

	/**
	 * 获取一个学校的当前学期
	 */
	@Override
	public SemesterVo getCurrentSemester(String schoolId) {
		SemesterVo vo = semesterMapper.getCurrentSemester(schoolId);
		vo.setStartTime(vo.getStartTime().substring(0, vo.getStartTime().indexOf(" ")));
		vo.setEndTime(vo.getEndTime().substring(0, vo.getEndTime().indexOf(" ")));
		return vo;
	}

	/**
	 * 获取当前和历史学期 
	 */
	@Override
	public List<SemesterVo> selectHistorySemester(String schoolId) {
		List<SemesterVo> result = semesterMapper.selectHistorySemester(schoolId);
		for (SemesterVo vo : result) {
			vo.setStartTime(vo.getStartTime().substring(0, vo.getStartTime().indexOf(" ")));
			vo.setEndTime(vo.getEndTime().substring(0, vo.getEndTime().indexOf(" ")));
		}
		return result;
	}
	
	/**
	 * 根据id更新学期
	 */
	@Override
	public boolean updateSemesterById(SemesterVo vo) {
		Semester semester = SemesterVo.changeToPo(vo);
		return semesterMapper.updateByPrimaryKeySelective(semester) > 0;
	}
	
	/**
	 * 设为当前学期
	 */
	@Override
	public boolean setCurrentSemester(String semesterId) {
		Semester semester = semesterMapper.selectByPrimaryKey(semesterId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schoolId", semester.gettScSchoolId());
		map.put("name", semester.getName());
		return semesterMapper.setCurrentSemester(map) > 0;
	}

}
