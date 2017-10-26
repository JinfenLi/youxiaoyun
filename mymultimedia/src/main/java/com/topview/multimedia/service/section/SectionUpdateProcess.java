package com.topview.multimedia.service.section;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaSectionMapper;
import com.topview.multimedia.po.MultimediaSection;
import com.topview.multimedia.vo.SectionInfo;
/**
 * 更新文章分类
 * 项目名称:com.topview.multimedia.service.section<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class SectionUpdateProcess implements SectionProcess {

	@Autowired
	private MultimediaSectionMapper multimediaSectionMapper;
	private static final Logger logger = Logger
			.getLogger(SectionUpdateProcess.class);

	public boolean doProcess(SectionProcessContext context) {
		try {
			MultimediaSection section = SectionInfo.changeToPo(context
					.getInfo());
			multimediaSectionMapper.updateByPrimaryKey(section);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			logger.error("update section fail");
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
