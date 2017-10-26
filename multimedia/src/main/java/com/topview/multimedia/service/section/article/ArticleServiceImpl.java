package com.topview.multimedia.service.section.article;

import java.util.List;

import javax.annotation.Resource;

import com.topview.multimedia.dao.MultimediaRichTextMapper;
import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	private MultimediaRichTextMapper mRichTextMapper;

	private List<ArticleProcess> articleDeleteProcesses;
	private List<ArticleProcess> articleUpdateProcesses;
	private List<ArticleProcess> articleFindProcesses;
	private List<ArticleProcess> articleSendProcesses;
	private List<ArticleProcess> articleFindAllProcesses;

	public RichTextInfoResult articleDelete(RichTextInfo info) {
		return doProcess(info, articleDeleteProcesses);
	}

	public RichTextInfoResult articleUpdate(RichTextInfo info) {
		return doProcess(info, articleUpdateProcesses);
	}

	public RichTextInfoResult articleFind(RichTextInfo info) {
		return doProcess(info, articleFindProcesses);
	}

	public RichTextInfoResult articleSend(RichTextInfo info) {
		return doProcess(info, articleSendProcesses);
	}

	public RichTextInfoResult articleFindAll(RichTextInfo info) {
		return doProcess(info, articleFindAllProcesses);
	}

	public RichTextInfoResult articleSend(RichTextInfo info,
			List<ArticleProcess> processes, String type) {
		ArticleProcessContext context = new ArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		context.setResult(result);
		for (ArticleProcess process : articleSendProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public RichTextInfoResult doProcess(RichTextInfo info,
			List<ArticleProcess> processes) {
		ArticleProcessContext context = new ArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		context.setResult(result);
		context.setInfo(info);
		for (ArticleProcess process : processes) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	public List<ArticleProcess> getArticleDeleteProcesses() {
		return articleDeleteProcesses;
	}

	public void setArticleDeleteProcesses(
			List<ArticleProcess> articleDeleteProcesses) {
		this.articleDeleteProcesses = articleDeleteProcesses;
	}

	public List<ArticleProcess> getArticleUpdateProcesses() {
		return articleUpdateProcesses;
	}

	public void setArticleUpdateProcesses(
			List<ArticleProcess> articleUpdateProcesses) {
		this.articleUpdateProcesses = articleUpdateProcesses;
	}

	public List<ArticleProcess> getArticleFindProcesses() {
		return articleFindProcesses;
	}

	public void setArticleFindProcesses(
			List<ArticleProcess> articleFindProcesses) {
		this.articleFindProcesses = articleFindProcesses;
	}

	public List<ArticleProcess> getArticleSendProcesses() {
		return articleSendProcesses;
	}

	public void setArticleSendProcesses(
			List<ArticleProcess> articleSendProcesses) {
		this.articleSendProcesses = articleSendProcesses;
	}

	public List<ArticleProcess> getArticleFindAllProcesses() {
		return articleFindAllProcesses;
	}

	public void setArticleFindAllProcesses(
			List<ArticleProcess> articleFindAllProcesses) {
		this.articleFindAllProcesses = articleFindAllProcesses;
	}

	public int selectCountByZoneIdAndType(String zoneId, String type) {
		return mRichTextMapper.selectCountByZoneIdAndType(zoneId, type);
	}

	public int getReadNumber(String title, String context) {
		return mRichTextMapper.getReadNumber(title, context);
	}
	
}
