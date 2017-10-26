package com.topview.school.service.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topview.school.dao.feedback.PageViewMapper;
import com.topview.school.dao.feedback.RecordUrlMapper;
import com.topview.school.po.PageView;
import com.topview.school.po.RecordUrl;

@Service
public class PageViewServiceImpl implements PageViewService {

	@Resource
	private PageViewMapper pageViewMapper;
	
	@Resource 
	private RecordUrlMapper recordUrlMapper;

	@Override
	public boolean save(PageView pv) {
		return pageViewMapper.insertSelective(pv) > 0 ? true : false;
	}

	@Override
	public Map<String, Object> getPvCount(String beginTime, String endTime,String schoolId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// resultMap.put("访问量",
		// pageViewMapper.selectVisitCount(beginTime, endTime, null));
		List<RecordUrl> resultList=recordUrlMapper.selectAllKeysAndUrl();
		for(int i=0;i<resultList.size();i++){
			resultMap.put(resultList.get(i).getType(), 
					pageViewMapper.selectVisitCount(beginTime, endTime, resultList.get(i).getUrl(), schoolId));
		}
		resultMap.put("独立用户数",
				pageViewMapper.selectIpCount(beginTime, endTime, null,schoolId));
		/*resultMap.put(
				"校园简介",
				pageViewMapper.selectVisitCount(beginTime, endTime,
						RecordUrl.getRecordUrl("校园简介"),schoolId));
		resultMap.put(
				"校园新闻",
				pageViewMapper.selectVisitCount(beginTime, endTime,
						RecordUrl.getRecordUrl("校园新闻"),schoolId));
		resultMap.put("荣誉之窗",
				pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("荣誉之窗"),schoolId));
		resultMap.put("校园公告",
				pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("校园公告"),schoolId));
		resultMap.put("教育热讯",
				pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("教育热讯"),schoolId));
		resultMap.put("教子学园", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("教子乐园"),schoolId));
		resultMap.put("校园相册", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("校园相册"),schoolId));
		resultMap.put("班级时光相册", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("班级时光相册"),schoolId));
		resultMap.put("视频库", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("视频库"),schoolId));
		resultMap.put("我的短信", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("我的短信"),schoolId));
		resultMap.put("家园桥", pageViewMapper.selectVisitCount(beginTime, endTime,RecordUrl.getRecordUrl("家园桥"),schoolId));
		resultMap.put("教师端学生成绩", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("教师端学生成绩"),schoolId));
		resultMap.put("家长端学生成绩", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("家长端学生成绩"),schoolId));
		resultMap.put("教师端学生评价", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("教师端学生评价"),schoolId));
		resultMap.put("家长端学生评价", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("家长端学生评价"),schoolId));
		resultMap.put("教师端学生健康", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("教师端学生健康"),schoolId));
		resultMap.put("家长端学生健康", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("家长端学生健康"),schoolId));
		resultMap.put("请假申请", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("请假申请"),schoolId));
		resultMap.put("家长圈", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("家长圈"),schoolId));
		resultMap.put("课程表", pageViewMapper.selectVisitCount(beginTime, endTime, RecordUrl.getRecordUrl("课程表"),schoolId));
		*/return resultMap;
	}

}