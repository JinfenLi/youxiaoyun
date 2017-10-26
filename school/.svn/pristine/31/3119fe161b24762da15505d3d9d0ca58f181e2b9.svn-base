package com.topview.school.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.topview.school.po.PageView;
import com.topview.school.service.feedback.PageViewService;
import com.topview.school.service.feedback.RecordUrlService;
import com.topview.school.util.FilterUtils;
import com.topview.school.util.RecordUtil;
import com.topview.school.util.UUIDUtil;
import com.topview.school.vo.User.UserInfo;

/**
 * @Description 记录用户访问url
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2015年10月4日 下午3:10:21scewdfef Appraise Appraise
 * @CopyRight 2015 TopView Inc
 * @version V1.0
 */
public class PageViewFilter implements Filter {

	private PageViewService pageViewService;
	
	private RecordUrlService recordUrlService;
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		pageViewService = (PageViewService) FilterUtils.getBeans(req,
				"pageViewServiceImpl");
		String ip = req.getRemoteAddr(); // 获取用户ip地址
		String url = recordUrl(req); // 需要记录的url
	/*	UserInfo user=(UserInfo)req.getSession().getAttribute("currentUser");*/
		PageView pv = new PageView();
		if(url==null){
			String exc_url=req.getRequestURI();
			if("/school/permission/user_module_permission_list.action".equals(exc_url)){
			}
			//登陆请求直接通过
			else if("/school/user/login.action".equals(exc_url)){			
			}
			//开发者上传新版本请求直接通过
			else if("/school/version/uploadNewVersion.action".equals(exc_url)){		
			}
			else if(this.judgeLogger(exc_url)){
			UserInfo user=(UserInfo)req.getSession().getAttribute("currentUser");
			pv.setAccessTime(new Date());
			pv.setId(UUIDUtil.getUUID());
			pv.setIp(ip);
			pv.setUrl(exc_url);
			pv.setSchoolId(user.getSchool_id());
			pv.setUserId(user.getUser_id());
			this.pageViewService.save(pv);
			}
		}
		else if (url != null) {
			pv.setId(UUIDUtil.getUUID());
			pv.setIp(ip);
			pv.setUrl(url);
			pv.setAccessTime(new Date());
			pageViewService.save(pv);
		}
		chain.doFilter(request, response);
	}

	/**
	 * 返回需要记录的url
	 * 
	 * @param url
	 * @return
	 */
	private String recordUrl(HttpServletRequest req) {
		String url = req.getRequestURI();
		List<String> urlList=new ArrayList<String>();
		for(int i=0;i<RecordUtil.record.size();i++){
			urlList.add(RecordUtil.record.get(i).getUrl());
		}
		if ("/school/article/getAllArticle.action".equals(url)) {
			String type = (String) req.getParameter("type");
			return url + "?type=" + type;
		}
		if(urlList.contains(url)){
			return url;
		}
		return null;
	}
	
	
	//若获取的url包含增删改的关键字则返回true
	private boolean judgeLogger(String url){
		//开发者上传新app版本无需登录，因此不记录入用户的使用日志当中
		if(url.equals("/school/version/uploadNewVersion.action")){
			return false;
		}
		if(url.contains("save")||url.contains("add")||url.contains("creat")
				||url.contains("del")||
				url.contains("upload")||url.contains("down")
				||url.contains("update")||url.contains("edit")
				){
			return true;
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		BeanFactory beans=WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		this.recordUrlService=(RecordUrlService) beans.getBean("recordUrlServiceImpl");
		RecordUtil.record=recordUrlService.selectAllKeysAndUrl();
		
	}

	public void destroy() {
		
	}

}
