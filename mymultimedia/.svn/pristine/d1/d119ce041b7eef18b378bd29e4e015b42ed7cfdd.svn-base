package com.topview.multimedia.service.section;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaSectionMapper;
import com.topview.multimedia.po.MultimediaSection;
import com.topview.multimedia.vo.SectionInfo;
/**
 * 查找全部文章分类
 * 项目名称:com.topview.multimedia.service.section<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class SectionFindAllProcess implements SectionProcess {

	@Autowired
	private MultimediaSectionMapper multimendiaSectionMapper;

	public boolean doProcess(SectionProcessContext context) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("params", params);
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<MultimediaSection> l = multimendiaSectionMapper.findByMulti(param);
			List<SectionInfo> infos = SectionInfo.changeToVo(l);
			context.getResult().setResult(infos);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
