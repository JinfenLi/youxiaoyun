package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.EvaluationTemplateMapper;
import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.dao.StudentGroupMapper;
import com.topview.multimedia.po.Group;
import com.topview.multimedia.po.StudentGroup;
import com.topview.multimedia.vo.GroupInfo;

/** * @author  Rachel 
@date 创建时间：2016年10月16日 下午2:04:09 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class StudentFindByGroupIdProcess implements EvaluationProcess{
	
	private static final Logger logger = Logger.getLogger(StudentFindByGroupIdProcess.class);
	@Resource
	private GroupMapper gm;
	@Resource
	private StudentGroupMapper sgm;
	@Resource
	private EvaluationTemplateMapper etm;
	
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			Group group = GroupInfo.changeToPo(context.getGi());
			Integer score = etm.selectByPrimaryKey(context.getGi().getTemplateId()).getScore();
			if(gm.selectByPrimaryKey(group.getId()).getScore()!=null){
				score = gm.selectByPrimaryKey(group.getId()).getScore()+score;
			}
			group.setScore(score);
			gm.updateByPrimaryKeySelective(group);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", group.getId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			List<Group> gl = gm.findByMulti(param);
			List<GroupInfo> gil = GroupInfo.changeToVo(gl);
			for(GroupInfo g : gil){
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("group_id", g.getId());
				Map<String, Object> param1 = new HashMap<String, Object>();
				param1.put("params", params1);
				List<StudentGroup> list = sgm.findByMulti(param1);
				List<String> ids = new ArrayList<String>();
			    for(StudentGroup sg : list){
			    	ids.add(sg.getStudentId());
			    }
			    g.setStudentId(ids);
			}
			context.getGir().setInfoResult(gil);
			context.getGir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("get studentid by groupid fail"+e.getMessage());
			e.printStackTrace();
			context.getGir().setSuccess(false);
			return false;
		}
	}

}