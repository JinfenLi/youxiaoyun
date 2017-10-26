package com.topview.multimedia.service.section.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaCollectMapper;
import com.topview.multimedia.dao.MultimediaRichTextMapper;

/**
 * 删除文章 项目名称:com.topview.multimedia.service.section.article<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class ArticleDeleteProcess implements ArticleProcess {
	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;
	@Autowired
	private MultimediaCollectMapper multimediaCollectMapper;

	public boolean doProcess(ArticleProcessContext context) {

		try {
			multimediaCollectMapper.deleteByPrimaryKey(context.getInfo()
					.getId());
			multimediaRichTextMapper.deleteByPrimaryKey(context.getInfo()
					.getId());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
