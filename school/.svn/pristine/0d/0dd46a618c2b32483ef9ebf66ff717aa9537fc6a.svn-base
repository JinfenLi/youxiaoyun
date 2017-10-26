package com.topview.school.util;

import java.lang.reflect.Method;
import java.text.Collator;
import java.util.Comparator;


/**
 * 年级班级排序
 * @author yjy
 *
 * @param <T>
 */
public class SortChineseName<T> implements Comparator<T>{  
	private String name;
    Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
    @Override  
    public int compare(T o1, T o2) { 
    	
    	String name1=null;
    	String name2=null;
		try {															//反射获取字符串
			Class<? extends Object> class1 = o1.getClass();
	    	Method method1;
			method1 = class1.getMethod(name);
			method1.setAccessible(true);
	    	name1 = (String)method1.invoke(o1, null);
	    	Class<? extends Object> class2 = o2.getClass();
	    	Method method2 = class2.getMethod(name);
	    	method2.setAccessible(true);
	    	name2 = (String)method2.invoke(o2, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	ChineseNum n1 = null;
    	ChineseNum n2 = null;
    	
		for(int i=0;i<name1.length() && i<name2.length();i++) {
			char s1 = name1.charAt(i);
			char s2 = name2.charAt(i);
			if(s1>=48 && s1<=57 && s2>=48 && s2<=57) {					//比较阿拉伯数字
				int k = i;
				String number1 = "";
				String number2 = "";
				while(name1.charAt(k)>=48 && name1.charAt(k)<=57) {
					number1+=name1.charAt(k);
					k++;
				}
				k=i;
				while(name2.charAt(k)>=48 && name2.charAt(k)<=57) {
					number2+=name2.charAt(k);
					k++;
				}
				if(Integer.valueOf(number1)>Integer.valueOf(number2)) {
					return 1;
				}else if(Integer.valueOf(number1)<Integer.valueOf(number2)){
					return -1;
				}
			}
			for(ChineseNum m :ChineseNum.values()) {    								//比较中文数字
				if(m.getName()==s1) {
					n1=m;
				}
				if(m.getName()==s2) {
					n2=m;
				}
			}
			if(null != n1 && null!= n2) {
				if(n1.getIndex()>n2.getIndex()) {
					return 1; 
				}else if(n1.getIndex()<n2.getIndex()){
					return -1;  
				}else {
					n1=null;
					n2=null;
				}
			}else {																//比较其余汉字
				if (cmp.compare(String.valueOf(s1), String.valueOf(s2))>0){ 
		            return 1;  
		        }else if (cmp.compare(String.valueOf(s1), String.valueOf(s2))<0){ 
		            return -1;  
		        }  
			}
		}
		return 0;
    }
    
	public SortChineseName(String name) {
		super();
		this.name = name;
	}  
    
    
}  

//年级班级中文排序枚举
enum ChineseNum {
	ONE('一', 1), TWO('二', 2), THREE('三', 3), FOUR('四', 4),Five('五',5),
	SIX('六',6),SEVEN('七',7),EIGHT('八',8),NINE('九',9);
	
	 private char name ;
	 private int index ;
	 
	 private ChineseNum( char name , int index ){
	        this.name = name ;
	        this.index = index ;
	    }

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	 
}

