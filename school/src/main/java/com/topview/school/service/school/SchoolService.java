package com.topview.school.service.school;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.School;
import com.topview.school.vo.school.SchoolInfo;
import com.topview.school.vo.school.result.SchoolInfoResult;

public interface SchoolService {
	
	/**
	 * 根据学校id查询学校
	 * @param info
	 * @return
	 */
	public SchoolInfoResult schoolFind(SchoolInfo info);
	
	/**
	 * 保存学校
	 * @param info
	 * @return
	 */
	public SchoolInfoResult schoolSave(SchoolInfo info);
	
	/**
	 * 获取所有学校
	 * @return
	 */
	public List<School> getAllSchool(Pager pager);
	
	/**
	 * @dateTime 2016年7月25日上午11:15:04
	 * @author zjd
	 * @description 
	 */
	public List<School> getAllSchoolWithoutPage(int offset, int limit);
	
	/**
	 * 删除学校
	 * @param schoolId
	 * @return
	 */
	public boolean delectSchool(String schoolId);
	
	/**
	 * 更新学校信息
	 * @param info
	 * @return
	 */
	public boolean updateSchool(SchoolInfo info);
	
	/**
	 * 根据学校名称模糊查询学校
	 * @param name
	 * @return
	 */
	public List<School> selectSchoolByNameLike(String name);
	
	public School selectByPrimaryKey(String id);
	
	/**
	 * 根据家长手机号查询学校
	 * @param mobile
	 * @return
	 */
	public List<School> selectByParentPhone(String mobile);

	/**
	 * 根据区域id查询学校
	 * @param areaId
	 * @return
	 */
	public List<SchoolInfo> findByAreaId(Integer areaId);
	
}
