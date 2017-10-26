//package com.topview.multimedia;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.topview.multimedia.service.album.AlbumService;
//import com.topview.multimedia.vo.AlbumInfo;
//
///**
// * 
// * 项目名称：school  <br>
// * 类名称：GetBeanTest  <br>
// * 类描述：  <br>
// * 创建人：luozhangjie  <br>
// * 创建时间：2015年4月2日 下午8:28:26  <br>
// * 修改人：luozhangjie<br>
// * 修改时间：2015年4月2日 下午8:28:26  <br>
// * 修改备注：  <br>
// * @version 1.0  <br>
// *
// */
//public class GetBeanTest {
//
//	private ApplicationContext ac;
//	private AlbumService a;
//	@Before
//	public void init(){
//		ac = new ClassPathXmlApplicationContext("classpath*:spring-*.xml");
//	}
//	
//	@Test
//	public void Test(){
//		a = (AlbumService) ac.getBean("albumServiceImpl");
//		AlbumInfo i = new AlbumInfo();
//		i.settMId("1");
//		i.setId("q1");
//		a.albumDelete(i);
//	}
//	
//}
