/**
 * This file created at 2011-12-28.
 *
 * Copyright (c) 2002-2011 Bingosoft, Inc. All rights reserved.
 */
package com.picc.common.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <code>{@link XssHttpServletRequestWrapper}</code>
 *
 * 处理从request获取参数的XssHttpServletRequestWrapper
 *
 * @author zhongtao
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest originalRequest;

	/**
	 * @param request
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.originalRequest = request;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (null != value && !"".equals(value.trim())) {
			value = xssEncode(value);
		}
		return value;
	}



	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map getParameterMap() {
		return xssEncodeMap(super.getParameterMap());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequestWrapper#getQueryString()
	 */
	@Override
	public String getQueryString() {
		String value = super.getQueryString();
		if (null != value && !"".equals(value.trim())) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	* 将容易引起xss漏洞的半角字符直接替换成全角字符
	*
	* @param s
	* @return
	*/
	private String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		if (s.indexOf('>') != -1 || s.indexOf('<') != -1 || s.indexOf('\'') != -1) {

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				switch (c) {
				case '>':
					sb.append('＞');//全角大于号
					break;
				case '<':
					sb.append('＜');//全角小于号
					break;
				case '\'':
					sb.append('‘');//全角单引号
					break;
				case '(':
					sb.append('（');//全角左括号
					break;
				case ')':
					sb.append('）');//全角右括号
					break;
				// 下面三个不常用
				//				case '\"':
				//					sb.append('“');//全角双引号
				//					break;
				//				case '&':
				//					sb.append('＆');//全角
				//					break;
				//				case '\\':
				//					sb.append('＼');//全角斜线
				//					break;
				//				case '#':
				//					sb.append('＃');//全角井号
				//					break;
				default:
					sb.append(c);
					break;
				}
			}
		} else {
			sb.append(s);
		}
		return sb.toString().trim();
	}

	/**
	 * 处理map中的数据
	 * @param map
	 */
	@SuppressWarnings( { "unchecked" })
	private Map xssEncodeMap(Map map) {
		Map mapre = new HashMap();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (entry.getValue() instanceof String) {//只处理String类型的数据
				mapre.put(key, xssEncode((String) val));
			} else if (entry.getValue() instanceof String[]) { //非String类型数据，原样保存
				String[] s = (String[]) entry.getValue();
				for (int i = 0; i < s.length; i++) {
					s[i] = xssEncode(s[i]);
				}
				mapre.put(key, s);
			} else { //非String类型数据，原样保存
				mapre.put(key, val);
			}
		}
		return mapre;
	}

	/**
	 * @return the originalRequest
	 */
	public HttpServletRequest getOriginalRequest() {
		return originalRequest;
	}

	/**
	 * @param originalRequest the originalRequest to set
	 */
	public void setOriginalRequest(HttpServletRequest originalRequest) {
		this.originalRequest = originalRequest;
	}
}