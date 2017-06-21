package com.picc.common.web;

import javax.jws.WebService;

/**
 * DWR消息推送，支持webservice调用
 * @author shenyichan
 *
 */
@WebService
public interface DwrPush {
	
	
	/**
	 * dwr消息推送
	 * @param showPage 要显示消息的页面请求名
	 * @param functionName 页面要调用的函数名
	 * @param msg 要推送的消息内容
	 * @param userCodes 要推送的用户，值为null时表示广播
	 * @throws Exception 
	 */
	public void doPush(String showPage,String functionName,String msg, String[] userCodes) ;
}
