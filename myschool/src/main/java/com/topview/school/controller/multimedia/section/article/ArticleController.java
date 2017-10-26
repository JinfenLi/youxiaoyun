package com.topview.school.controller.multimedia.section.article;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topview.multimedia.bean.Pager;
import com.topview.multimedia.service.section.article.ArticleService;
import com.topview.multimedia.vo.RichTextInfo;
import com.topview.multimedia.vo.result.RichTextInfoResult;
import com.topview.school.service.school.article.SchoolArticleService;
import com.topview.school.util.ClassLoaderUtil;
import com.topview.school.util.HtmlUtil;
import com.topview.school.util.JSONUtil;

@Controller
@RequestMapping(value = "/article", produces = "text/html;charset=UTF-8")
public class ArticleController {

	@Autowired
	private SchoolArticleService schoolArticleService;
	@Resource
	private ArticleService articleService;

	/**
	 * 校园模块的校园新闻和校园公告和荣誉之窗和教子学园和班级模块的教子良方第一层入口
	 * 
	 * @param type
	 *            news(校园新闻)、notice(校园公告)、reward(荣誉之窗)、teaching(教子学园、教子良方)、
	 *            educate(教子良方)
	 * @param pager
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllArticle")
	public String getAllArticle(HttpSession session, String schoolId,
			String gradeId, String type, Pager pager,
			HttpServletRequest request, Integer start, Integer limit) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String filter[] = { "essence", "context", "pager", "status",
				"subtitle", "summary", "tMId", "top", "zoneId", "collectid" };

		if ((schoolId == null || "".equals(schoolId))
				&& (gradeId == null || "".equals(gradeId))) { // 安卓传参不稳定临时做处理
			return HtmlUtil
					.toHtml("<center><h3>很抱歉,您的网络好像有点问题...<br/>404Not Found!</h3></center>");
		}
		// 如果是班级模块的教子良方，将年级id赋值给学校id
		if ("educate".equals(type)) {
			schoolId = gradeId;
		}
		if (start != null && !"".equals(start) && limit != null
				&& !"".equals(limit)) {
			pager.setPageSize(limit);
			pager.setPageNumber(start / limit + 1);
		}

		List<RichTextInfo> infos = schoolArticleService.getAllArticle(schoolId, // schoolId对应的是t_mutimedia_mutil_media_zone中的id
				type, pager).getInfoResult();
		if (infos != null && infos.size() >= 0) {
			for (RichTextInfo info : infos) {
				info.setUrl(request.getContextPath()
						+ "/article/getArticle.action?articleId="
						+ info.getId());
			}
		}
		resultMap.put("success", true);
		resultMap.put("articles", infos);
		resultMap.put("totalCount",
				articleService.selectCountByZoneIdAndType(schoolId, type));
		return JSONUtil.toObject(resultMap, filter).toString();
	}

	/**
	 * 校园新闻和校园公告和荣誉之窗和教子学园第二层入口，获取具体的新闻内容和公告内容
	 * 
	 * @param articleId
	 *            信息板块id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getArticle")
	public String getArticle(String articleId) {
		return schoolArticleService.getArticleById(articleId).getInfoResult()
				.get(0).getContext();
	}

	/**
	 * 校园简介
	 * 
	 * @param schoolId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getIntroduction")
	public String getIntroduction(String schoolId) {
		List<RichTextInfo> infos = schoolArticleService.getAllArticle(schoolId,
				"summy", new Pager()).getInfoResult();
		if (infos != null && infos.size() > 0) {
			String introduction = infos.get(0).getContext();
			return HtmlUtil.toHtml(introduction);
		} else {
			return HtmlUtil.toHtml("<center>尚无相关校园介绍信息</center>");
		}
	}

	/**
	 * 保存文章内容
	 * 
	 * @param session
	 * @param type
	 *            文章类型，如news(校园新闻)、notice（校园公告）
	 * @param content
	 *            文章内容
	 * @param title
	 *            文章标题
	 * @param path
	 *            图片URL，由前端保存返回路径给后台
	 * @return
	 */
	// TO 逻辑、结构混乱
	@ResponseBody
	@RequestMapping("/sendArticle")
	public String sendArticle(HttpSession session, String type, String content,
			String title, String path, String schoolId, String gradeId,
			String createTime) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", false);
		// 1.处理传入的图片生成缩略图
		String projectName = session.getServletContext().getContextPath(); // 获取项目名
		int index = path.lastIndexOf("."); // 最后一个小数点的位置
		String fileType = path.substring(index, path.length()); // 截取图片类型
		String orignalPath = path; // 原图相对路径
		String titlePath = path.replaceAll("ueditorImg", "thumb"); // 缩略图的相对路径（使用缩略图作为文章标题图片）
		// 因为要获取到的图片资源不在项目中，所以要通过绝对路径去获取图片资源
		try {
			path = ClassLoaderUtil.getExtendResource("../" + path, projectName)
					.toString(); // 获取项目外部资源的绝对路径
		} catch (MalformedURLException e) {
			e.printStackTrace();
			resultMap.put("msg", "系统错误，请联系管理员");
			return JSONUtil.toObject(resultMap).toString();
		}
		String resultPath = path.replaceAll("ueditorImg", "thumb"); // 缩略图保存绝对路径
		if (!scale(path, resultPath, 3, fileType)) { // 如果生成缩略图失败则使用原图作为标题图片
			titlePath = orignalPath;
		}

		RichTextInfoResult result = new RichTextInfoResult();
		if ("educate".equals(type) && gradeId != null && !"".equals(gradeId)) { // 属于年级的教子学园
			result = schoolArticleService.articleSend(gradeId, type, content,
					title, titlePath, createTime);
		} else if ("teaching".equals(type)) { // 属于多个学校的教育热讯
			String[] schoolsId = schoolId.split(",");
			for (int i = 0; i < schoolsId.length; i++) {
				result = schoolArticleService.articleSend(schoolsId[i], type,
						content, title, titlePath, createTime);
			}
		} else { // 其他：校园新闻、校园公告
			result = schoolArticleService.articleSend(schoolId, type, content,
					title, titlePath, createTime);
		}
		resultMap.put("success", result.isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 根据文章id删除文章
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping("/deleteArticle")
	@ResponseBody
	public String deleteArticle(String articleId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		RichTextInfo info = new RichTextInfo();
		info.setId(articleId);
		resultMap
				.put("success", articleService.articleDelete(info).isSuccess());
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 缩放图片（按比例缩放）
	 * 
	 * @param srcImageFile
	 *            操作图片的地址
	 * @param result
	 *            存放结果图片的地址
	 * @param scale
	 *            缩放比例
	 * @param type
	 *            图片的类型
	 * @return void
	 * @throws
	 */
	private boolean scale(String srcPath, String result, int scale,
			String fileType) {
		try {
			// 获得一个BufferedImage，即读入文件
			File file = new File(srcPath);
			BufferedImage src = ImageIO.read(file);
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			width = (int) (width / scale); // 缩小后的图宽
			height = (int) (height / scale); // 缩小后的图长
			// 第三个是指示用于图像重新取样的算法类型的标志，这个参数是默认算法
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			// 第三个为图片类型
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();

			File uploadPath = new File(result);
			System.out.println("缩略图保存路径：" + result);
			// 目标文件夹不存在则创建
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			ImageIO.write(tag, fileType.substring(1), uploadPath);// 输出到文件流
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 
	 * @Title: copyArticle
	 * @Description: 复制新闻到指定的多个学校或多个年级
	 * @param @param articleId
	 * @param @param id
	 * @param @param type
	 * @param @return 参数
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/copyArticle")
	@ResponseBody
	public String copyArticle(String articleId, String zoneId, String type) { // TODO
																				// 事务控制
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] zoneIds = zoneId.split(",");
		for (int i = 0; i < zoneIds.length; i++) {
			resultMap.put("success",
					schoolArticleService.articleCopy(articleId, zoneIds[i], type)
							.isSuccess());
		}
		return JSONUtil.toObject(resultMap).toString();
	}

	/**
	 * 复制教子学园
	 * @return
	 */
	@RequestMapping("/copyEducate")
	@ResponseBody
	public String copyEducate(String gradeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Pager pager = new Pager();
		pager.setPageSize(50);
		pager.setPageNumber(1);
		List<RichTextInfo> infos = schoolArticleService.getAllArticle("8ee0357ed5fd4c7b8efc0ecf433e3047",
				"educate", pager).getInfoResult();
		List<String> ids = new ArrayList<String>();
		for(RichTextInfo info : infos) {
			ids.add(info.getId());
		} //拿到华景所有一年级新闻id
		
		for(int i = 0; i < ids.size(); i++) {
			schoolArticleService.articleCopy(ids.get(i), gradeId, "educate"); 
		}
		resultMap.put("success", true);
		return JSONUtil.toObject(resultMap).toString();
	}
	
	
}
