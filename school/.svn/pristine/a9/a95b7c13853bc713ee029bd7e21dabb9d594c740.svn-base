package com.topview.school.service.user.teacher;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.school.po.TeacherPosition;

/**
 * 教师职位service层接口
* @ClassName: TeacherPositionService 
* @author lxd  <836696016@qq.com>
* @date 2015年8月14日 下午8:31:47 
* @version V1.0
 */
public interface TeacherPositionService {

	public boolean add(TeacherPosition position);
	
	public boolean delete(String positionId);
	
	public List<TeacherPosition> getPositions(String teacherId);
	
	public boolean update(TeacherPosition position);
	
	public List<TeacherPosition> getAllPositions(Pager pager, String schoolId);
	
	public int getCount(String schoolId);
}
