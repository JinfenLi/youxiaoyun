package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.po.Evaluation;

/** * @author  Rachel 
@date 创建时间：2016年10月18日 下午9:50:04 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class EvaluationJudgeByGroupProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(EvaluationJudgeByGroupProcess.class);
	
	@Resource
	private EvaluationMapper em;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			Evaluation e = em.selectByPrimaryKey(context.getInfo().getId());
			if(e.getGroupId()!=null&&!"".equals(e.getGroupId())){
				return true;
			}
		} catch (Exception e) {
			logger.error("find group evaluation fail"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
