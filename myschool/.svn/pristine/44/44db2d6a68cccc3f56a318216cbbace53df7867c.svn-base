package com.topview.school.service.user.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.school.SubjectMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Subject;
import com.topview.school.po.Teacher;
import com.topview.school.vo.User.TeacherVo;

@Service
public class TeacherFindByIdProcess implements TeacherProcess{

	private static final Logger logger = Logger
			.getLogger(TeacherFindByIdProcess.class);
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	public boolean doProcess(TeacherProcessContext context) {
		try{
			Teacher teacher = teacherMapper.selectByPrimaryKey(context.getInfo().getId());
			TeacherVo info = TeacherVo.changeToVo(teacher);
			Subject subject = subjectMapper.getTeacherSubject(teacher.getId());
			if(subject != null) { //不是所有能登陆这个系统的都是老师或有教课程
				info.setSubject(subject.getName());
			}
			List<TeacherVo> l = new ArrayList<TeacherVo>();
			l.add(info);
			context.getResult().setResult(l);
			context.getResult().setSuccess(true);
			return true;
		}catch(Exception e){
			logger.error("teacher find by id fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}


}
