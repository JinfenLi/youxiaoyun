package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.Date;



import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.po.EvaluationTemplate;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.EvaluationTemplateInfo;

/** * @author  Rachel 
@date 创建时间：2016年9月21日 下午9:22:19 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class EvaluationTemplateSaveProcess implements EvaluationProcess {

	@Resource
	private EvaluationTemplateMapper etm;
	
	private static final Logger logger = Logger.getLogger(EvaluationTemplateSaveProcess.class);
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			EvaluationTemplate et = EvaluationTemplateInfo.changeToPo(context.getEti());
			List<EvaluationTemplateInfo> list = new ArrayList<EvaluationTemplateInfo>();
			EvaluationTemplateInfo info = new EvaluationTemplateInfo();
			String id = UUIDUtil.getUUID();
			et.setId(id);
			et.setCreateTime(new Date());
			etm.insert(et);
			info.setId(id);
			list.add(info);
			context.getEtir().setInfoResult(list);
			context.getEtir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("save evaluationtemplate fail"+e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
