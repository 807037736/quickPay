package com.picc.um.cognos.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Cookie工具类
 * @author shenyichan
 * 2013-08-27
 */
public class CookieUtil {
	private static String path = "/khyx";
	private static int age = 24*3600;
	
	/**
	 * 添加cookie
	 * @param name
	 * @param value
	 * @param response
	 * @throws Exception
	 */
	public static void addCookie(String name,String value,
			HttpServletResponse response) throws Exception {
		addCookie(name,value,response,age,path);
	}
	
	/**
	 * 添加cookie
	 * @param name
	 * @param value
	 * @param response
	 * @param age
	 * @param path
	 * @throws Exception
	 */
	public static void addCookie(String name,String value,
			HttpServletResponse response,
			int age,String path) throws Exception {
		Cookie cookie = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		cookie.setMaxAge(age);
		cookie.setPath(path);
		cookie.setDomain("58.1.39.129");
		
		response.addCookie(cookie);
	}
	
	/**
	 * 根据Cookie的名字得到Cookie的值
	 * @param name
	 * @param request
	 * @return
	 */
	public static String findCookie(String name,
			HttpServletRequest request) throws Exception{
		String value = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(name)){
					value = URLDecoder.decode(cookie.getValue(),"utf-8");
				}
			}
		}
		
		return value;
	}
	
	/**
	 * 删除Cookie
	 * @param name
	 * @param response
	 */
	public static void deleteCookie(String name,HttpServletResponse response){
		Cookie cookie = new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(path);
		
		response.addCookie(cookie);
	}
	
}
