package com.picc.um.security;

import ins.framework.web.Struts2Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.um.schema.vo.SessionUser;


/**
 * 营销系统注销操作Action,当用户点击右上角的注销按钮时进行触发
 * @author 姜卫洋
 */

@SuppressWarnings("serial")
public class LogoutSuccessAction extends Struts2Action {
	
	
	private static Logger logger = LoggerFactory.getLogger(LogoutSuccessAction.class);
	
	
	public void logoutSuccess() {
		/**
		 * 获取当前登录用户
		 */
		SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute("SessionUser");
		try {
			if(sessionUser!=null){		//注销话务登录操作
//				cMSInterfaceForSysService.conLogoutCMS(sessionUser.getUserCode(), sessionUser.getComCode());
//				System.out.println("用户:"+sessionUser.getUserCode()+"注销成功");
				logger.info("用户:"+sessionUser.getUserCode()+"注销成功");
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	

}
