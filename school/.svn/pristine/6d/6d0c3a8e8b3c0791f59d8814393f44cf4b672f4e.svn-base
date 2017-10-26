package com.topview.school.service.school.department;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.school.DepartmentsMapper;
import com.topview.school.dao.user.Teacher2DepartmentsMapper;
import com.topview.school.po.Departments;
import com.topview.school.po.Teacher2DempartmentsKey;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentsMapper departmentsMapper;
	@Resource
	private Teacher2DepartmentsMapper teacher2DepartmentMapper;

	/**
	 * 创建部门
	 */
	@Override
	public boolean createDepartment(Departments departments) {
		return departmentsMapper.insertSelective(departments) > 0 ? true
				: false;
	}

	/**
	 * 删除部门
	 */
	@Override
	public boolean deleteDepartment(String departmentId) {
		try {
			return departmentsMapper.deleteByPrimaryKey(departmentId) > 0 ? true
					: false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改部门信息
	 */
	@Override
	public boolean updateDepartment(Departments departments) {
		try {
			return departmentsMapper.updateByPrimaryKeySelective(departments) > 0 ? true
					: false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据不同条件查询部门
	 */
	@Override
	public List<Departments> selectDepartments(Map<String, Object> map) {
		return departmentsMapper.selectDepartments(map);
	}

	@Override
	public int selectCount(String schoolId) {
		return departmentsMapper.selectCount(schoolId);
	}

	@Override
	public boolean assignTeacherToDepartment(String departmentsId,
			String teacherId, int option) {
		Teacher2DempartmentsKey t = new Teacher2DempartmentsKey();
		t.setDepartmentsId(departmentsId);
		t.setTeacherId(teacherId);

		if (option == 1) {
			try {
				teacher2DepartmentMapper.insert(t);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else if (option == 2) {
			teacher2DepartmentMapper.delete(t);
		} else {
			return false;
		}
		return true;

	}

	@Override
	public List<Departments> selectAllBySchoolId(String schoolId) {
		return departmentsMapper.selectAllBySchoolId(schoolId);
	}

}
