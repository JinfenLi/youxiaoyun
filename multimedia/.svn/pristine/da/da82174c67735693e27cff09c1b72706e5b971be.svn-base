package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.vo.EvaluationInfo;

/** * @author  Rachel 
@date 创建时间：2016年10月17日 下午11:23:37 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class StuTotalScoreProcess implements EvaluationProcess {
	private static final Logger logger = Logger.getLogger(GroupUpdateProcess.class);
	@Resource
	private EvaluationMapper em;
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			EvaluationInfo info = context.getInfo();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("startTime", info.getStartTime());
			map.put("endTime", info.getEndTime());
			map.put("studentId", info.getStudentId());
			Integer stuscore = em.getStudentScore(map);
			info.setStuscore(stuscore);
			List<EvaluationInfo> list = new ArrayList<EvaluationInfo>();
			list.add(info);
			context.getResult().setInfoResult(list);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("get stu total score fail"+e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
