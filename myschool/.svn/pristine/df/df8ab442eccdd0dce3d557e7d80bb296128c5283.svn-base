package com.topview.school.service.school.semester;

import java.util.List;

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
	 * 保存学期
	 */
	@Override
	public boolean createSemester(SemesterVo vo) {
		Semester semester = SemesterVo.changeToPo(vo);
		return semesterMapper.insert(semester) > 0 ? true : false;
	}

	/**
	 * 选择一个学期作为当前学期
	 */
	@Override
	public boolean setCurrentSemester(String semesterId) {
		return semesterMapper.setCurrentSemester(semesterId) > 0 ? true : false;
	}

	/**
	 * 获取一个学校的所有学期
	 */
	@Override
	public List<SemesterVo> findAll(String schoolId) {
		return semesterMapper.findAll(schoolId);
	}

	/**
	 * 获取一个学校的当前学期
	 */
	@Override
	public SemesterVo getCurrentSemester(String schoolId) {
		return semesterMapper.getCurrentSemester(schoolId);
	}

	/**
	 * 获取当前和历史学期
	 */
	@Override
	public List<SemesterVo> selectHistorySemester(String schoolId) {
		return semesterMapper.selectHistorySemester(schoolId);
	}

}
