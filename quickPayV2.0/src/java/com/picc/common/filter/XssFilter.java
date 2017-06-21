/**
 * This file created at 2011-12-28.
 *
 * Copyright (c) 2002-2011 Bingosoft, Inc. All rights reserved.
 */
package com.picc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <code>{@link XssFilter}</code>
 *
 * 防止脚本攻击的Filter
 *
 * @author zhongtao
 */
public class XssFilter implements Filter {

	private String[] ignoreKeys;
	private int ignoreKeyCount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		boolean isIgnore  = false;
		String ignoreKey = null;
		
		String uri = httpServletRequest.getRequestURI();
		/*//上下文为空的时候，自动补上。
		if(uri.indexOf("/",2)==-1){
			uri="/QuickPay/";
		}
		uri = uri.substring(uri.indexOf("/",2));
		if (uri == null) {
			uri = "";
		}
		*/
		uri = uri.toLowerCase();
		
		for (int i = 0; i < this.ignoreKeyCount; i++) {
			ignoreKey = ignoreKeys[i];
			if(ignoreKey == null){
				ignoreKey = "";
			}else{
				ignoreKey = ignoreKeys[i].toLowerCase().trim();
			}
			if (uri.equals(ignoreKey)) {
				isIgnore = true;
				break;
			}
		}
		if(!isIgnore){
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
					(HttpServletRequest) request);
			filterChain.doFilter(xssRequest, response);
		}else{
			filterChain.doFilter(request, response);
		}
	}


	public void init(FilterConfig filterConfig) throws ServletException {
		String ignoreKey = filterConfig.getInitParameter("ignoreKey");
		if (ignoreKey != null) {
			this.ignoreKeys = ignoreKey.split(",");
		} else {
			this.ignoreKeys = new String[0];
		}
		this.ignoreKeyCount = this.ignoreKeys.length;
	}
	
}