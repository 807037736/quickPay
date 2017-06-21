package com.picc.um.security;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.anonymous.AnonymousAuthenticationToken;
import org.springframework.security.ui.AuthenticationDetailsSource;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.ui.WebAuthenticationDetailsSource;
import org.springframework.security.userdetails.memory.UserAttribute;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CustomerIdentifyProcessingFilter extends SpringSecurityFilter
   implements InitializingBean {
	private AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private String key;
	private UserAttribute userAttribute;
	private boolean removeAfterRequest = true;
	public void afterPropertiesSet() throws Exception {
	   Assert.notNull(userAttribute);
	   Assert.hasLength(key);
	}
	protected boolean applyAnonymousForThisRequest(HttpServletRequest request) {
	   return false;
	}
	protected Authentication createAuthentication(HttpServletRequest request) {
	   AnonymousAuthenticationToken auth = new AnonymousAuthenticationToken(
	     key, userAttribute.getPassword(), userAttribute
	       .getAuthorities());
	   auth.setDetails(authenticationDetailsSource
	     .buildDetails((HttpServletRequest) request));
	   return auth;
	}
	
	private boolean isJspResources(HttpServletRequest request) {
	   boolean flag = false;
	   return flag;
	}
	
	protected void doFilterHttp(HttpServletRequest request,
	    HttpServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	   if (SecurityContextHolder.getContext() != null
	     && SecurityContextHolder.getContext().getAuthentication() != null
	     && SecurityContextHolder.getContext().getAuthentication()
	       .isAuthenticated()) {
	    //获得前面filter登陆的用户loginId
	    String loginId = SecurityContextHolder.getContext()
	      .getAuthentication().getName();
	    //得到请求URL
	    String url = request.getRequestURI().toLowerCase();
	    //目前通过properties获得对应的spring bean名称
	    /*
	    String managerName = ResourceSystemConfiguration.getResourceMap()
	      .get(url);
	    boolean flag = true;
	    if (StringUtil.isNotBlank(managerName)) {
	     //要实现资源拦截的manager多implement一个接口ResourceMappingManager
	     flag = ((ResourceMappingManager) getAppContext(request)
	       .getBean(managerName)).checkResourceMapping(loginId,
	       url);
	    */
	     //flag = false; //测试用
	     if (true) {
	      // 不通过，那么转向没有权限页面
	      response.sendRedirect(request.getContextPath()
	        + "/common/noright.jsp");
	      return;
	     }
	     //放行
	   }
	   chain.doFilter(request, response);
	}
	
	protected ApplicationContext getAppContext(HttpServletRequest request) {
	   HttpSession session = request.getSession();
	   ApplicationContext ctx = WebApplicationContextUtils
	     .getRequiredWebApplicationContext(session.getServletContext());
	   return ctx;
	}
	public int getOrder() {
	   return FilterChainOrder.ANONYMOUS_FILTER;
	}
	public String getKey() {
	   return key;
	}
	public UserAttribute getUserAttribute() {
	   return userAttribute;
	}
	public boolean isRemoveAfterRequest() {
	   return removeAfterRequest;
	}
	public void setAuthenticationDetailsSource(
	    AuthenticationDetailsSource authenticationDetailsSource) {
	   Assert.notNull(authenticationDetailsSource,
	     "AuthenticationDetailsSource required");
	   this.authenticationDetailsSource = authenticationDetailsSource;
	}
	public void setKey(String key) {
	   this.key = key;
	}
	public void setRemoveAfterRequest(boolean removeAfterRequest) {
	   this.removeAfterRequest = removeAfterRequest;
	}
	public void setUserAttribute(UserAttribute userAttributeDefinition) {
	   this.userAttribute = userAttributeDefinition;
	}
}