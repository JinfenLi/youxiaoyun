package com.topview.school.controller.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.school.service.clazz.curricula.CurriculaVariableService;
import com.topview.school.service.clazz.exam.ExamService;
import com.topview.school.service.school.semester.SemesterService;
import com.topview.school.util.JSONUtil;
import com.topview.school.util.NotEmptyString;
import com.topview.school.vo.curricula.CurriculaVariableInfo;
import com.topview.school.vo.exam.ExamInfo;

/**
 * @Description 考试Controller
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年8月4日 上午10:43:07
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
@RequestMapping(value = "/exam", produces = "text/html;charset=UTF-8")
@Controller
public class ExamController {

	@Autowired
	private ExamService examService;
	@Resource
	private SemesterService semesterService;
	@Resource
	private CurriculaVariableService curriculaVariableService;

	/**
	 * 根据选课id（单独某个班开考）或课程id(选了这门课的所有班级开考)创建考试
	 * 
	 * @param session
	 * @param info
	 * @return
	 */
	@RequestMapping("/createExam")
	@ResponseBody
	public String createExam(HttpSession session, ExamInfo info,
			String curriculaId, String semesterId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean flag = true;
		if (curriculaId != null && !"".equals(curriculaId)) {
			List<CurriculaVariableInfo> infos = curriculaVariableService
					.selectBySemesterIdAndCurriculaId(semesterId, curriculaId);
			for (int i = 0; i < infos.size(); i++) {
				info.setCurriculaVariableId(infos.get(i).getId());
				if (!examService.createExam(info)) {
					flag = false;
					break;
				}
			}
		} else {
			flag = examService.createExam(info);
		}
		resultMap.put("success", flag);
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 删除考试
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteExam")
	@ResponseBody
	public String deleteExam(HttpSession session, String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", examService.deleteExam(id));
		return JSONUtil.toObject(resultMap).toString();

	}

	/**
	 * 修改考试信息
	 * 
	 * @param session
	 * @param examInfo
	 * @return
	 */
	@RequestMapping("updateExam")
	@ResponseBody
	public String updateExam(HttpSession session, ExamInfo examInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", examService.updateExam(examInfo));
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 单条件或多条件查询考试记录
	 * 
	 * @param gradeId
	 * @param clazzId
	 * @param curriculaId
	 * @param semesterId
	 * @param subjectId
	 * @return
	 */
	@RequestMapping("selectExamRecord")
	@ResponseBody
	public String selectExamRecord(String curriculaVariableId, String gradeId,
			String clazzId, String curriculaId, String semesterId,
			String subjectId, Integer offset, Integer limit) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		if(!NotEmptyString.isAtLeastOneNotEmpty(new String[]{curriculaVariableId, subjectId, clazzId, semesterId, gradeId, curriculaId})) {
			resultMap.put("msg", "查询条件不足");
			resultMap.put("success", true);
			return JSONUtil.toObject(resultMap).toString();
		}
		map.put("curriculaVariableId", curriculaVariableId);
		map.put("subjectId", subjectId);
		map.put("clazzId", clazzId);
		map.put("semesterId", semesterId);
		map.put("gradeId", gradeId);
		map.put("curriculaId", curriculaId);
		map.put("offset", offset);
		map.put("limit", limit);
		
		resultMap.put("exams", examService.selectExamRecord(map));
		resultMap.put("totalCount", examService.selectExamRecordCount(map));
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	

}
