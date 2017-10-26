package com.topview.school.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description 字符串相关工具类
 * @Date 2016年1月14日 下午2:26:34
 * @CopyRight 2016 TopView Inc
 * @version V1.0
 */
public class StringUtil {
	
	public static String getDateTimeString (){
		SimpleDateFormat df = new SimpleDateFormat("yyyMMddHHmmss");
		return df.format(new Date());
	}
	
	/**
	 * 把字符串的第一个字母从小写改为大写。
	 * @param str 需要更改的字符串。
	 * @return	如果字符串首字母属于a-z，则返回把首字母改为大写后的字符串。
	 * 			如果字符串首字母不属于a-z，则返回原字符串。
	 */
	public static String firstLetterToUpperCase(String str) {
		byte[] items = str.getBytes();
		if (items[0] <= 'z' && items[0] >= 'a') {
			items[0] = (byte) ((char) items[0] - 'a' + 'A');	//首字母更改为大写
		}
		return new String(items);
	}

	/**
	 * 把字符串的第一个字母从大写改为小写。
	 * @param str 需要更改的字符串。
	 * @return	如果字符串首字母属于A-Z，则返回把首字母改为小写后的字符串。
	 * 			如果字符串首字母不属于A-Z，则返回原字符串。
	 */
	public static String firstLetterToLowerCase(String str) {
		byte[] items = str.getBytes();
		if (items[0] <= 'Z' && items[0] >= 'A') {
			items[0] = (byte) ((char) items[0] - 'A' + 'a');	//首字母更改为小写
		}
		return new String(items);
	}
	
	/**
	 * 生成一个随机的n位数的数字字符串并返回
	 * @param length 生成的字符串的长度
	 * 
	 */
	public static String getRandomString(int length){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<length;i++){
			sb.append((int)(Math.random()*10));
		}
		return sb.toString();
	}
	
}
