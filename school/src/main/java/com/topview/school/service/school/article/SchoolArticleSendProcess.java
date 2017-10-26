package com.topview.school.service.school.article;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.record.RecordUpdateService;
import com.topview.multimedia.service.section.SectionService;
import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.SectionInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

@Service
public class SchoolArticleSendProcess implements SchoolArticleProcess {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SectionService sectionService;
	@Resource
	private RecordUpdateService recordUpdateService;

	/**
	 * 1.查询文章所属板块的id设置到RichTextInfo对象中 2.保存文章 3.记录最后更新时间
	 */
	@Override
	public boolean doProcess(SchoolArticleProcessContext context) {
		try {
			SectionInfo sectionInfo = new SectionInfo();
			sectionInfo.settMId(context.getSchoolId());
			sectionInfo.setType(context.getType());
			List<SectionInfo> sections = sectionService.sectionFindByType(
					sectionInfo).getResult(); // 根据多媒体空间id和信息板块类型查询板块信息
			if (sections.size() == 0) {
				return false;
			}
			String tMId = sections.get(0).getId();
			RichTextInfo info = context.getInfo();
			info.settMId(tMId);
			info.setEssence(true);
			info.setStatus(0);
			info.setTop(false);
			info.setReadNumber(0);
			RichTextInfoResult result = articleService.articleSend(info);
			if (result.isSuccess()) {
				recordUpdateService.saveOrUpdateRecord(context.getSchoolId(),
						context.getType()); // 记录所属模块的更新时间
			}
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(result.isSuccess());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
