package com.picc.um.security;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SecurityUtils {

	/**
	 * 处理特殊字符(Cross-site scripting (XSS)) // Filter the HTTP response using
	 * SecurityUtil.outputfilter PrintWriter out = response.getWriter(); // set
	 * output response out.write(SecurityUtil.outputfilter(response));
	 * out.close();
	 */
	public static String outputfilter(String value) {
		if (value == null) {
			return null;
		}
		
		StringBuffer returnStringBuffer = new StringBuffer(value.length());
		for (int i = 0; i < value.length(); ++i) {
			returnStringBuffer.append(value.charAt(i));
		}
		
		String returnString = returnStringBuffer.toString();
		StringBuffer result = new StringBuffer(returnString.length());
		for (int i = 0; i < returnString.length(); ++i) {
			switch (returnString.charAt(i)) {
			case '<':
				result.append("＜");
				break;
			case '>':
				result.append("＞");
				break;
			case '(':
				result.append("（");
				break;
			case ')':
				result.append("）");
				break;
			case '&':
				result.append("＆");
				break;
			case '\'':
				result.append("＇");
				break;
			case '"':
				result.append("＂");
				break;
			case '%':
				result.append("％");
				break;
			case '/':
				result.append("／");
				break;
			default:
				result.append(returnString.charAt(i));
				break;
			}
		}
		return result.toString();
	}

	/**
	 * 处理特殊字符(HTTP response splitting)
	 * 在HTTP响应头文件中包含未经过校验的数据会导致cache-poisoning，cross-site scripting，cross-user
	 * defacement或者page hijacking攻击。
	 * 应用程序应该屏蔽任何肯定要出现在HTTP响应头中、含有特殊字符的输入，特别是CR（回车符
	 * ，也可由%0d或\r提供）和LF（换行符，也可由%0a或\n提供）字符，将它们当作非法字符。
	 * 
	 * @param str
	 * @return
	 */
	public static String fixHttpRS(String str) {
		if (str == null) {
			return null;
		}
		String str_temp = str;

		while (true) {
			if ((str_temp.indexOf("CR") == -1) && (str_temp.indexOf("%0d") == -1) && (str_temp.indexOf("\r") == -1) && (str_temp.indexOf("LF") == -1)
					&& (str_temp.indexOf("%0a") == -1) && (str_temp.indexOf("\n") == -1)) {
				break;
			}
			if (str_temp.indexOf("CR") != -1) {
				str_temp = str_temp.replaceAll("CR", "");
			}
			if (str_temp.indexOf("%0d") != -1) {
				str_temp = str_temp.replaceAll("%0d", "");
			}
			if (str_temp.indexOf("\r") != -1) {
				str_temp = str_temp.replaceAll("\r", "");
			}
			if (str_temp.indexOf("LF") != -1) {
				str_temp = str_temp.replaceAll("LF", "");
			}
			if (str_temp.indexOf("%0a") != -1) {
				str_temp = str_temp.replaceAll("%0a", "");
			}
			if (str_temp.indexOf("\n") != -1) {
				str_temp = str_temp.replaceAll("\n", "");
			}
		}

		return str_temp.toString();
	}

	/**
	 * 处理SQLINJECTION(数据库注入攻击) 一些数据库输入语句可能导致数据库信息的泄漏
	 * 应用程序所使用数据库语句中的个关键字都应经过字符过滤，避免注入攻击。
	 * 
	 * @param str
	 * @return
	 */
	public static String sqlfilter(String value) {

		if (value == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer(value.length());
		for (int i = 0; i < value.length(); ++i) {
			switch (value.charAt(i)) {
			case '\'':
				result.append("'");
				break;
			case '-':
				result.append("-");
				break;
			case '#':
				result.append("#");
				break;
			case '"':
				result.append("\"");
				break;
			case '/':
				result.append("/");
				break;
			case '$':
				result.append("$");
				break;
			default:
				result.append(value.charAt(i));
				break;
			}
		}
		return result.toString();
	}
	
	/*
	 * 处理简单的List，无法处理List嵌套List等类型
	 */
	public static List outputfilter(List list) {
		if(list ==null){
			return null;
		}
		List returnList = new ArrayList();
		for(Object object : list){
			returnList.add(object);
		}
		for (Object object : returnList) {
			Field[] fields = object.getClass().getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object attr = field.get(object);
					if(attr instanceof String){
						String attrStr = attr.toString();
						String filterStr = SecurityUtils.outputfilter(attrStr);
						Object obj = attr.getClass().newInstance();
						obj = filterStr;
						field.set(object, obj);
					}
				} catch (Exception e) {
					continue;
				}
			}
			
		}
		return returnList;
	}
	
	/*
	 * 处理简单的Map，暂不做嵌套处理
	 */
	public static Map outputfilter(Map map) {
		if(map == null){
			return null;
		}
		Map returnMap = new HashMap();
		Set c = map.keySet();
		for (Object key : c) {
			Object value = map.get(key);
			returnMap.put(key, value);
		}
		Set returnKey = returnMap.keySet();
		for (Object key : returnKey) {
			Object object = returnMap.get(key);
			if(object instanceof String){
				String attrStr = object.toString();
				String filterStr = SecurityUtils.outputfilter(attrStr);
				returnMap.put(key, filterStr);
			}
		}
		return returnMap;
	}
}
