package com.topview.school.service.user.student;

import java.util.List;

import com.topview.school.po.Student;
import com.topview.school.vo.User.StudentAndParentInfo;
import com.topview.school.vo.User.StudentVo;
import com.topview.school.vo.User.result.StudentInfoResult;

public interface StudentService {

	public StudentInfoResult studentFind(StudentVo info);

	/**
	 * 创建学生家长excel表
	 * @param filePath
	 * @param sapis
	 * @return
	 */
	public boolean createStudentAndParentExcel(String filePath, List<StudentAndParentInfo> sapis);
	
	/**
	 * 解析上传的excel并保存学生家长个人信息
	 * @param fileName 文件名
	 * @param realPath 绝对路径
	 * @param clazzId 班级id
	 * @return
	 */
	public boolean uploadStudentAndParentInfo(String fileName, String realPath, String clazzId);
	
	/**
	 * 修改学生个人资料
	 * 
	 * @param s
	 * @return
	 */
	public boolean updateStudent(Student s);

	/**
	 * 根据班级id查询学生
	 * 
	 * @param clazzId
	 * @return
	 */
	public List<Student> selectByClazzId(String clazzId);

	/**
	 * 根据主键查询学生
	 * @param id
	 * @return
	 */
	public Student selectByPrimaryKey(String id);
	
	/**
	 * 根据家长id查询学生
	 * @param parentId
	 * @return
	 */
	public List<Student> findByParentId(String parentId);
	
	/**
	 * 删除学生家长
	 * @param studentId
	 * @param parentId
	 * @return
	 */
	public boolean deleteStudentAndParent(String studentId, String parentId);
}