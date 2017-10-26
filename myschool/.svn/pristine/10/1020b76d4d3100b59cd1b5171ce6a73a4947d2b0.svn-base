package com.topview.school.util;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Description 用于tomcat启动后spring注入bean前,获取bean
 * @Author <wuyiliang 757210697@qq.com>
 * @Date 2016年1月14日 下午2:21:37
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public class FilterUtils {

	private static ApplicationContext ctx = null;
	
	public static Object getBeans(HttpServletRequest req, String name) {
		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(req.getSession()
							.getServletContext());
		}
		return ctx.getBean(name);
	}
}
