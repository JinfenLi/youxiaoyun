package com.topview.multimedia.service.evaluation;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.Evaluation;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.EvaluationInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月20日 下午9:18:01 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class EvaluationSaveProcess implements EvaluationProcess {

	@Resource
	private EvaluationMapper em;
	@Resource
	private EvaluationTemplateMapper etm;
	
	private static final Logger logger = Logger.getLogger(EvaluationSaveProcess.class);
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			
			Evaluation evaluation = EvaluationInfo.changeToPo(context.getInfo());
			if(context.getInfo().getTemplateId()!=null&&!"".equals(context.getInfo().getTemplateId())){
				int score = etm.selectByPrimaryKey(context.getInfo().getTemplateId()).getScore();
				evaluation.setScore(score);
			}
			evaluation.setId(UUIDUtil.getUUID());
			evaluation.setCreateTime(new Date());
			em.insert(evaluation);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("save evaluation fail"+e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
