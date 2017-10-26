package com.topview.multimedia.service.evaluation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.dao.StudentGroupMapper;

/** * @author  Rachel 
@date 创建时间：2016年10月16日 下午11:19:37 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class GroupDeleteProcess implements EvaluationProcess {
	
	private static final Logger logger = Logger.getLogger(GroupDeleteProcess.class);
	@Resource
	private GroupMapper gm;
	@Resource
	private StudentGroupMapper sm;
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			gm.deleteByPrimaryKey(context.getGi().getId());
			context.getGir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("delete group fail"+e.getMessage());
			e.printStackTrace();
			context.getGir().setSuccess(false);
			return false;
		}
	}

}
