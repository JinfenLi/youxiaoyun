package com.topview.multimedia.service.folder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.dao.MultimediaFolderMapper;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午7:26:59 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FolderDeleteProcess implements FolderProcess {

	@Autowired
	private MultimediaFolderMapper multimendiaFolderMapper;
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;

	private static final Logger logger = Logger
			.getLogger(FolderDeleteProcess.class);

	public boolean doProcess(FolderProcessContext context) {
		try {
			String tMId = context.getInfo().getId();
			multimediaFileMapper.deleteByTmid(tMId); 
			if(multimendiaFolderMapper.deleteByPrimaryKey(tMId) > 0) {
				context.getResult().setSuccess(true);
			} else {
				context.getResult().setSuccess(false);
			}
			return true;
		} catch (Exception e) {
			logger.error("delete Folder fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
