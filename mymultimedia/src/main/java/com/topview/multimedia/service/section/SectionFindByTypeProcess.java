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
 * 根据多媒体空间id和信息板块类型查找信息板块
 * 项目名称:com.topview.multimedia.service.section<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class SectionFindByTypeProcess implements SectionProcess {

	@Autowired
	private MultimediaSectionMapper multimendiaSectionMapper;

	public boolean doProcess(SectionProcessContext context) {
		try {
			Map<String , Object> params = new HashMap<String, Object>();
			Map<String, Object> param = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId()); //信息板块的t_m_id
			params.put("type", context.getInfo().getType()); //信息板块中的类型，如新闻、公告、教育热讯
			param.put("params", params);
			List<MultimediaSection> l = multimendiaSectionMapper.findByMulti(param); //拿到对应班或对应学校指定类型的信息板块
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
