/**
 * @Description: 
 * @author chenzufeng
 * @date  2015年8月4日 下午7:42:04 
 * @version V1.0
 */
package com.topview.school.service.appraise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.school.dao.appraise.AppraiseMapper;
import com.topview.school.po.Appraise;
import com.topview.school.vo.appraise.AppraiseInfo;

/** 
 * @ClassName: AppraiseFindByPartentProcess 
 * @Description: TODO 
 * @author Chenzufeng 1023284613@qq.com
 * @date 2015年8月4日 下午7:42:04 
 *  
 */
@Service
public class AppraiseFindByPartentProcess implements AppraiseProcess{

	@Autowired
	private AppraiseMapper appraiseMapper;
	private static final Logger logger = Logger
			.getLogger(AppraiseFindByPartentProcess.class);
	
	
	
	@Override
	public boolean doProcess(AppraiseProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("student_id", context.getInfo().getStudentId());
			params.put("semester_id", context.getInfo().getSemester());
     		params.put("stage", context.getInfo().getStage());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);	
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<Appraise>  list = appraiseMapper.findByMulti(param);
     		context.getResult().setSuccess(true);
			if(list!=null) {
			List<AppraiseInfo> infos = AppraiseInfo.changeToVo(list);
			context.getResult().setInforesult(infos);
			}
			return true;
			
		}catch(Exception e) {
			logger.error("find Appraise error");
			context.getResult().setSuccess(false);
			
			return false;
		}
	}

	
	
}
