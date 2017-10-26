package com.topview.school.service.clazz.curricula;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topview.school.dao.curricula.CurriculaAdditionMapper;
import com.topview.school.dao.curricula.CurriculaMapper;
import com.topview.school.dao.curricula.CurriculaTimeMapper;
import com.topview.school.dao.curricula.CurriculaVariableMapper;
import com.topview.school.dao.school.ClazzMapper;
import com.topview.school.dao.school.SemesterMapper;
import com.topview.school.dao.school.SubjectMapper;
import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.po.Curricula;
import com.topview.school.po.CurriculaAddition;
import com.topview.school.po.CurriculaTime;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;
import com.topview.school.vo.curricula.ClassCurriculaInfo;
import com.topview.school.vo.curricula.CurriculaVo;
import com.topview.school.vo.curricula.UploadCurriculaInfoVo;
import com.topview.school.vo.curricula.result.ClassCurriculaInfoResult;
import com.topview.school.vo.school.SemesterVo;

@Service
public class CurriculaServiceImpl implements CurriculaService {

	@Resource
	private CurriculaMapper curriculaMapper;
	@Resource
	private ClazzMapper clazzMapper;
	@Resource
	private CurriculaTimeMapper curriculaTimeMapper;
	@Resource
	private CurriculaVariableMapper curriculaVariableMapper;
	@Resource
	private CurriculaAdditionMapper curriculaAdditionMapper;
	@Resource
	private SubjectMapper subjectMapper;
	@Resource
	private TeacherMapper teacherMapper;
	@Resource
	private SemesterMapper semesterMapper;

	/**
	 * 根据班级id，学期id查询课程表
	 * //TODO
	 */
	@Override
	public ClassCurriculaInfoResult getCurricula(HttpSession session,
			String clazzId, String semesterId) {

		ClassCurriculaInfoResult result = new ClassCurriculaInfoResult();
		List<ClassCurriculaInfo> curriculaInfos = new ArrayList<ClassCurriculaInfo>();
		SemesterVo s = null;
		UserInfo userInfo = (UserInfo) session.getAttribute("currentUser");
		
		if (clazzId == null || "".equals(clazzId)) {
			clazzId = userInfo.getClass_id();
		}
		if(semesterId == null || "".equals(semesterId)) {
			s = semesterMapper.getCurrentSemester(userInfo
					.getSchool_id());
			semesterId = s.getId();
		}
		// 查询课程信息及上课时间信息
		curriculaInfos = clazzMapper.findCurriculaByClassId(clazzId, semesterId);
		// 查询课表备注信息
		CurriculaAddition addition = curriculaAdditionMapper
				.selectBySemesterId(semesterId);
		if (addition != null) {
			result.setComment(addition.getComment());
		} else {
			result.setComment(" ");
		}
		result.setResult(curriculaInfos);
		if (curriculaInfos != null) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 创建课程
	 */
	@Override
	public Curricula createCurricula(Curricula curricula) {
		return curriculaMapper.insertSelective(curricula) > 0 ? curricula
				: null;
	}

	/**
	 * 根据学科id获取课程
	 */
	@Override
	public List<CurriculaVo> getCurriculaBySubjectId(String subjectId) {
		return curriculaMapper.getCurriculaBySubjectId(subjectId);
	}

	/**
	 * 根据课程id删除课程
	 */
	@Override
	public boolean delectCurricula(String curriculaId) {
		try {
			return curriculaMapper.deleteByPrimaryKey(curriculaId) > 0 ? true
					: false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 更新课程信息
	 */
	@Override
	public boolean updateCurricula(Curricula curricula) {
		return curriculaMapper.updateByPrimaryKeySelective(curricula) > 0 ? true
				: false;
	}

	@Override
	public List<Curricula> getCurriculas(Map<String, Object> map) {
		return curriculaMapper.getCurriculas(map);
	}

	/**
	 * 解析上传的课表
	 */
	@Transactional
	@Override
	public boolean uploadCurricula(String fileName, String realPath,
			String clazzId, String semesterId) {

		// 1.根据班级id和学期id确定该班级本学期所上的课程信息,即未开课不能上传该学期的课表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazzId", clazzId);
		map.put("semesterId", semesterId);
		List<UploadCurriculaInfoVo> vo = curriculaVariableMapper
				.selectBySemesterIdAndClazzId(map);
		if (vo.size() == 0) {
			return false;
		} else {
			for (int i = 0; i < vo.size(); i++)
				curriculaTimeMapper.deleteByCurriculaVariableId(vo.get(i)
						.getCurriculaVariableId());// 删除已有的上课时间信息
		}
		// 2.读取excel表内容
		Workbook wb = null;
		Row row;
		try {
			InputStream in = new FileInputStream(new File(realPath));
			POIFSFileSystem fs = new POIFSFileSystem(in);
			wb = new HSSFWorkbook(fs); // 创建工作簿对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		Sheet sheet = wb.getSheetAt(0); // 获取第一个Sheet页
		for (int i = 1; i <= 8; i++) { // 遍历行：第几节
			for (int j = 1; j <= 6; j++) { // 遍历列：周几
				row = sheet.getRow(i);
				Cell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				String content = cell.getStringCellValue();
				// 遍历匹配科目名
				for (UploadCurriculaInfoVo info : vo) {
					if (info.getSubjectName().equals(content)) {
						CurriculaTime ct = new CurriculaTime();
						ct.setId(UUIDUtil.getUUID());
						ct.settScCurriculaVariableId(info
								.getCurriculaVariableId());
						ct.setWeek(j);
						ct.setSection(i);
						try {
							curriculaTimeMapper.insert(ct);
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException();
						}
						break;
					}
				}
			}
		}
		// 处理课表备注信息
		String comment = sheet.getRow(9).getCell(1).getStringCellValue();
		CurriculaAddition addition = curriculaAdditionMapper
				.selectBySemesterId(semesterId);
		if (addition != null) {
			if (comment != null && !"".equals(comment)) {
				addition.setComment(comment);
				curriculaAdditionMapper.updateByPrimaryKey(addition);
			}
		} else {
			CurriculaAddition ca = new CurriculaAddition();
			ca.setComment(comment);
			ca.setSemesterId(semesterId);
			curriculaAdditionMapper.insertSelective(ca);
		}
		return true;
	}

	@Override
	public Curricula selectByPrimaryKey(String id) {
		return curriculaMapper.selectByPrimaryKey(id);
	}
}
