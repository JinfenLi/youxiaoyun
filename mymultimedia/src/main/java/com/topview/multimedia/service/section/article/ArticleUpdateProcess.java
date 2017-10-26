package com.topview.multimedia.service.section.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.vo.RichTextInfo;

/**
 * 文章内容更新 项目名称:com.topview.multimedia.service.section.article<br>
 * 
 * @author houwenjun<br>
 *         创建时间:Apr 11, 2015<br>
 */
@Service
public class ArticleUpdateProcess implements ArticleProcess {

	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(ArticleProcessContext context) {

		try {
			MultimediaRichText richText = RichTextInfo.changeToPo(context
					.getInfo());
			multimediaRichTextMapper.updateByPrimaryKeySelective(richText);
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
