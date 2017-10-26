package com.topview.multimedia.service.evaluation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationMapper;
import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.po.Evaluation;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.vo.EvaluationInfo;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月25日 下午9:59:59 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class EvaluationFindByStudentProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(EvaluationFindByStudentProcess.class);
	@Resource
	private EvaluationMapper em;
	@Resource
	private EvaluationTemplateMapper etm;
	@Resource
	private GroupMapper gm;

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			Evaluation evaluation = EvaluationInfo.changeToPo(context.getInfo());
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("student_id",evaluation.getStudentId());
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("params", params);
			param.put("startTime", context.getInfo().getStartTime());
			param.put("endTime", context.getInfo().getEndTime());
			List<Evaluation> el = em.findByMulti(param);
			List<EvaluationInfo> ell = EvaluationInfo.changeToVo(el);
			if(ell!=null&&ell.size()>0){
					for(EvaluationInfo ei : ell){
					if(ei.getTemplateId()!=null&&!"".equals(ei.getTemplateId())){
						EvaluationTemplate et = etm.selectByPrimaryKey(ei.getTemplateId());
						if(et!=null){
							ei.setTemplateInfo(EvaluationTemplateInfo.changeToVo(et));
						}else{
							context.getResult().setSuccess(false);
							return false;
						}
					}
					if(ei.getGroupId()!=null&&!"".equals(ei.getGroupId())){
						ei.setGroupName(gm.selectByPrimaryKey(ei.getGroupId()).getName());
					}
				}
			}
			context.getResult().setInfoResult(ell);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("find student evalution fail"+e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
