package com.topview.multimedia.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.dao.base.impl.BaseDaoImpl;
import com.topview.multimedia.po.MultimediaRichText;

/**
 * 文章DAO实现 项目名称:com.topview.multimedia.dao.impl<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Repository
public class MultimediaRichTextDaoImpl extends BaseDaoImpl<MultimediaRichText>
		implements MultimediaRichTextMapper {

	public int selectCountByZoneIdAndType(String zoneId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zoneId", zoneId);
		map.put("type", type);
		return template.selectOne(getFirstInterface()
				+ ".selectCountByZoneIdAndType", map);
	}

}
