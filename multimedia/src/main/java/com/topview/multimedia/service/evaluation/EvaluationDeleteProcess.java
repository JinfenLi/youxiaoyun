package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.po.Evaluation;
import com.topview.multimedia.po.Group;

/** * @author  Rachel 
@date 创建时间：2016年10月17日 下午9:24:52 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class EvaluationDeleteProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(EvaluationDeleteProcess.class);
	@Resource
	private EvaluationMapper em;
	@Resource
	private GroupMapper gm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			Evaluation e = em.selectByPrimaryKey(context.getInfo().getId());
			if(e.getGroupId()!=null&&!"".equals(e.getGroupId())){
				Group g = gm.selectByPrimaryKey(e.getGroupId());
				Integer score = g.getScore()-e.getScore();
				g.setScore(score);
				gm.updateByPrimaryKeySelective(g);
			}
			em.deleteByPrimaryKey(context.getInfo().getId());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("delete evaluation fail"+e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
