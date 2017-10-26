package com.topview.school.service.school.article;

import java.util.List;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;

public class SchoolArticleServiceImpl implements SchoolArticleService {

	private List<SchoolArticleProcess> schoolArticleFindAllProcesses;
	private List<SchoolArticleProcess> schoolArticleFindProcesses;
	private List<SchoolArticleProcess> schoolArticleDeleteProcesses;
	private List<SchoolArticleProcess> schoolArticleSendProcesses;
	private List<SchoolArticleProcess> schoolArticleCopyProcesses;

	/**
	 * 获得对应部分所有的文章
	 */
	@Override
	public RichTextInfoResult getAllArticle(String schoolId, String type,
			Pager pager) {
		SchoolArticleProcessContext context = new SchoolArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		RichTextInfo info = new RichTextInfo();
		info.setPager(pager);
		context.setSchoolId(schoolId);
		context.setType(type);
		context.setInfo(info);
		context.setResult(result);
		for (SchoolArticleProcess process : schoolArticleFindAllProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 获得文章内容
	 */
	@Override
	public RichTextInfoResult getArticleById(String articleId) {
		SchoolArticleProcessContext context = new SchoolArticleProcessContext();
		RichTextInfo info = new RichTextInfo();
		RichTextInfoResult result = new RichTextInfoResult();
		info.setId(articleId);
		context.setInfo(info);
		context.setResult(result);
		for (SchoolArticleProcess process : schoolArticleFindProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 
	 * 删除文章
	 */
	public RichTextInfoResult articleDelete(String articleId) {
		SchoolArticleProcessContext context = new SchoolArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		RichTextInfo info = new RichTextInfo();
		info.setId(articleId);
		context.setInfo(info);
		context.setResult(result);
		for (SchoolArticleProcess process : schoolArticleDeleteProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}

	/**
	 * 保存文章
	 */
	public RichTextInfoResult articleSend(String schoolId, String type,
			String content, String title, String path, String createTime) {
		SchoolArticleProcessContext context = new SchoolArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		RichTextInfo info = new RichTextInfo();
		info.setContext(content);
		info.setZoneId(schoolId);
		info.setType(type);
		info.setTitlephoto(path);
		info.setTitle(title);
		info.setCreateTime(createTime);
		context.setSchoolId(schoolId);
		context.setType(type);
		context.setInfo(info);
		context.setResult(result);
		for (SchoolArticleProcess process : schoolArticleSendProcesses) {
			if (!process.doProcess(context)) {
				break;
			}
		}
		return context.getResult();
	}
	
	/**
	 * 复制新闻到指定的多个学校或多个年级
	 */
	public RichTextInfoResult articleCopy(String articleId, String zoneId, String type) {
		SchoolArticleProcessContext context = new SchoolArticleProcessContext();
		RichTextInfoResult result = new RichTextInfoResult();
		RichTextInfo info = new RichTextInfo();
		info.setId(articleId);
		context.setInfo(info);
		context.setResult(result);
		schoolArticleCopyProcesses.get(0).doProcess(context);
		
		SchoolArticleProcessContext context2 = new SchoolArticleProcessContext();
		RichTextInfoResult result2 = new RichTextInfoResult();
		RichTextInfo info2 = new RichTextInfo();
		info2.setContext(context.getResult().getInfoResult().get(0).getContext());
		info2.setTitlephoto(context.getResult().getInfoResult().get(0).getTitlephoto());
		info2.setTitle(context.getResult().getInfoResult().get(0).getTitle());
		info2.setCreateTime(context.getResult().getInfoResult().get(0).getCreateTime());
		info2.setType(type);
		context2.setSchoolId(zoneId);
		context2.setType(type);
		context2.setInfo(info2);
		context2.setResult(result2);
		schoolArticleCopyProcesses.get(1).doProcess(context2);
		return context2.getResult();
	}

	public List<SchoolArticleProcess> getSchoolArticleFindProcesses() {
		return schoolArticleFindProcesses;
	}

	public void setSchoolArticleFindProcesses(
			List<SchoolArticleProcess> schoolArticleFindProcesses) {
		this.schoolArticleFindProcesses = schoolArticleFindProcesses;
	}

	public List<SchoolArticleProcess> getSchoolArticleFindAllProcesses() {
		return schoolArticleFindAllProcesses;
	}

	public void setSchoolArticleFindAllProcesses(
			List<SchoolArticleProcess> schoolArticleFindAllProcesses) {
		this.schoolArticleFindAllProcesses = schoolArticleFindAllProcesses;
	}

	public List<SchoolArticleProcess> getSchoolArticleDeleteProcesses() {
		return schoolArticleDeleteProcesses;
	}

	public void setSchoolArticleDeleteProcesses(
			List<SchoolArticleProcess> schoolArticleDeleteProcesses) {
		this.schoolArticleDeleteProcesses = schoolArticleDeleteProcesses;
	}

	public List<SchoolArticleProcess> getSchoolArticleSendProcesses() {
		return schoolArticleSendProcesses;
	}

	public void setSchoolArticleSendProcesses(
			List<SchoolArticleProcess> schoolArticleSendProcesses) {
		this.schoolArticleSendProcesses = schoolArticleSendProcesses;
	}

	public List<SchoolArticleProcess> getSchoolArticleCopyProcesses() {
		return schoolArticleCopyProcesses;
	}

	public void setSchoolArticleCopyProcesses(
			List<SchoolArticleProcess> schoolArticleCopyProcesses) {
		this.schoolArticleCopyProcesses = schoolArticleCopyProcesses;
	}

}
