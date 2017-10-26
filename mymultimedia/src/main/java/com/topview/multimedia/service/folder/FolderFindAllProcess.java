package com.topview.multimedia.service.folder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFolderMapper;
import com.topview.multimedia.po.MultimediaFolder;
import com.topview.multimedia.vo.FolderInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午8:10:10 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FolderFindAllProcess implements FolderProcess {

	@Autowired
	private MultimediaFolderMapper multimendiaFolderMapper;
	private static final Logger logger = Logger
			.getLogger(FolderFindAllProcess.class);

	public boolean doProcess(FolderProcessContext context) {
		try {
			MultimediaFolder Folder = FolderInfo.changeToPo(context.getInfo());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", Folder.gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<MultimediaFolder> l = multimendiaFolderMapper.findByMulti(param);
			context.getResult().setSuccess(true);
			if (l != null) {
				context.getResult().setInfoResult(FolderInfo.changeToVo(l));
			}
			return true;
		} catch (Exception e) {
			logger.error("find all Folder fail" + e.getMessage());
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
