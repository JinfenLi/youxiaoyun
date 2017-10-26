package com.topview.school.service.clazz.exam;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.topview.school.dao.curricula.CurriculaVariableMapper;
import com.topview.school.dao.exam.ExamMapper;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.po.Clazz;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.po.Exam;
import com.topview.school.vo.exam.ExamInfo;
import com.topview.school.vo.exam.ExamInfoToTeacherResult;

public class ExamServiceImpl implements ExamService {
	private List<ExamProcess> examFindProcesses;

	@Resource
	private ExamMapper examMapper;
	@Resource
	private CurriculaVariableMapper curriculaVariableMapper;
	@Resource
	private ClazzMapper clazzMapper;

	@Override
	public ExamInfoToTeacherResult examFind(HttpSession session) {
		ExamProcessContext context = new ExamProcessContext();
		ExamInfoToTeacherResult result = new ExamInfoToTeacherResult();
		context.setResult(result);
		context.setSession(session);
		for (ExamProcess process : examFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 创建一次考试
	 */
	@Override
	public boolean createExam(ExamInfo info) {
		Exam exam = ExamInfo.changeToPo(info);
		try {
			return examMapper.insertSelective(exam) > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除一个考试
	 */
	@Override
	public boolean deleteExam(String examId) {
		try {
			return examMapper.deleteByPrimaryKey(examId) > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateExam(ExamInfo info) {
		try {
			Exam exam = ExamInfo.changeToPo(info);
			return examMapper.updateByPrimaryKeySelective(exam) > 0 ? true
					: false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ExamProcess> getExamFindProcesses() {
		return examFindProcesses;
	}

	public void setExamFindProcesses(List<ExamProcess> examFindProcesses) {
		this.examFindProcesses = examFindProcesses;
	}

	@Override
	public List<ExamInfo> selectExamRecord(Map<String, Object> map) {
		List<ExamInfo> examInfos = ExamInfo.changeToVo(examMapper.selectExamRecord(map));
		for(int i = 0; i < examInfos.size(); i++) {
			CurriculaVariable curriculaVariable = curriculaVariableMapper.selectByPrimaryKey(examInfos.get(i).getCurriculaVariableId());
			Clazz clazz = clazzMapper.selectByPrimaryKey(curriculaVariable.gettScClassId());
			examInfos.get(i).setClazzId(clazz.getId());
			examInfos.get(i).setClazzName(clazz.getName());
		}
		return examInfos;
	}

	@Override
	public int selectExamRecordCount(Map<String, Object> map) {
		return examMapper.selectExamRecordCount(map);
	}

	@Override
	public List<Exam> selectByCurriculaVariableId(String curriculaVariableId) {
		return examMapper.selectByCurriculaVariableId(curriculaVariableId);
	}

}
