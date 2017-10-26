package com.topview.school.service.user.student;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.multimedia.util.UUIDUtil;
import com.topview.school.dao.user.Parent2StudentMapper;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.po.Parent;
import com.topview.school.po.Parent2Student;
import com.topview.school.po.Student;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.ExcelUtil;
import com.topview.school.vo.User.StudentAndParentInfo;
import com.topview.school.vo.User.StudentVo;
import com.topview.school.vo.User.result.StudentInfoResult;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ParentMapper parentMapper;
	@Resource
	private Parent2StudentMapper parent2StudentMapper;

	@Override
	public boolean updateStudent(Student s) {
		return studentMapper.updateByPrimaryKeySelective(s) > 0 ? true : false;
	}

	@Override
	public List<Student> selectByClazzId(String clazzId) {
		return studentMapper.selectByClazzId(clazzId);
	}

	@Override
	public Student selectByPrimaryKey(String id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public StudentInfoResult studentFind(StudentVo info) {
		StudentInfoResult result = new StudentInfoResult();
		try {
			Student student = studentMapper.selectByPrimaryKey(info.getId());
			List<Student> l = new ArrayList<Student>();
			l.add(student);
			List<StudentVo> infos = StudentVo.changeToVo(l);
			result.setResult(infos);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

	/**
	 * 创建学生家长excel表
	 */
	public boolean createStudentAndParentExcel(String filePath,
			List<StudentAndParentInfo> sapis) {
		List<String> headList = createHeadList();
		Map<String, String> map = createHeadListMap();
		ExcelUtil poi = new ExcelUtil();
		try {
			poi.exportExcel("Sheet1", filePath, map, headList, sapis, 1,
					StudentAndParentInfo.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 上传学生家长excel表
	 */
	@Transactional
	@Override
	public boolean uploadStudentAndParentInfo(String fileName, String realPath,
			String clazzId) {
		// 1.声明变量
		List<String> headList = createHeadList();
		Map<String, String> map = createHeadListMap();
		ExcelUtil poi = new ExcelUtil();
		File file = null;
		List<StudentAndParentInfo> result = null;
		boolean flag1 = false; // 判断学生和家长是否是本次上传才导入到数据库中的
		boolean flag2 = false;
		boolean flag3 = false;
		// 2.解析Excel内容
		try {
			result = poi.importExcel("Sheet1", realPath, map, headList,
					StudentAndParentInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// 处理解析之后的数据
		for (StudentAndParentInfo info : result) {
			if (info.getStudentName() == null
					|| "".equals(info.getStudentName())) {
				continue;
			}
			Student student = studentMapper.findByNameAndIdCard(
					info.getStudentName(), info.getStudentIDCard());
			if (student == null) {
				student = new Student();
				student.setId(UUIDUtil.getUUID());
				student.setCreateTime(new Date());
				student.settScClassId(clazzId);
				student.setAddress(info.getStudentAddress());
				student.setName(info.getStudentName());
				student.setSex(info.getStudentGender());
				if ("男".equals(info.getStudentGender())) {
					student.setPicurl("/schoolUpload/userPic/学生boy.png");
				} else {
					student.setPicurl("/schoolUpload/userPic/学生girl.png");
				}
				student.setPhone(info.getStudentPhone());
				student.setIdcard(info.getStudentIDCard());
				student.setEmergencyPhone(info.getEmergencyPhone());
				student.setLastupdate(new Date());
				student.setPassword("123456");
				if(info.getFeteday() != null && !"".equals(info.getFeteday())) {
					student.setBirthday(DateFormatUtil.parseToDay(info.getFeteday()));
				} 

				try {
					studentMapper.insertSelective(student); // 只有当数据库不存在该学生时才保存
					flag1 = true;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(); // 事务回滚
				}
			}
			Parent parent1 = parentMapper.findByMoble(info.getParentPhone()); // 只用手机号来确定有可能因为用户输入错误导致数据出现错乱，但很多家长并不提供姓名
			if (parent1 == null) {
				parent1 = new Parent();
				parent1.setId(UUIDUtil.getUUID());
				parent1.setCreateTime(new Date());
				// 家长姓名可为空
				if (info.getParentName() != null
						&& !"".equals(info.getParentName())) {
					parent1.setName(info.getParentName());
				}
				parent1.setParenttype("1"); // 主账号
				parent1.setMobile(info.getParentPhone());
				parent1.setPassword("123456");
				parent1.setSex(info.getParentGender());
				parent1.setType(1); // 用来标示是否激活状态？
				try {
					parentMapper.insert(parent1); // 只有当数据库不存在该家长时才保存
					flag2 = true;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
			Parent parent2 = parentMapper.findByMoble(info.getParent2Phone());
			if (parent2 == null) { // 只有当副账号尚未注册 且用户 要注册两个账号时执行
				if (info.getParent2Phone() != null
						&& !"".equals(info.getParent2Phone())) {
					parent2 = new Parent();
					parent2.setId(UUIDUtil.getUUID());
					parent2.setCreateTime(new Date());
					if (info.getParent2Name() != null
							&& !"".equals(info.getParent2Name())) {
						parent2.setName(info.getParent2Name());
					}
					parent2.setParenttype("2"); // 副账号
					parent2.setMobile(info.getParent2Phone());
					parent2.setPassword("123456");
					parent2.setSex(info.getParent2Gender());
					parent2.setType(1);
					try {
						parentMapper.insert(parent2); // 只有当数据库不存在该家长时才保存
						flag3 = true;
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(); // 事务回滚
					}
				}
			}

			if (flag1 == true || flag2 == true) {
				// 建立关联
				Parent2Student p2s = new Parent2Student();
				p2s.setParentId(parent1.getId());
				p2s.setStudentId(student.getId());
				try {
					parent2StudentMapper.insert(p2s);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(); // 事务回滚
				}
			}
			if (flag1 == true || flag3 == true) {
				if (parent2 != null) {
					Parent2Student p2s = new Parent2Student();
					p2s.setParentId(parent2.getId());
					p2s.setStudentId(student.getId());
					try {
						parent2StudentMapper.insert(p2s);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(); // 事务回滚
					}
				}
			}
		}
		file = new File(realPath);
		file.delete();// 数据读取完后删除掉文件
		return true;
	}

	/**
	 * 声明学生家长excel表的列头
	 * 
	 * @return
	 */
	private List<String> createHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("学生名字");
		headList.add("学生性别");
		headList.add("学生学号");
		headList.add("出生年月日");
		headList.add("学生地址");
		headList.add("紧急电话");
		headList.add("家庭固定电话");
		headList.add("家长1姓名");
		headList.add("家长1性别");
		headList.add("家长1电话");
		headList.add("家长2姓名");
		headList.add("家长2性别");
		headList.add("家长2电话");
		return headList;
	}

	/**
	 * 声明列头与数据集合的对应关系
	 * 
	 * @return
	 */
	private Map<String, String> createHeadListMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("学生名字", "studentName");
		map.put("学生性别", "studentGender");
		map.put("学生学号", "studentIDCard");
		// map.put("出生年月日", "birthday");
		map.put("出生年月日", "feteday");
		map.put("学生地址", "studentAddress");
		map.put("紧急电话", "emergencyPhone");
		map.put("家庭固定电话", "studentPhone");
		map.put("家长1姓名", "parentName");
		map.put("家长1性别", "parentGender");
		map.put("家长1电话", "parentPhone");
		map.put("家长2姓名", "parent2Name");
		map.put("家长2性别", "parent2Gender");
		map.put("家长2电话", "parent2Phone");
		return map;
	}

	@Override
	public List<Student> findByParentId(String parentId) {
		return studentMapper.findByParentId(parentId);
	}

	@Transactional
	@Override
	public boolean deleteStudentAndParent(String studentId, String parentId) {
		try {
			parent2StudentMapper.delete(studentId, parentId); //解除学生家长之间的关联
			if(studentMapper.findByParentId(parentId).size() < 1) { //若该家长仅管理一个学生则删除该家长账号否则不删除
				parentMapper.deleteByPrimaryKey(parentId);
			} 
			studentMapper.deleteByPrimaryKey(studentId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
