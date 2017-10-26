package com.topview.multimedia.service.folder;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.po.MultimediaFolder;
import com.topview.multimedia.vo.FolderInfo;

/** * 
 * 创建文件夹
 * @author  Rachel 
@date 创建时间：2016年7月29日 下午5:58:38 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */

@Service
public class FolderCreateProcess implements FolderProcess {
	
	@Autowired
	private MultimediaFolderMapper multimendiaFolderMapper;
	private static final Logger logger = Logger
			.getLogger(FolderCreateProcess.class);

	@Override
	public boolean doProcess(FolderProcessContext context) {
		try {
			MultimediaFolder folder = FolderInfo.changeToPo(context.getInfo());
			folder.setCreateTime(new Date());
			folder.setUpdateTime(new Date()); 
			folder.setFileCount(0);
			if (multimendiaFolderMapper.insert(folder) > 0) {
				context.getResult().setSuccess(true);
			} else {
				context.getResult().setSuccess(false);
			}
			return true;
		} catch (Exception e) {
			logger.error("create folder fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
