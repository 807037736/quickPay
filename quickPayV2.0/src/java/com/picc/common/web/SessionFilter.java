package com.picc.common.web;

import ins.framework.common.ServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.picc.common.utils.AppConfig;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.security.CustomAuthenticationProcessingFilter;
import com.picc.um.service.facade.IUmTUserGroupService;
import com.picc.um.service.facade.IUmTUserService;

public class SessionFilter implements Filter {
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this
			.getClass());
	private static IUmTUserService umTUserService = null;
	private static IUmTUserGroupService umTUserGroupService =null;
	
	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if(initSession((HttpServletRequest) request, (HttpServletResponse) response)==0){
			chain.doFilter(request, response);
		}
	}

	private int initSession(HttpServletRequest request,HttpServletResponse response) {
		int flag = 0;
		HttpSession session = request.getSession();
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails ud = (UserDetails) principal;
				String userCode = ud.getUsername();
				String oldUserCode = (String) session.getAttribute("UserCode");
				if (userCode.equals(oldUserCode)) { // session exists
					
				} else if(userCode!=null&&CustomAuthenticationProcessingFilter.ANONYMOUS.equals(userCode)){//匿名方式处理
					try {
						UmTUser umTUser = new UmTUser();
						UmTUserId umTUserId = new UmTUserId();
						umTUserId.setUserCode(userCode);
						umTUser.setId(umTUserId);
						umTUser.setUserName(userCode);
						umTUser.setComCode("44030000");
						
						SessionUser sessionUser =  new SessionUser(umTUser); 						//构造Session用户对象
						session.setAttribute("UserCode", userCode);
						session.setAttribute("UserName", umTUser.getUserName());
						session.setAttribute("ComCode", umTUser.getComCode());
						session.setAttribute("IP", ToolsUtils.getIpAddr(request));
						sessionUser.setIpAddress(ToolsUtils.getIpAddr(request));
						sessionUser.setUserPass(ud.getPassword());									//构造对角中设置密码
						session.setAttribute("SessionUser", sessionUser);
						logger.info("匿名登录成功",userCode);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("", e);
					}
					
					
				}else {
					umTUserService = (IUmTUserService)ServiceFactory.getService("umTUserService");
					umTUserGroupService = (IUmTUserGroupService)ServiceFactory.getService("umTUserGroupService");
					UmTUser umTUser = null;
					List<UmTGroup> ugl = new ArrayList<UmTGroup>();
					try {
						umTUser = umTUserService.findUmTUserByUserCode(userCode);
						String comId=umTUser.getComId();
						ugl.addAll((List<UmTGroup>) umTUserGroupService.find4sCodeByUserCode(userCode,comId)) ;
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("", e);
					}
					if(umTUser!=null){
						SessionUser sessionUser =  new SessionUser(umTUser); 						//构造Session用户对象
						session.setAttribute("UserCode", userCode);
						session.setAttribute("UserName", umTUser.getUserName());
						session.setAttribute("ComCode", umTUser.getComCode());
						session.setAttribute("IP", ToolsUtils.getIpAddr(request));
						if(ugl!=null&&ugl.size()!=0){
						sessionUser.setGroupCodeList(ugl);
						}
						sessionUser.setIpAddress(ToolsUtils.getIpAddr(request));
						sessionUser.setUserPass(ud.getPassword());									//构造对角中设置密码
						session.setAttribute("SessionUser", sessionUser);
						
						logger.info("登录成功",AppConfig.get("um.LOGIN"),sessionUser.getUserCode(),sessionUser.getUserName(),sessionUser.getIpAddress(),sessionUser.getComId());
					}
					// set session for user
//					UserService userService = (UserService) ServiceFactory.getService("userService");
//					CompanyService companyService = (CompanyService) ServiceFactory.getService("companyService");
//					PrpDuser prpDuser = userService.findUserByUserCode(userCode);
//					String comCName = companyService.findComCNameByComCode(prpDuser.getPrpDcompany().getComCode());
//					// 登录ip验证
//					// loginService.loginIpCheck(userCode, request, session.getId());
//					// modify by wangzhi 在线人数统计功能完善（weblogic集群部署时session复制导致sessionID变化的问题）
//					String sessionIdForIdentify = String.valueOf(new Date().getTime()) + new Random().nextInt(1000);// 生成一个唯一的毫秒数
//					// System.out.println("sessionIdForIdentify==" + sessionIdForIdentify);
//					session.setAttribute("sessionID", sessionIdForIdentify);// 往session里放一个属性作为唯一标记
//					session.setAttribute("UserCode", userCode);
//					session.setAttribute("UserName", "超级管理员");
//					session.setAttribute("ComCode", "44030000");
//					session.setAttribute("ComCName","总公司");
				}
			}else {
				for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
					if("ROLE_ANONYMOUS".equals(auth.getAuthority())){
						try {
							response.sendRedirect(request.getContextPath().concat("/login.jsp?r=").concat(String.valueOf(System.currentTimeMillis())));
							flag = 1;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							logger.warn(e.getMessage());
						}
					}
				}
			}
		}
		return flag;
	}
}
