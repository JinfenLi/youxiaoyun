package com.topview.school.service.school.article;

import java.util.Date;
import java.util.List;

import com.topview.message.service.JPushServiceImpl;
import com.topview.message.util.DateFormatUtil;
import com.topview.message.vo.OfflineMessageVo;
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
		info.setReadNumber(0);
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
		
			// 如果判断类型在类型范围内
			if(this.typeJudge(type).equals("false")==false){
				//保存校园公告或者班级新闻后，根据校园或班级Id发出推送
				OfflineMessageVo message = new OfflineMessageVo();
				message.setReceiverId(schoolId);
				message.setContent(context.getInfo().getId());
				
				message.setType(this.typeJudge(type));
				message.setSendTime(DateFormatUtil.formatDateToSeconds (new Date()));
				message.setStatue("2");//群发消息作为已发送，不保存到数据库，不关心是否收到
				JPushServiceImpl pushService = new JPushServiceImpl();
				
				//向ios端发送通知
				pushService.sendNoticeToTag(schoolId, "您有一条新的" + this.typeJudge(type) + "，请点击查看", "ios");
				
				//向所有平台发送推送
				pushService.sendMultiMessageToTag(message.toJsonString(), schoolId, null);
				System.out.println(message.toJsonString());
				
			}	
		return context.getResult();
	}
	
	/**
	 * 该方法用于判断添加到推送vo中的推送是什么类型，判断并返回对应的推送类型。
	 * @param type
	 * @return
	 */
	private String typeJudge(String type){
			if(type.equals("notice")){
				return "校园公告";
			}
			else if(type.equals("educate")){
				return "教子乐园";
			}
			else if(type.equals("news")){
				return "校园新闻";
			}
			else if(type.equals("reward")){
				return "荣誉之窗";
			}
			else if(type.equals("teaching")){
				return "教育热讯";
			}
			return "false";
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
