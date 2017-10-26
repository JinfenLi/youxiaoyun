package com.topview.school.service.user.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.multimedia.bean.Pager;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.dao.user.TeacherPositionMapper;
import com.topview.school.po.TeacherPosition;
import com.topview.school.util.UUIDUtil;

/**
 * 教师职位service接口实现类
 * 
 * @ClassName: TeacherPositionServiceImpl
 * @author lxd <836696016@qq.com>
 * @date 2015年8月14日 下午8:43:20
 * @version V1.0
 */
@Service
public class TeacherPositionServiceImpl implements TeacherPositionService {

	@Resource
	private TeacherPositionMapper teacherPositionMapper;
	@Resource
	private TeacherMapper teacherMapper;

	@Override
	public boolean add(TeacherPosition position) {
		position.setId(UUIDUtil.getUUID());
		if (position.getTemplate() == null) {
			position.setTemplate(false);
		}
		return teacherPositionMapper.insert(position) > 0 ? true : false;
	}

	@Override
	public boolean delete(String positionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("positionId", positionId);
		teacherMapper.deletePosition(map);// 删除所有与该职位有关联关系的记录
		return teacherPositionMapper.deleteByPrimaryKey(positionId) > 0 ? true
				: false;
	}

	@Override
	public List<TeacherPosition> getPositions(String teacherId) {
		return teacherPositionMapper.getPositions(teacherId);
	}

	@Override
	public boolean update(TeacherPosition position) {
		if (position.getTemplate() == null) {
			position.setTemplate(false);
		}
		return teacherPositionMapper.updateByPrimaryKeySelective(position) > 0 ? true
				: false;
	}

	@Override
	public List<TeacherPosition> getAllPositions(Pager pager, String schoolId) {
		return teacherPositionMapper.getAllBySchoolId((pager.getPageNumber() - 1)
				* pager.getPageSize(), pager.getPageSize(), schoolId);
	}

	@Override
	public int getCount(String schoolId) {
		return teacherPositionMapper.getCount(schoolId);
	}

}
