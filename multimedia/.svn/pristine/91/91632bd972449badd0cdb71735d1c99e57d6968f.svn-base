package com.topview.multimedia.service.section.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.vo.RichTextInfo;
/**
 * 查找全部文章
 * 项目名称:com.topview.multimedia.service.section.article<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class ArticleFindAllProcess implements ArticleProcess {

	@Autowired
	private MultimediaRichTextMapper multimendiaRichTextMapper;

	public boolean doProcess(ArticleProcessContext context) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("t_m_id", context.getInfo().gettMId()); //信息板块的id
			param.put("params", params);
			int pageNumber = context.getInfo().getPager().getPageNumber();
			int pageSize = context.getInfo().getPager().getPageSize();
			param.put("offset",(pageNumber-1)*pageSize);
			param.put("limit", pageSize);
			List<MultimediaRichText> l = multimendiaRichTextMapper.findByMulti(param); //根据信息板块的id分页查询对应的所有文章
			List<RichTextInfo> infos = (RichTextInfo.changeToVo(l));
			context.getResult().setInfoResult(infos);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
