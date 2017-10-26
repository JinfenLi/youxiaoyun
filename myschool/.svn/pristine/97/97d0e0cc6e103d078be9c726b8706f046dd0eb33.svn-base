package com.topview.school.util;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 
 * 项目名称：school  <br>
 * 类名称：SpringSourceUtil  <br>
 * 类描述：  <br>
 * 创建人：luozhangjie  <br>
 * 创建时间：2015年3月27日 下午9:10:44  <br>
 * 修改人：luozhangjie<br>
 * 修改时间：2015年3月27日 下午9:10:44  <br>
 * 修改备注：  <br>
 * @version 1.0  <br>
 *
 */
public class SpringSourceUtil {

	public static void getSourcePath(String sourcePatten){
		try {  
		    for (Resource r : new PathMatchingResourcePatternResolver().getResources(sourcePatten)){  
		        System.out.println("===================================================");
		    	System.out.println("The real path is : "+r.getFile().getAbsolutePath());  
		    	System.out.println("===================================================");
		    }  
		} catch (IOException e1) {  
		    e1.printStackTrace();  
		}  
	}
	
}
