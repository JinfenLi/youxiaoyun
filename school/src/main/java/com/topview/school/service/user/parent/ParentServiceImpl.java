package com.topview.school.service.user.parent;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.user.ParentMapper;
import com.topview.school.po.Parent;
import com.topview.school.po.StudentParentConnection;

@Service
public class ParentServiceImpl implements ParentService {

	@Resource
	private ParentMapper parentMapper;

	/**
	 * 根据手机号查询家长
	 */
	@Override
	public Parent findByMoble(String moble) {
		return parentMapper.findByMoble(moble);
	}

	/**
	 * 修改家长密码
	 */
	public boolean updatePassword(Parent parent) {
		return parentMapper.updateByPrimaryKeySelective(parent) > 0 ? true
				: false;
	}

	/**
	 * 修改家长个人信息
	 */
	@Override
	public boolean updateParent(Parent parent) {
		return parentMapper.updateByPrimaryKeySelective(parent) > 0 ? true
				: false;
	}

	/**
	 * 根据主键查询家长
	 */
	@Override
	public Parent selectByPrimaryKey(String id) {
		return parentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据学生id查询家长
	 */
	@Override
	public List<Parent> selectByStudentId(String studentId) {
		return parentMapper.selectByStudentId(studentId);
	}

	@Override
	public Parent selectMainParent(String studentId) {
		return parentMapper.selectMainParent(studentId);
	}

	@Override
	public int addConnection(StudentParentConnection studentParentConnection) {
		return parentMapper.addConnection(studentParentConnection);
	}

	@Override
	public int updateConnection(StudentParentConnection studentParentConnection) {
		return parentMapper.updateConnection(studentParentConnection);
	}

	@Override
	public int addParent(Parent p, String studentId) {
		return parentMapper.insert(p);
	}

	@Override
	public List<Parent> getAllParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
