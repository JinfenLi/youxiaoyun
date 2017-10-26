package com.topview.school.service.clazz.score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.message.service.PushMsgService;
import com.topview.message.vo.OfflineMessageVo;
import com.topview.school.dao.curricula.CurriculaVariableMapper;
import com.topview.school.dao.exam.ExamMapper;
import com.topview.school.dao.exam.ScoreMapper;
import com.topview.school.dao.school.SubjectMapper;
import com.topview.school.dao.user.ParentMapper;
import com.topview.school.dao.user.StudentMapper;
import com.topview.school.po.CurriculaVariable;
import com.topview.school.po.Exam;
import com.topview.school.po.Parent;
import com.topview.school.po.Score;
import com.topview.school.po.Student;
import com.topview.school.util.DateFormatUtil;
import com.topview.school.util.ExcelUtil;
import com.topview.school.util.PushThreadUtil;
import com.topview.school.util.ScoreInfoUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;
import com.topview.school.vo.exam.ScoreInfo;
import com.topview.school.vo.exam.ScoreInfoResult;
import com.topview.school.vo.exam.ScoreVoForStudent;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Resource
	private PushMsgService pushMsgService;
	@Resource
	private ScoreMapper scoreMapper;
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ParentMapper parentMapper;
	@Resource
	private ExamMapper examMapper;
	@Resource
	private CurriculaVariableMapper curriculaVariableMapper;
	@Resource
	private SubjectMapper subjectMapper;

	/**
	 * 解析excel保存成绩
	 */
	@Transactional
	@Override
	public boolean saveScore(String fileName, String realPath, String examId) {

		List<String> headList = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		ExcelUtil poi = new ExcelUtil();
		List<ScoreInfo> scoreInfos = new ArrayList<ScoreInfo>();
		List<Student> students = new ArrayList<Student>();

		// 写入表头
		headList.add("学生学号");
		headList.add("学生姓名");
		headList.add("学生成绩");
		headList.add("班内排名");
		// 建立对应关系
		map.put("学生学号", "idCard");
		map.put("学生姓名", "studentName");
		map.put("学生成绩", "score");
		map.put("班内排名", "ranking");
		// 查询该考试属于哪个选课从而确定属于哪个班级
		Exam exam = examMapper.selectByPrimaryKey(examId);
		CurriculaVariable cv = curriculaVariableMapper.selectByPrimaryKey(exam
				.gettScCurriculaVariableId());

		// 读出excel内容
		try {
			scoreInfos = poi.importExcel("sheet1", realPath, map, headList,
					ScoreInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		scoreInfos = sortScore2(scoreInfos);// 计算排名

		for (ScoreInfo info : scoreInfos) {
			if (info.getScore() == null || "".equals(info.getScore())) {
				continue;
			}
			// 查找学生,有可能存在同名同学号所以必须加上班级id参数
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			Student student = null;
			params.put("name", info.getStudentName());
			params.put("IDCard", info.getIdCard());
			params.put("t_sc_class_id", cv.gettScClassId());
			param.put("params", params);
			List<Student> result = studentMapper.findByMulti(param);
			if (result.size() > 0) { // 如果存在加入数组方便后续推送操作
				student = result.get(0);
				students.add(student);
			} else { // 否则学生姓名和学号不在该系统中则上传失败
				return false;
			}

			if (student != null) {
				// 判断是否已有此次考试的成绩，若有则更新，没有则插入
				Score score = scoreMapper.selectByStudentIdAndExamId(
						student.getId(), examId);
				if (score != null) {
					score.setScore(Float.valueOf(info.getScore()));
					if (info.getRanking() != null) {
						score.setRank_in_class(Integer.valueOf(info
								.getRanking()));
					}
					scoreMapper.updateByPrimaryKeySelective(score);
				} else {
					score = new Score();
					score.setId(UUIDUtil.getUUID());
					score.settScStudentId(student.getId());
					score.settScExamId(examId);
					score.setScore(Float.valueOf(info.getScore()));
					if (info.getRanking() != null) {
						score.setRank_in_class(Integer.valueOf(info
								.getRanking()));
					}
					try {
						scoreMapper.insertSelective(score);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException();
					}
				}
			}
		}
		new Thread(new PushThreadUtil(pushMsgService, getMessageVoList(students)))
				.start();// 推送
		return true;
	}

	/**
	 * 家长端查看学生成绩
	 */
	@Override
	public List<ScoreVoForStudent> getScoreForStudent(String semesterId,
			String studentId, String clazzId) {
		List<ScoreVoForStudent> scores = new ArrayList<ScoreVoForStudent>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据学期id和班级id确定本学期该班级所选的所有课程
		map.put("clazzId", clazzId);
		map.put("semesterId", semesterId);
		List<UploadCurriculaInfoVo> uci = curriculaVariableMapper
				.selectBySemesterIdAndClazzId(map);
		// 取出所选课程的id
		List<String> curriculaVariableIds = new ArrayList<String>();
		for (UploadCurriculaInfoVo vo : uci) {
			curriculaVariableIds.add(vo.getCurriculaVariableId());
		}
		// 根据选课id数组查询所有考试
		List<Exam> exams = examMapper
				.selectExamsByCurriculaVariableIds(curriculaVariableIds);

		// 循环考试记录结合学生id查询成绩
		map.put("studentId", studentId);
		for (Exam e : exams) {
			map.put("examId", e.getId());
			List<ScoreInfo> scoreInfos = scoreMapper.selectScoreByExamId(map);
			if (scoreInfos.size() > 0) {
				ScoreVoForStudent svfs = new ScoreVoForStudent();
				svfs.setExamId(e.getId());
				svfs.setExamName(e.getName());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				svfs.setExamTime(df.format(e.getExam_time()));
				svfs.setType(e.getType());
				svfs.setScore(scoreInfos.get(0).getScore());
				svfs.setRankInClass(scoreInfos.get(0).getRanking());
				for (UploadCurriculaInfoVo vo : uci) {
					if (e.gettScCurriculaVariableId().equals(
							vo.getCurriculaVariableId())) {
						svfs.setSubjectName(vo.getSubjectName());
						break;
					}
				}
				svfs.setAverageScore(scoreMapper.avgScore(e.getId())); // 查询平均分
				svfs.setHighestScore(scoreMapper.maxScore(e.getId())); // 查询最高分
				svfs.setLowestScore(scoreMapper.minScore(e.getId())); // 最低分
				scores.add(svfs);
			}
		}
		return scores;
	}

	/**
	 * 教师端获取成绩
	 */
	@Override
	public ScoreInfoResult getScores(String examId, String studentId) {
		ScoreInfoResult result = new ScoreInfoResult();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("examId", examId);
		map.put("studentId", studentId);
		List<ScoreInfo> list = scoreMapper.selectScoreByExamId(map);
		float average = 0;
		for (int i = 0; i < list.size(); i++) {
			average += Float.parseFloat(list.get(i).getScore());
		}
		if (average != 0) {
			average /= list.size(); // 求平均分
			average = (float) (Math.round(average * 10)) / 10; // 保留小数点后一位
			result.setMaxScore(Float.valueOf(list.get(0).getScore())); // 设置最高分
			result.setMinScore(Float.valueOf(list.get(list.size() - 1)
					.getScore())); // 设置最低分
		}
		result.setAverage(average); // 设置平均分
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}

	@Override
	public Score selectByStudentIdAndExamId(String studentId, String examId) {
		return scoreMapper.selectByStudentIdAndExamId(studentId, examId);
	}

	/**
	 * 计算成绩排名方式一:相同分数同一排名，排名连续不中断
	 * 
	 * @param scoreInfos
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<ScoreInfo> sortScore1(List<ScoreInfo> scoreInfos) {

		List<Float> scores = new ArrayList<Float>();
		// 取出成绩不为空的记录
		for (ScoreInfo info : scoreInfos) {
			if (info.getScore() != null) {
				scores.add(Float.parseFloat(info.getScore()));
			}
		}
		Set<Float> set = new HashSet<Float>(scores);// 剔除相同的成绩
		List<Float> list = new ArrayList<Float>(set);
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < scoreInfos.size(); j++) {
				String score = scoreInfos.get(j).getScore();
				if (score != null && !"".equals(score)) {
					float studentScore = Float.parseFloat(score); // 循环取出每个学生的成绩比较
					if (list.get(i) == studentScore) {
						scoreInfos.get(j).setRanking(list.size() - i + "");
					}
				}
			}
		}
		return scoreInfos;
	}

	/**
	 * 成绩排名方式2 idiotic way：相同分数同一排名，排名中断不连续
	 * 
	 * @param scoreInfos
	 * @return
	 */
	private List<ScoreInfo> sortScore2(List<ScoreInfo> scoreInfos) {
		List<Float> studentScores = new ArrayList<Float>();
		List<Float> tempScores = new ArrayList<Float>();
		Map<Float, String> scoreRanking = new HashMap<Float, String>();
		// 取出成绩不为空的记录
		for (ScoreInfo info : scoreInfos) {
			if (info.getScore() != null) {
				studentScores.add(Float.parseFloat(info.getScore()));
				tempScores.add(Float.parseFloat(info.getScore()));
			}
		}

		Set<Float> set = new HashSet<Float>(tempScores);// 剔除相同的成绩
		List<Float> list = new ArrayList<Float>(set);
		Collections.sort(list);
		Collections.sort(studentScores);

		// 先计算成绩对应的排名
		int ranking = 1, temp = 1;
		for (int i = list.size() - 1; i >= 0; i--) {
			for (int j = studentScores.size() - 1; j >= 0; j--) {
				if (studentScores.get(j).equals(list.get(i))) {
					scoreRanking.put(studentScores.get(j), ranking + "");
					studentScores.remove(j);
					temp++;
				} else {
					ranking = temp;
					break;
				}
			}
		}

		for (int j = 0; j < scoreInfos.size(); j++) {
			String score = scoreInfos.get(j).getScore();
			if (score != null && !"".equals(score)) {
				float studentScore = Float.parseFloat(score); // 循环取出每个学生的成绩比较
				for (Float key : scoreRanking.keySet()) {
					if (key == studentScore) {
						scoreInfos.get(j).setRanking(scoreRanking.get(key));
					}
				}
			}
		}
		return scoreInfos;
	}

	@Override
	public boolean deleteByExamId(String examId) {
		return scoreMapper.deleteByExamId(examId) > 0 ? true : false;
	}

	/**
	 * 获取成绩推送的消息格式
	 * @param students
	 * @return
	 */
	private List<OfflineMessageVo> getMessageVoList(List<Student> students) {
		OfflineMessageVo vo = new OfflineMessageVo();
		StringBuilder receiversId = new StringBuilder();
		StringBuilder studentsId = new StringBuilder();
		for (Student student : students) {
			Parent p = parentMapper.selectMainParent(student.getId()); // TODO
																		// 暂时只推送到主家长
			receiversId.append(p.getId()).append(",");
			studentsId.append(student.getId()).append(",");
			vo.setSenderId(" ");
			vo.setContent("您有一条成绩推送未查看,点击查看");
			vo.setMessageType("1");
			vo.setType("4");
			vo.setStatue("1");
			vo.setSendTime(DateFormatUtil.formatDateToSeconds(new Date()));
		}
		vo.setReceiverId(receiversId.toString());
		vo.setStudentId(studentsId.toString());
		return pushMsgService.saveMassPush(vo).getResult();
	}
	
	/**
	 * 
	* @Title: getScoreByExamIdAndClazzId
	* @Description: 根据考试id和班级id查询所有成绩
	* @param @param examId
	* @param @param clazzId
	* @param @return    参数
	* @return ScoreInfoResult    返回类型
	* @throws
	 */
	@Override
	public ScoreInfoResult getScoreByExamIdAndClazzId(String examId,
			String clazzId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("clazzId", clazzId);
		List<ScoreInfo> list = scoreMapper.getScores(map);
		ScoreInfoResult result = new ScoreInfoResult();
		result.setSuccess(true);
		result.setResult(list);
		result.setAverage(ScoreInfoUtil.getAverageScore(list));
		result.setMaxScore(Float.parseFloat(ScoreInfoUtil.getHighestScore(list).get(0).getScore()));
		result.setMinScore(Float.parseFloat(ScoreInfoUtil.getLowestScore(list).get(0).getScore()));
		return result;
	}
	
	/**
	 * 
	* @Title: getScoreByExamIdAndGradeId
	* @Description: 根据考试id和年级id查询所有成绩
	* @param @param examId
	* @param @param gradeId
	* @param @return    参数
	* @return ScoreInfoResult    返回类型
	* @throws
	 */
	@Override
	public ScoreInfoResult getScoreByExamIdAndGradeId(String examId,
			String gradeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examId", examId);
		map.put("gradeId", gradeId);
		List<ScoreInfo> list = scoreMapper.getScoreByExamIdAndGradeId(map);
		ScoreInfoResult result = new ScoreInfoResult();
		result.setSuccess(true);
		result.setResult(list);
		result.setAverage(ScoreInfoUtil.getAverageScore(list));
		result.setMaxScore(Float.parseFloat(ScoreInfoUtil.getHighestScore(list).get(0).getScore()));
		result.setMinScore(Float.parseFloat(ScoreInfoUtil.getLowestScore(list).get(0).getScore()));
		return result;
	}
}
