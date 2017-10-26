package com.topview.multimedia.service.evaluation;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.GroupMapper;
import com.topview.multimedia.dao.StudentGroupMapper;
import com.topview.multimedia.po.Group;
import com.topview.multimedia.po.StudentGroup;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.GroupInfo;


/** * @author  Rachel 
@date 创建时间：2016年9月23日 下午11:57:36 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class GroupSaveProcess implements EvaluationProcess {
	
	@Resource
	private GroupMapper gm;
	@Resource
	private StudentGroupMapper sgm;
	
	private static final Logger logger = Logger.getLogger(GroupSaveProcess.class);

	@Override
	public boolean doProcess(EvaluationProcessContext context) {
		try {
			GroupInfo info = context.getGi();
			StudentGroup sg = new StudentGroup();
			Group group = GroupInfo.changeToPo(info);
			String id = UUIDUtil.getUUID();
			group.setId(id);
			gm.insert(group);
			sg.setGroupId(id);
			List<String> ids = info.getStudentId();
			for(String t :ids){
				sg.setStudentId(t);
				sgm.insert(sg);
			}
			context.getGir().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("save group fail"+e.getMessage());
			e.printStackTrace();
			context.getGir().setSuccess(false);
			return false;
		}
	}

}
