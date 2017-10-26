package com.topview.multimedia.service.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.dao.StudentGroupMapper;
import com.topview.multimedia.po.Group;
import com.topview.multimedia.po.StudentGroup;
import com.topview.multimedia.vo.GroupInfo;

/** * @author  Rachel 
@date 创建时间：2016年10月17日 下午10:16:46 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class GroupUpdateProcess implements EvaluationProcess {
	private static final Logger logger = Logger.getLogger(GroupUpdateProcess.class);
	@Resource
	private GroupMapper gm;
	@Resource
	private StudentGroupMapper sgm;
	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			Group group = GroupInfo.changeToPo(context.getGi());
			GroupInfo info = context.getGi();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("group_id", info.getId());
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("params", params);
			List<StudentGroup> groupList = sgm.findByMulti(param);
			gm.updateByPrimaryKeySelective(group);
			StudentGroup sg = new StudentGroup();
			sg.setGroupId(group.getId());
			List<String> ids = info.getStudentId();
			List<String> groupListIds = new ArrayList<String>();
			for(StudentGroup s : groupList){
				groupListIds.add(s.getStudentId());
			}
			for(String t : ids){
				if(!groupListIds.contains(t)){
					sg.setStudentId(t);
					sgm.insert(sg);
				}
			}
			for(String t : groupListIds){
				if(!ids.contains(t)){
					sg.setStudentId(t);
					sgm.deleteByPo(sg);
				}
			}
			context.getGir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("group update fail"+e.getMessage());
			e.printStackTrace();
			context.getGir().setSuccess(false);
			return false;
		}
	}

}
