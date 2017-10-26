package com.topview.school.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.school.dao.base.impl.BaseDaoImpl;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.po.Parent;
import com.topview.school.po.StudentParentConnection;
import com.topview.school.vo.contacts.ContactsInfo;
import com.topview.school.vo.contacts.ParentContact;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：ParentMapperImpl  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月26日 下午8:44:39  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月26日 下午8:44:39  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
@Repository
public class ParentMapperImpl extends BaseDaoImpl<Parent> implements ParentMapper{

	@Override
	public Parent findByNameAndPassword(Map<String, Object> params) {
		return template.selectOne(getStatement("findByNameAndPassword"), params);
	}

	@Override
	public List<Parent> selectByStudentId(String id) {
		return template.selectList(getStatement("selectByStudentId"), id);
	}

	//根据班级id查看本班家长通讯录(已过时)
	@Deprecated
	@Override
	public List<ContactsInfo> selectContactsByClassId(String id) {
		return template.selectList(getStatement("selectContactByClassId"), id);
	}

	//根据家长姓名和手机号查询家长
	@Override
	public Parent findByNameAndPhone(String name, String phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("phone", phone);
		return template.selectOne(getStatement("findByNameAndPhone"), map);
	}

	/**
	 * 根据班级id获取家长通讯录
	 */
	@Override
	public List<ParentContact> getParentContacts(String clazzId) {
		return template.selectList(getStatement("getParentContacts"), clazzId);
	}
	
	/**
	 * 根据手机号查询家长
	 * @param moble
	 * @return
	 */
	public Parent findByMoble(String moble) {
		return template.selectOne(getStatement("findByAccount"), moble);
	}

	@Override
	public Parent selectMainParent(String studentId) {
		Parent parent = new Parent();
		try{		
			parent = (Parent) template.selectList(getStatement("selectMainParent"), studentId).get(0);
		}
		catch(Exception e){
			//TODO 设置值避免为空报错空指针异常
			parent.setId("NoParentExist");
		}
		return parent;
	}

	@Override
	public int addConnection(StudentParentConnection studentParentConnection) {
			return template.insert(getStatement("insertConnection"), studentParentConnection);
	}

	@Override
	public int updateConnection(StudentParentConnection studentParentConnection) {
			return template.update(getStatement("updateConnection"),studentParentConnection);
	}

	@Override
	public List<Parent> getAllParent() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
