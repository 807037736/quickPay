package com.picc.um.security;

import ins.framework.web.Struts2Action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.savedrequest.SavedRequest;

import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;



/**
 * 多登录入口处理类
 * @author 姚文锋
 */
public class LogonHandlerAction extends Struts2Action {

	@Autowired
	public ITmTApplicationConfigService tmTApplicationConfigService = null;
	
	
	public ITmTApplicationConfigService getTmTApplicationConfigService() {
		return tmTApplicationConfigService;
	}


	public void setTmTApplicationConfigService(
			ITmTApplicationConfigService tmTApplicationConfigService) {
		this.tmTApplicationConfigService = tmTApplicationConfigService;
	}


	/**
	 * 登录前根据系统配置表配置信息判断要进入的登录入口
	 * @return 跳转页面，在struts里配置
	 * @throws Exception
	 */
	public void preLogon() throws Exception {
		HttpServletRequest request =  getRequest();
		HttpServletResponse response = getResponse();
		String context = request.getContextPath().replaceFirst("/", "");
		
		Enumeration paramNameEnum = request.getParameterNames();
		String paramName = null;
		while (paramNameEnum.hasMoreElements()) {
			paramName = (String) paramNameEnum.nextElement();
			request.setAttribute(paramName,request.getParameter(paramName));
		}
		
		if (context == null){
			HttpSession session = getSession();
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
			context = savedRequest.getContextPath();
		}
		
		//response.sendRedirect(context+"/"+tmTApplicationConfigService.getPageByContext(context));
		//logger.info("path:{}",context+"/"+tmTApplicationConfigService.getPageByContext(context));
		request.getRequestDispatcher(tmTApplicationConfigService.getPageByContext(context)).forward(request, response);
	}
	
	
	/**
	 * index.jsp发起请求，根据context判断要进入系统的主页面
	 * @return 跳转页面，在struts里配置
	 * @throws Exception
	 */
	public void forwordMainPage() throws Exception {
		HttpServletRequest request =  getRequest();
		HttpServletResponse response = getResponse();
		String context = request.getContextPath().replaceFirst("/", "");
		String mainPage = "main.jsp";
		
		Enumeration paramNameEnum = request.getParameterNames();
		String paramName = null;
		while (paramNameEnum.hasMoreElements()) {
			paramName = (String) paramNameEnum.nextElement();
			request.setAttribute(paramName,request.getParameter(paramName));
		}
		
		if (context == null){
			HttpSession session = getSession();
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
			context = savedRequest.getContextPath();
		}
		TmTApplicationConfig tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(context);
		if(tmTApplicationConfig!=null&&tmTApplicationConfig.getMainPage()!=null&&!"".equals(tmTApplicationConfig.getMainPage())){
			mainPage = tmTApplicationConfig.getMainPage();
		}else{
			throw new BadCredentialsException("您访问的系统【"+context+"】不存在，请联系管理员！");
		}
		//response.sendRedirect("/"+context+"/"+mainPage);
		if(context!=null&&!"".equals(context)){
			response.sendRedirect("/"+context+"/"+mainPage);
		}else{
			response.sendRedirect("/"+mainPage);
		}
	}
	
}
