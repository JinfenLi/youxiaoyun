package com.topview.multimedia.service.section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaSectionMapper;
/**
 * 删除文章文类
 * 项目名称:com.topview.multimedia.service.section<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class SectionDeleteProcess implements SectionProcess {

	@Autowired
	private MultimediaSectionMapper multimendiaSectionMapper;

	public boolean doProcess(SectionProcessContext context) {
		try {
			multimendiaSectionMapper.deleteByPrimaryKey(context.getInfo().getId());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
