package com.topview.school.util;

public class HtmlUtil {
	
	static String htmlHead = "<!doctype html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta charset=\"utf-8\"><link rel=\"stylesheet\" href=\"../resources/css/bootstrap.min.css\"> </head><body><div class=\"container\"> <div class=\"row\"><div class=\"col-xs-12\">";
	static String htmlBody = "</div></div></div></body></html>";
	/**
	 * 将文本转化成HTML
	 * 
	 * @param text
	 * @return
	 */
	public static String toHtml(String text) {
		return htmlHead + text + htmlBody;
	}
	
	public static String toText(String html) {
		return html.substring(htmlHead.length(), html.indexOf(htmlBody));
	}
	
}
