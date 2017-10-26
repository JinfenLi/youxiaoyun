package com.topview.multimedia.service.folder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.po.MultimediaFolder;
import com.topview.multimedia.vo.FolderInfo;
import com.topview.multimedia.vo.result.FolderInfoResult;

/** * 更新文件夹
 * @author  Rachel 
@date 创建时间：2016年7月29日 下午7:12:39 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service 
public class FolderUpdateProcess implements FolderProcess {

	@Autowired
	private MultimediaFolderMapper multimendiaFolderMapper;
	private static final Logger logger = Logger
			.getLogger(FolderUpdateProcess.class);

	public boolean doProcess(FolderProcessContext context) {
		FolderInfoResult result = new FolderInfoResult();
		try {
			MultimediaFolder folder = FolderInfo.changeToPo(context.getInfo());
			if(multimendiaFolderMapper.updateByPrimaryKeySelective(folder) > 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
			context.setResult(result);
			return true;
		} catch (Exception e) {
			logger.error("update folder fail" + e.getMessage());
			e.printStackTrace();
			result.setSuccess(false);
			context.setResult(result);
			return false;
		}
	}

}
