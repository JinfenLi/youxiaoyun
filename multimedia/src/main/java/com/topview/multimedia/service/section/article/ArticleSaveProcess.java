package com.topview.multimedia.service.section.article;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.po.MultimediaRichText;
import com.topview.multimedia.vo.RichTextInfo;
/**
 * 保存文章
 * 项目名称:com.topview.multimedia.service.section.article<br>
 * 
 * @author houwenjun<br>
 * 创建时间:Apr 11, 2015<br>
 */
@Service
public class ArticleSaveProcess implements ArticleProcess {

	@Autowired
	private MultimediaRichTextMapper multimediaRichTextMapper;

	public boolean doProcess(ArticleProcessContext context) {

		try {
			MultimediaRichText richText = RichTextInfo.changeToPo(context.getInfo());
			if(richText.getType() == 2) { //如果是校园简介则将发布时间设置为当前时间,这样可以不必去删除旧的校园简介信息
				richText.setCreateTime(new Date());
			}
			multimediaRichTextMapper.insert(richText);
			context.getInfo().setId(richText.getId());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
