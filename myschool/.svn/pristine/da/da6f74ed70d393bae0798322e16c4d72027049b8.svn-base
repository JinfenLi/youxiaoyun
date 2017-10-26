package com.topview;

import org.junit.Test;

import com.topview.school.util.SpringSourceUtil;

public class GetXmlTest {

	@Test
	public void test_01(){
		System.out.println("----------测试读取springXML配置文件---------");
		SpringSourceUtil.getSourcePath("classpath*:spring-*.xml");
	}
	
	@Test
	public void test_02(){
		System.out.println("----------测试读取springXML配置文件---------");
		SpringSourceUtil.getSourcePath("classpath*:multimedia-0.0.1-SNAPSHOT.jar*/spring-multimedia-album.xml");
	}
	
	@Test
	public void test_03(){
		System.out.println("----------测试读取springMapperXML配置文件---------");
		SpringSourceUtil.getSourcePath("classpath*:/**/*Mapper.xml");
	}
	
}
