package com.topview.school.service.school;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.bean.Pager;
import com.topview.school.dao.school.SchoolMapper;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.po.School;
import com.topview.school.vo.school.SchoolInfo;
import com.topview.school.vo.school.result.SchoolInfoResult;

public class SchoolServiceImpl implements SchoolService {

	private List<SchoolProcess> schoolFindProcesses;
	private List<SchoolProcess> schoolSaveProcesses;

	@Resource
	private SchoolMapper schoolMapper;
	@Resource
	private SemesterMapper semesterMapper;

	@Override
	public SchoolInfoResult schoolFind(SchoolInfo info) {
		SchoolProcessContext context = new SchoolProcessContext();
		SchoolInfoResult result = new SchoolInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (SchoolProcess process : schoolFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public SchoolInfoResult schoolSave(SchoolInfo info) {

		SchoolProcessContext context = new SchoolProcessContext();
		SchoolInfoResult result = new SchoolInfoResult();
		result.setResult(new ArrayList<SchoolInfo>());

		context.setResult(result);
		context.setInfo(info);
		for (SchoolProcess process : schoolSaveProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 获取所有学校信息
	 */
	@Override
	public List<School> getAllSchool(Pager pager) {
		return schoolMapper.selectAll(
				(pager.getPageNumber() - 1) * pager.getPageSize(),
				pager.getPageSize());
	}

	/**
	 * 删除学校
	 */
	@Transactional
	@Override
	public boolean delectSchool(String schoolId) {
		try {
			semesterMapper.delectSemesterBySchoolId(schoolId);
			if (schoolMapper.deleteByPrimaryKey(schoolId) > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return false;
	}

	/**
	 * 更新学校
	 */
	@Override
	public boolean updateSchool(SchoolInfo info) {
		return schoolMapper.updateByPrimaryKeySelective(SchoolInfo
				.changeToPo(info)) > 0 ? true : false;
	}

	/**
	 * 根据学校名字模糊查询学校
	 */
	@Override
	public List<School> selectSchoolByNameLike(String name) {
		return schoolMapper.selectSchoolLike(name);
	}
	
	public List<SchoolProcess> getSchoolFindProcesses() {
		return schoolFindProcesses;
	}

	public void setSchoolFindProcesses(List<SchoolProcess> schoolFindProcesses) {
		this.schoolFindProcesses = schoolFindProcesses;
	}

	public List<SchoolProcess> getSchoolSaveProcesses() {
		return schoolSaveProcesses;
	}

	public void setSchoolSaveProcesses(List<SchoolProcess> schoolSaveProcesses) {
		this.schoolSaveProcesses = schoolSaveProcesses;
	}

	@Override
	public School selectByPrimaryKey(String id) {
		return schoolMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<School> selectByParentPhone(String mobile) {
		return schoolMapper.selectByParentPhone(mobile);
	}

	@Override
	public List<SchoolInfo> findByAreaId(Integer areaId) {
		List<School> schools = schoolMapper.findByAreaId(areaId);
		return SchoolInfo.changeToVo(schools);
	}

}
