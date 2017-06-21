package com.picc.um.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.security.util.TextUtils;
import org.springframework.util.Assert;

import com.picc.common.Constants;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 重写登录认证逻辑
 * 
 * @author 沈一婵
 * 
 */
public class CustomAuthenticationProcessingFilter extends
		AuthenticationProcessingFilter {

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";			//登录用户名
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";			//登录密码
	public static final String SPRING_SECURITY_HOST = "j_host";													//访问Host地址
	public static final String ANONYMOUS = "ANONYMOUS";
	public static final String OUTER_PASSWORD_PREFIX_BINDING = "OUTER_PASSWORD_PREFIX_BINDING_";
	public static final String OUTER_PASSWORD_PREFIX_ANONYMOUS = "OUTER_PASSWORD_PREFIX_ANONYMOUS_";
	public static final String OUTER_PASSWORD_PREFIX_LOGON_ = "OUTER_PASSWORD_PREFIX_LOGON_";//外部客户登录
	
	public static final String PUBLIC_ALL_ALLOW = "public_all_allow";
	public static final String PUBLIC_REGIST_ALLOW = "public_regist_allow";
	public static final String PUBLIC_BIND_ALLOW = "public_bind_allow";
	public static final String ROLE_ALLOW = "role_allow";
	public static final String ROLE_DENY = "role_deny";
	
	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	private String hostParameter = SPRING_SECURITY_HOST;
	
	
	@Autowired
	private  IUmTUserService umTUserService = null;
	
	@Autowired
	public ITmTApplicationConfigService tmTApplicationConfigService = null;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request)
			throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		username = username.trim();
		
		
		HttpSession session = request.getSession(true);
		String errormessage = null;
		//查询用户存不存在
		UmTUser umTUser = null;
		TmTApplicationConfig tmTApplicationConfig = null;
		try {
			tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(request.getContextPath().replaceFirst("/", ""));
			umTUser = umTUserService.findUmTUserByUserCode(username);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			errormessage = "系统资源繁忙，请稍后再试！";
			request.setAttribute("errormessage", errormessage);
			throw new BadCredentialsException(errormessage);
		}
		
		if(tmTApplicationConfig!=null&&umTUser!=null){//用户存在则放入session中
			SessionUser sessionUser = new SessionUser(umTUser);
			session.setAttribute("UserCode", username);
			session.setAttribute("UserName", umTUser.getUserName());
			session.setAttribute("ComCode", umTUser.getComCode());
			session.setAttribute("IP", ToolsUtils.getIpAddr(request));
			session.setAttribute(Constants.SERVERCODE, tmTApplicationConfig.getId().getServerCode());
			session.setAttribute(Constants.SERVERTYPE, tmTApplicationConfig.getUserType());
			//暂时先这样处理，后续优化
			if("07".equals(umTUser.getUserSort())||"08".equals(umTUser.getUserSort())){
				session.setAttribute(Constants.SERVERNAME, "湖南省高速公路交通警察局长沙支队道路交通事故快处快赔中心");
			}else{
				session.setAttribute(Constants.SERVERNAME, tmTApplicationConfig.getServerName());
			}
			sessionUser.setIpAddress(ToolsUtils.getIpAddr(request));
			sessionUser.setUserPass(password);
			sessionUser.setBindStatus(umTUser.getBindStatus());
			session.setAttribute("SessionUser", sessionUser);
		}else {
			request.setAttribute("errormessage", "用户【"+username+"】不存在！");
			throw new UsernameNotFoundException("用户【"+username+"】不存在！");
		}
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		if (session != null || getAllowSessionCreation()) {
			session.setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,
					TextUtils.escapeEntities(username));
		}
		
		setDetails(request, authRequest);
		return getAuthManager().authenticate(authRequest);
	}
	
	public AuthenticationManager getAuthManager(){
		return this.getAuthenticationManager();
	}

	public String getDefaultFilterProcessesUrl() {
		return "/j_spring_security_check";
	}

	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}

	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource
				.buildDetails(request));
	}

	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter,
				"Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter,
				"Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	public int getOrder() {
		return FilterChainOrder.AUTHENTICATION_PROCESSING_FILTER;
	}

	public String getUsernameParameter() {
		return usernameParameter;
	}

	public String getPasswordParameter() {
		return passwordParameter;
	}
	

	public String getHostParameter() {
		return hostParameter;
	}

	public void setHostParameter(String hostParameter) {
		Assert.hasText(hostParameter,
				"hostParameter must not be empty or null");
		this.hostParameter = hostParameter;
	}
	
	protected String obtainHost(HttpServletRequest request) {
		return request.getParameter(hostParameter);
	}

	/**
	 * spring security 登录成功后进行重定向
	 */
	protected void sendRedirect(HttpServletRequest request,
			HttpServletResponse response, String url) throws IOException {
		if(url.indexOf("login.jsp")!=-1||url.indexOf("preLogon.do")!=-1||"/logon.do".equals(url)||"/main.jsp".equals(url)){
			url = request.getContextPath()+url;
		}else{
			TmTApplicationConfig tmTApplicationConfig = null;
			try {
				tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(request.getContextPath().replaceFirst("/", ""));
				if(tmTApplicationConfig!=null){
					//判断是否需要cogNos交互
					if("1".equals(tmTApplicationConfig.getNeedCognos())){
						//开启Cognos环境,cognos登录完成后自动跳到main.jsp页面
						url = request.getContextPath() + "/logon.do";
					}
					else{
						//不需要Cognos交互，根据系统配置信息自动跳到相应主页面
						url = request.getContextPath() + "/forwordMainPage.do";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				request.setAttribute("errormessage", "登录跳转异常！");
				//throw new AccessDeniedException("登录跳转异常！");
			}
		}
		response.sendRedirect(response.encodeRedirectURL(url));
	}

}
