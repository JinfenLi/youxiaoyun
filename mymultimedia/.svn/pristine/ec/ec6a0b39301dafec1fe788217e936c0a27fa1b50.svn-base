package com.topview.multimedia.service.section;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaSectionMapper;
import com.topview.multimedia.po.MultimediaSection;
import com.topview.multimedia.util.UUIDUtil;
import com.topview.multimedia.vo.SectionInfo;
/**
 * 创建文章分类
 * 项目名称:com.topview.multimedia.service.section<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class SectionCreateProcess implements SectionProcess {

	@Autowired
	private MultimediaSectionMapper multimendiaSectionMapper;
	private static final Logger logger = Logger
			.getLogger(SectionCreateProcess.class);

	public boolean doProcess(SectionProcessContext context) {
		try {
			MultimediaSection  section = SectionInfo.changeToPo(context.getInfo());
			section.setCreateTime(new Date());
			section.setId(UUIDUtil.getUUID());
			multimendiaSectionMapper.insert(section);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("create section fail"+e.getMessage());
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
