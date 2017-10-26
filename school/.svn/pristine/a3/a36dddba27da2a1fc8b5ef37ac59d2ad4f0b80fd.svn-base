package com.topview.school.filter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author dr 747184616@qq.com	
 * @description 通过spring aop 获取用户操作并记录到数据库中
 * @version 1.0
 */
@Aspect
@Component
public class AopInterceptor {
	
	
				@Before("anyMethod()")
				public void before(){
					System.out.println("hhhhh");
				}
			
			  @Pointcut("execution (* com.topview.school.controller.*.*(..))")// 切入点表达式  
			 //  @Pointcut("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
			    private void anyMethod() {  
			     }// 声明一个切入点  
	
				@After("anyMethod()")
				public void after(JoinPoint joinPoint){
						System.out.println(joinPoint.getSignature().getName());
				}
}
