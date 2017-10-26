package com.topview.multimedia.service.folder.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaFileMapper;
import com.topview.multimedia.po.MultimediaFile;
import com.topview.multimedia.vo.FileInfo;

/** * @author  Rachel 
@date 创建时间：2016年7月29日 下午9:23:53 * 
@version 1.0 * 
@parameter  *
 @since  * 
@return  */
@Service
public class FileFindAllProcess implements FileProcess {

	private static final Logger logger = Logger
			.getLogger(FileFindAllProcess.class);
	@Autowired
	private MultimediaFileMapper multimediaFileMapper;

	public boolean doProcess(FileProcessContext context) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_ID", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			if(context.getInfo().getPager()!=null){
				int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			}
			
			List<MultimediaFile> l = multimediaFileMapper.findByMulti(param);
			List<FileInfo> list = FileInfo.changeToVo(l);
			context.getResult().setInfoResult(list);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("find files fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
