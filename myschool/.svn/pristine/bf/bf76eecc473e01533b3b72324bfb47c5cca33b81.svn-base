package com.topview.school.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author hcdn
 * @description 检验字段长度类
 */
public class LengthUtil {
	
	public static Map<String, Object> validate(String text, int minLength, int maxLength) {
		Map<String, Object> map = new HashMap<>();
		if(NotEmptyString.isNotNullString(text)) {
			if(text.length() < minLength) {
				map.put("reason", "字符串长度太短");
			}
			if(text.length() > maxLength) {
				map.put("reason", "字符串长度太长");
			}
			if(!map.isEmpty()) {
				map.put("success", false);
			}
		}else {
			map.put("success", false);
		}
		if(map.isEmpty()) {
			map.put("success", true);
		}
		return map;
	}
}
