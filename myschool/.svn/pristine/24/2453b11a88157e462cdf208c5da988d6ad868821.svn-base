package com.topview.school.service.base;

import java.util.List;

/**   
 * @Title: BaseServiceImpl.java 
 * @Package com.topview.school.service.base 
 * @Description: TODO 
 * @author Sherlock-lee   
 * @date 2015年5月16日 下午4:32:06 
 * @version V1.0   
 */
public class BaseServiceImpl {
	/** 
	 * @ClassName: BaseServiceImpl 
	 * @Description: TODO
	 * @author Sherlock-lee
	 * @date 2015年5月16日 下午4:32:06 
	 */
	public boolean excute(Request request, List<Process> processes){
		
		Context context = new Context(request);
		for (Process process : processes) {
			if (!process.doProcess(context))
				return false;
		}
		return true;
	}
	
	
}
