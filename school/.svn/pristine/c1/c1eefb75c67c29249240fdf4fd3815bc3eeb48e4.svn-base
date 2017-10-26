package com.topview.school.service.clazz.curricula;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.curricula.CurriculaTimeMapper;
import com.topview.school.dao.curricula.CurriculaVariableMapper;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.vo.curricula.ChooseCurriculaInfo;
import com.topview.school.vo.curricula.CurriculaVariableInfo;
import com.topview.school.vo.curricula.CurriculaVariableInfo2;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;

@Service
public class CurriculaVariableServiceImpl implements CurriculaVariableService {

	@Resource
	private CurriculaVariableMapper curriculaVariableMapper;
	
	@Resource
	private CurriculaTimeMapper curriculaTimeMapper;

	/**
	 * 保存班级选课
	 */
	@Override
	public boolean saveCurriculaVariable(CurriculaVariable curriculaVariable) {

		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("t_sc_semester_id", curriculaVariable.gettScSemesterId());
		params.put("t_sc_curricula_id", curriculaVariable.gettScCurriculaId());
		params.put("t_sc_class_id", curriculaVariable.gettScClassId());
		param.put("params", params);
		List<CurriculaVariable> cvs = curriculaVariableMapper
				.selectByParameters(param);
		if (cvs.size() > 0) {
			curriculaVariable.setId(cvs.get(0).getId());
			curriculaVariableMapper
					.updateByPrimaryKeySelective(curriculaVariable);
		} else {
			curriculaVariableMapper.insert(curriculaVariable);
		}
		return true;
	}

	/**
	 * 根据学期id和课程id查询所有选课信息
	 */
	@Override
	public List<CurriculaVariableInfo> selectBySemesterIdAndCurriculaId(
			String semesterId, String curriculaId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("semesterId", semesterId);
		map.put("curriculaId", curriculaId);
		List<CurriculaVariable> curriculaVariables = curriculaVariableMapper
				.selectBySemesterIdAndCurricualId(map);
		return CurriculaVariableInfo.changeToVo(curriculaVariables);
	}

	/**
	 * 根据学期id、年级id、学科id和教师id单条件或多条件查询选课结果
	 */
	@Override
	public List<CurriculaVariableInfo2> showCurriculaVariable(String subjectId,
			String gradeId, String teacherId, String semesterId, String classId, Integer start,
			Integer limit, Integer showTrunk) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectId", subjectId);
		map.put("gradeId", gradeId);
		map.put("teacherId", teacherId);
		map.put("semesterId", semesterId);
		map.put("classId", classId);
		map.put("offset", start);
		map.put("limit", limit);
		map.put("showTrunk", showTrunk);
		return curriculaVariableMapper.showCurriculaVariable(map);
	}

	/**
	 * 查询记录条数
	 * 
	 * @param subjectId
	 * @param gradeId
	 * @param teacherId
	 * @param semesterId
	 * @return
	 */
	public int selectCount(String subjectId, String gradeId, String teacherId,
			String semesterId, String classId, Integer showTrunk) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectId", subjectId);
		map.put("gradeId", gradeId);
		map.put("teacherId", teacherId);
		map.put("semesterId", semesterId);
		map.put("classId", classId);
		map.put("showTrunk", showTrunk);
		return curriculaVariableMapper.selectCount(map);
	}

	/**
	 * 根据班级id和学期id获取选课信息
	 */
	@Override
	public List<UploadCurriculaInfoVo> getSubjectBySemesterIdAndClazzId(
			String semesterId, String clazzId, String teacherId) {
		// 根据班级id和学期id查询选课记录
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazzId", clazzId);
		map.put("semesterId", semesterId);
		map.put("teacherId", teacherId);
		List<UploadCurriculaInfoVo> curriculaVariables = curriculaVariableMapper
				.selectBySemesterIdAndClazzId(map);
		return curriculaVariables;
	}

	@Override
	public boolean deleteCurriculaVariable(String curriculaVariableId) {
		try {
			curriculaTimeMapper.deleteByCurriculaVariableId(curriculaVariableId);
			return curriculaVariableMapper.deleteByPrimaryKey(curriculaVariableId) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CurriculaVariable selectByPrimaryKey(String id) {
		return this.curriculaVariableMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByPrimaryKey(
			CurriculaVariable curriculaVariable) {
		return this.curriculaVariableMapper.updateByPrimaryKey(curriculaVariable)>0 ;
	}

	@Override
	public ChooseCurriculaInfo getClazzCurriculaInfo(
			Map<String, Object> params) {
		return this.curriculaVariableMapper.getClazzCurriculaInfo(params);
	}

	@Override
	public Set<String> getCurriculaByTeacherAndSemesterId(String teacherId, String semesterId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("teacherId", teacherId);
		map.put("semesterId", semesterId);
		CurriculaVariable curriculaVariable;
		List<CurriculaVariable> list = curriculaVariableMapper.getCurriculaByTeacherAndSemesterId(map);
//		List<String> classList = new ArrayList<String>();
		Set<String> classList = new HashSet<String>();
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				curriculaVariable = list.get(i);
				classList.add(curriculaVariable.gettScClassId());
			}
		}
		return classList;
	}

}
