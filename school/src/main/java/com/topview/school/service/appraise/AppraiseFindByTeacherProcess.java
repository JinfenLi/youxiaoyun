/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月3日 下午10:03:22 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.po.Appraise;
import com.topview.school.vo.appraise.AppraiseInfo;

/** 
 * @ClassName: AppraiseFindByTeacherProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月3日 下午10:03:22 
 *  
 */
@Service
public class AppraiseFindByTeacherProcess implements AppraiseProcess{

	@Resource
	private AppraiseMapper appraiseMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseFindByTeacherProcess.class);
	
	@Override
	public boolean doProcess(AppraiseProcessContext context) {
		try {
     		Map<String, Object> params = new HashMap<String, Object>();
     		params.put("teacher_id", context.getInfo().getTeacherId());
     		if(context.getInfo().getGclass()!=null)
     		params.put("class_id", context.getInfo().getGclass());
     		params.put("semester_id", context.getInfo().getSemester());
     		params.put("stage", context.getInfo().getStage());
     		params.put("type", context.getInfo().getType());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);	
			List<Appraise>  list = appraiseMapper.getappraisesByIDCard(params);
     		context.getResult().setSuccess(true);
			if(list!=null) {
			List<AppraiseInfo> infos = AppraiseInfo.changeToVo(list);
			context.getResult().setInforesult(infos);
			}
			return true;
		} catch (Exception e) {
			logger.error("find Appraise error");
			context.getResult().setSuccess(false);
			return false;
		}
	
	}
}
