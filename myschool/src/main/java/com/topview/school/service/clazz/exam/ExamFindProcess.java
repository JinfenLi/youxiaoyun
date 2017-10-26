package com.topview.school.service.clazz.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.curricula.CurriculaVariableMapper;
import com.topview.school.dao.exam.ExamMapper;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Clazz;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.po.Exam;
import com.topview.school.po.Teacher;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.exam.ExamInfo;
import com.topview.school.vo.exam.ExamInfoToTeacher;
import com.topview.school.vo.exam.ExamInfoToTeacherResult;

@Service
public class ExamFindProcess implements ExamProcess{
	
	@Resource
	private ExamMapper examMapper;
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private CurriculaVariableMapper curriculaVariableMapper;
	
	public boolean doProcess(ExamProcessContext context) {
		
		ExamInfoToTeacherResult result = context.getResult();
		UserInfo userInfo = (UserInfo) context.getSession().getAttribute("currentUser");
		String userId = userInfo.getUser_id(); //当前用户的id
		String clazzId = userInfo.getClass_id();
		String subject = (String) context.getSession().getAttribute("subject"); 
		Teacher teacher = new Teacher();
		
		Clazz clazz = clazzMapper.selectByPrimaryKey(clazzId);//获取当前班级
		if(clazzId.equals(teacherMapper.selectByPrimaryKey(userId).gettScClassId())) { //如果是班主任
			if(subject == null || subject.equals("")) {
				subject = "语文";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("clazzId", clazzId);
			map.put("subject", subject);
			List<Exam> exams = examMapper.getExamList(map); //通过班级id和科目名称获取考试列表
			if(exams.size() > 0) {
				CurriculaVariable cv = curriculaVariableMapper.selectByPrimaryKey(exams.get(0).gettScCurriculaVariableId());
				teacher = teacherMapper.selectByPrimaryKey(cv.gettScTeacherId());
			}
			
			List<ExamInfo> infos = ExamInfo.changeToVo(exams);
			List<ExamInfoToTeacher> resultInfo = ExamInfoToTeacher.changeToVo(infos, teacher, clazz);
			result.setResult(resultInfo);
			result.setSuccess(true);
			context.setResult(result);
		}
		else { //如果是科任
			teacher = teacherMapper.selectByPrimaryKey(userId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teacherId", userId);
			map.put("clazzId", clazzId);
			List<Exam> exams = examMapper.getExams(map);
			List<ExamInfo> infos = ExamInfo.changeToVo(exams);
			List<ExamInfoToTeacher> resultInfo = ExamInfoToTeacher.changeToVo(infos, teacher, clazz);
			result.setResult(resultInfo);
			result.setSuccess(true);
			context.setResult(result);
		}
		return true;
	}
}
