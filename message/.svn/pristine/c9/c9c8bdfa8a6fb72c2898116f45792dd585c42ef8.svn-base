package com.topview.message.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	
	private static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	
	/**
	 * 返回String类型时间精确到秒: yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDateToSeconds(Date date){
		DateFormat df = new SimpleDateFormat(FORMAT_TIME);
		return df.format(date);
	}
	
	/**
	 * 返回String类型时间精确到天: yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDateToDay(Date date) {
		DateFormat df = new SimpleDateFormat(FORMAT_DATE);
		return df.format(date);
	}
	
	/**
	 * 返回Date类型时间精确到秒
	 * @param dateString
	 * @return
	 */
	public static Date parseToSeconds(String dateString) {
		DateFormat df = new SimpleDateFormat(FORMAT_TIME);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回Date类型时间精确到天
	 * @param dateString
	 * @return
	 */
	public static Date parseToDay(String dateString) {
		DateFormat df = new SimpleDateFormat(FORMAT_DATE);
		try {
			return df.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
