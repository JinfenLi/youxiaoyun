package com.topview.school.service.school.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topview.multimedia.service.section.SectionService;
import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.SectionInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

@Service
public class SchoolArticleFindAllProcess implements SchoolArticleProcess {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SectionService sectionService;

	@Override
	public boolean doProcess(SchoolArticleProcessContext context) {
		try {
			SectionInfo sectionInfo = new SectionInfo();
			sectionInfo.settMId(context.getSchoolId());
			sectionInfo.setType(context.getType());
			List<SectionInfo> sectionInfos = sectionService.sectionFindByType(
					sectionInfo).getResult();
			if (sectionInfos.size() > 0) {
				context.getInfo().settMId(sectionInfos.get(0).getId());
			} else {
				return false;
			}
			RichTextInfoResult result = articleService.articleFindAll(context
					.getInfo()); // 根据信息板块的id分页查询对应的所有的文章
			context.getResult().setInfoResult(result.getInfoResult());
			context.getResult().setSuccess(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			context.getResult().setSuccess(false);
			return false;
		}
	}

}
