package com.topview.school.util;

import java.security.MessageDigest;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import com.topview.school.dao.user.TeacherMapper;
import com.topview.school.service.user.UserService;
import com.topview.school.service.user.teacher.TeacherService;

/**
 * 用于根据md5来生成对应的密码以及匹配对应密码的工具类
 * @author dr
 * @email 747184616@qq.com
 *
 */
public class MD5Util {
	
			@Resource
			UserService userService;
			@Resource
			TeacherService teacherService;
			@Resource
			TeacherMapper teacherMapper;
	
			/**
			 * 传入原密码和盐，返回加盐后的密码
			 * @return
			 */
			public static String getCodedPassword(String password,String salt){
				if(NotEmptyString.isNotEmpty(new String[] {password,salt})){
					System.out.println("???");
					String md5Password = new Md5Hash(password,salt,2).toString();
					return md5Password;
				}
				return "error";
			}
			
			@Test
			public void test(){
				String temp = MD5Util.getCodedPassword("123456", "72765e2c1fc911e6927a14dae992dc05");
				System.out.println(temp);
			}
			
			
			/**
			 * 将字符串转成MD5
			 * @param str
			 * @return
			 */
			public static String getMD5(String str) {
				Logger log = Logger.getLogger(MD5Util.class);
				String reStr = null;
				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(str.getBytes());
					byte ss[] = md.digest();
					reStr = bytes2String(ss);
				} catch (Exception e) {
					log.error("[MD5加密失败]异常信息 ：", e);
				}
				return reStr;
			}
			
			private static String bytes2String(byte[] aa) {
				String hash = "";
				for(int i = 0; i < aa.length; i++) {
					int temp;
					temp = aa[i] < 0 ? aa[i]+256 : aa[i];
					if(temp < 16) hash += "0";
					hash += Integer.toString(temp, 16);
				}
				hash = hash.toUpperCase();
				return hash;
			}
}
