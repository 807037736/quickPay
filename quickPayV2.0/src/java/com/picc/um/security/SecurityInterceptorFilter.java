package com.picc.um.security;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.security.util.TextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.picc.common.Constants;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTUserRelationService;
import com.picc.um.service.facade.IUmTUserService;
import com.picc.um.service.facade.IUmTWhiteListService;

public class SecurityInterceptorFilter extends OncePerRequestFilter {
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this
			.getClass());
	
	/**用户角色对应关系**/
	private static CacheService userRoleCacheService = CacheManager.getInstance("UM_T_USERROLE");
	
	/**角色与URL对应的映射关系**/
	private static CacheService urlRoleCacheService = CacheManager.getInstance("UM_T_URLROLE");
	
	/**用户与URL对应的映射关系**/
	private static CacheService userTaskCacheService = CacheManager.getInstance("UM_T_USERTASK");
	
	/**系统访问白名单**/
	private static CacheService whiteListCacheService  = CacheManager.getInstance("UM_T_WHITELIST");
	
	/**系统公开访问资源名单**/
	private static CacheService openedListCacheService  = CacheManager.getInstance("UM_T_OPENEDLIST");
	
	/**角色维度缓存关系**/
	private static CacheService roleDimeCacheService = CacheManager.getInstance("UM_T_ROLEDIME");
	
	/**日志类型缓存**/
	private static CacheService LogTypeCacheService = CacheManager.getInstance("LOG_T_TYPE");
	
	@Autowired
	private IUmTWhiteListService umTWhiteListService = null;
	
	@Autowired
	private IUmTMethodTaskService umTMethodTaskService = null;
	
	@Autowired
	private IUmTUserRelationService umTUserRelationService = null;
	
	@Autowired
	private  IUmTUserService umTUserService = null;
	
	@Autowired
	public ITmTApplicationConfigService tmTApplicationConfigService = null;
	@Autowired
	private CustomAuthenticationProcessingFilter authenticationProcessingFilter = null;
	
	public CustomAuthenticationProcessingFilter getAuthenticationProcessingFilter() {
		return authenticationProcessingFilter;
	}

	public void setAuthenticationProcessingFilter(
			CustomAuthenticationProcessingFilter authenticationProcessingFilter) {
		this.authenticationProcessingFilter = authenticationProcessingFilter;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		/***********
		 * 外部系统接入认证 
		 * 0绑定方式：根据绑定的参数，获取绑定的用户，并按照该用户在本系统的权限进行控制
		 * 1匿名方式：根据加密串进行解析，与本系统加密算法解析结果一致即可开放
		 */
		int securityType = -1;// 0为绑定方式,例如微信;1为匿名方式,例如营销系统
		// 绑定方式必传参数
		String platId = request.getParameter("platId");
		String userId = request.getParameter("userId");
		String param = request.getParameter("param");
		// 匿名方式必传参数
		String openToken = request.getParameter("openToken");

		// 将当前递交的请求ToolsUtils.toStringHex进行十六进制转换
		try{
			if (platId != null && !"".equals(platId) && userId != null
					&& !"".equals(userId)) {//绑定
				platId = ToolsUtils.toStringHex(platId);
				userId = ToolsUtils.toStringHex(userId);
				
			} else if (param != null && !"".equals(param)&&param.indexOf("-")>-1) {//由于微信无法传&，在此获取param，然后切割为两个参数
				String[] params = param.split("-");
				if(params.length>1){
					platId = ToolsUtils.toStringHex(params[0]);
					userId = ToolsUtils.toStringHex(params[1]);
				}
			} 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			throw new BadCredentialsException("查找绑定用户过程异常！");
		}
		
		
		UmTUserRelation umTUserRelation = null;
		String outerUserCode = null;
		String outerSecurity = null;
		
		/**使用绑定方式，根据platformId和userId从绑定关系表获取绑定关系  处理开始*/
		if(platId != null && !"".equals(platId) && userId != null
				&& !"".equals(userId)){//根据绑定方式判断后，platId和userId都不为空时，则获取绑定关系
			securityType = 0;
			try{
				umTUserRelation = umTUserRelationService.findUmTUserRelationByUserId(platId, userId);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("", e);
				new BadCredentialsException("查找绑定用户过程异常！");
				securityType = -1;//异常或找不到绑定关系，则将绑定标志位还原
			}
			if(umTUserRelation==null){
				securityType = -1;//异常或找不到绑定关系，则将绑定标志位还原
			}else{
				outerUserCode = umTUserRelation.getUserCode();
				outerSecurity = CustomAuthenticationProcessingFilter.OUTER_PASSWORD_PREFIX_BINDING;
			}
		}
		/**使用绑定方式，根据platformId和userId从绑定关系表获取绑定关系  处理结束*/

		
		/**使用匿名方式，将openToken传到UserPasswordEncoder进行认证  处理开始*/
		if (securityType == -1 && openToken != null && !"".equals(openToken)) {//匿名
			securityType = 1;
			outerUserCode = CustomAuthenticationProcessingFilter.ANONYMOUS;
			outerSecurity = CustomAuthenticationProcessingFilter.OUTER_PASSWORD_PREFIX_ANONYMOUS+openToken;
		}
		/**使用匿名方式，将openToken传到UserPasswordEncoder进行认证  处理结束*/

		// 加入认证信息
		if (securityType != -1) {
			// 验证成功的请求
			// 设置公共的session
			if (outerUserCode != null && !"".equals(outerUserCode) && outerSecurity != null
					&& !"".equals(outerSecurity)) {
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
						outerUserCode, outerSecurity);
				HttpSession session = request.getSession();
				session.setAttribute(
						AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY,
						TextUtils.escapeEntities(outerUserCode));
				if(authenticationProcessingFilter==null){
					authenticationProcessingFilter = getAuthenticationProcessingFilter();
				}
				authenticationProcessingFilter.setDetails(request, authRequest);
				AuthenticationManager manager = authenticationProcessingFilter.getAuthManager();
				SecurityContextHolder.getContext().setAuthentication(
						manager.authenticate(authRequest)); // 递交验证
				//chain.doFilter(request, response);
			}
		}
		/*********** 外部系统接入认证 ******************/

		String url = request.getRequestURI().replaceFirst(request.getContextPath(), ""); // 获取当前请求路径,去除上下文
		if (url != null && url.indexOf("?") != -1) {
			url = url.substring(0, url.indexOf("?"));
		}

		if (whiteListCacheService.size() == 0) {
			UmTWhiteList whiteList = new UmTWhiteList();
			whiteList.setValidStatus("1"); // 查询有效状态的白名单配置信息
			List<UmTWhiteList> resultList = null;
			try {
				resultList = umTWhiteListService.findByUmTWhiteList(whiteList,
						1, Integer.MAX_VALUE).getResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("", e);
			}
			for (UmTWhiteList wl : resultList) {
				whiteListCacheService.putCache(wl.getVisitUrl(),
						wl.getVisitUrl());
			}
		}
		
		if (openedListCacheService.size() == 0) {
			try {
				List<UmTMethodTask> resultList = umTMethodTaskService.getOpenedMethodTaskList();
				//System.out.println("whiteList size:" + resultList.size());
				for (UmTMethodTask openedTask : resultList) {
					//以url为key，task对象对value
					openedListCacheService.putCache(openedTask.getMethodCode(),openedTask);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("", e);
			}
		}
       
		if (!url.endsWith(".do") || whiteListCacheService.containsKey(url) || isOpenToAllUrl(url)) {
			//先过去
			chain.doFilter(request, response);
		}
/*		if(true){
			chain.doFilter(request, response);
		}*/ 
		else {
		    String errormessage = null;
			String userCode = null;
			String passWord = null;
			SessionUser sessionUser = null;
			HttpSession session = request.getSession();
			if ((sessionUser = (SessionUser) request.getSession().getAttribute(
					"SessionUser")) == null) {
				// 获取当前用户代码 判断是否为空 modified by liuyatao 2014年8月11日
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if(auth == null){
					//说明认证失败,跳转到登录页面
					//response.sendRedirect(request.getContextPath() + "/login.jsp");
					errormessage = "会话超时，请重新登录";
//					request.setAttribute("errormessage", errormessage);
					throw new BadCredentialsException(errormessage);
				}
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetails) {
					userCode = ((UserDetails) principal).getUsername();
					passWord = ((UserDetails) principal).getPassword();
				} else {
					errormessage = "无法获取当前用户对象";
//					request.setAttribute("errormessage", errormessage);
					throw new BadCredentialsException(errormessage);
				}
			} else {
				userCode = ((SessionUser) request.getSession().getAttribute(
						"SessionUser")).getUserCode();
			}
			GrantedAuthority[] authorityArray = null;
			if (userCode != null && !"".equals(userCode)) {
				/*匿名登录处理*/
				if(CustomAuthenticationProcessingFilter.ANONYMOUS.equals(userCode)){
					UmTUser umTUser = new UmTUser();
					UmTUserId umTUserId = new UmTUserId();
					umTUserId.setUserCode(userCode);
					umTUser.setId(umTUserId);
					umTUser.setUserName(userCode);
					umTUser.setComCode("44030000");
					
					sessionUser =  new SessionUser(umTUser); 						//构造Session用户对象
					session.setAttribute("UserCode", userCode);
					session.setAttribute("UserName", umTUser.getUserName());
					session.setAttribute("ComCode", umTUser.getComCode());
					session.setAttribute("IP", ToolsUtils.getIpAddr(request));
					sessionUser.setIpAddress(ToolsUtils.getIpAddr(request));
					sessionUser.setUserPass(passWord);									//构造对角中设置密码
					session.setAttribute("SessionUser", sessionUser);
					logger.info("匿名登录成功",userCode);
					//授权
					boolean bindFlag = isOpenToBindUserUrl(url);
					//表明需要身份绑定
					if (bindFlag) {
						logger.info("此时需要对匿名用户进行身份绑定");
						logRecord(url, sessionUser);
						if("1".equals(sessionUser.getBindStatus())){
							chain.doFilter(request, response);
							return;
						}else{
							response.sendRedirect(request.getContextPath()
							        + "/pages/um/userBind/UserBindEdit.jsp");
							return;
						}
					}
					logRecord(url, sessionUser);
					chain.doFilter(request, response);
					return;
				}else{//非匿名方式登录
					authorityArray = (GrantedAuthority[]) userRoleCacheService
					.getCache(userRoleCacheService.generateCacheKey(
							"USERROLE", userCode));
					if (authorityArray != null && authorityArray.length > 0) {
						if(sessionUser==null){
							UmTUser umTUser = null;
							TmTApplicationConfig tmTApplicationConfig = null;
							try {
								tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(request.getContextPath().replaceFirst("/", ""));
								umTUser = umTUserService.findUmTUserByUserCode(userCode);
							} catch (Exception e) {
								e.printStackTrace();
								logger.error("", e);
								errormessage = "系统资源繁忙，请稍后再试！";
//								request.setAttribute("errormessage", errormessage);
								throw new BadCredentialsException(errormessage);
							}
							if(tmTApplicationConfig!=null&&umTUser!=null){
								sessionUser =  new SessionUser(umTUser); 						//构造Session用户对象
								session.setAttribute("UserCode", userCode);
								session.setAttribute("UserName", umTUser.getUserName());
								session.setAttribute("ComCode", umTUser.getComCode());
								session.setAttribute("IP", ToolsUtils.getIpAddr(request));
								session.setAttribute(Constants.SERVERCODE, tmTApplicationConfig.getId().getServerCode());
								session.setAttribute(Constants.SERVERNAME, tmTApplicationConfig.getServerName());
								List<UmTGroup> ugl = new ArrayList<UmTGroup>();
								if(ugl!=null&&ugl.size()!=0){
									sessionUser.setGroupCodeList(ugl);
								}
								sessionUser.setBindStatus(umTUser.getBindStatus());
								sessionUser.setIpAddress(ToolsUtils.getIpAddr(request));
								Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
								if(principal instanceof UserDetails){
									sessionUser.setUserPass(((UserDetails)principal).getPassword());									//构造对角中设置密码
								}
								session.setAttribute("SessionUser", sessionUser);
							}else {
								errormessage = "用户【"+userCode+"】不存在";
//								request.setAttribute("errormessage", errormessage);
								throw new UsernameNotFoundException(errormessage);
							}
						}
						String grantResult = isGrantUrl(url, sessionUser,authorityArray);
						/**在此着重处理** add by liuyatao 2014年8月11日**/
						if (CustomAuthenticationProcessingFilter.ROLE_ALLOW.equals(grantResult) || CustomAuthenticationProcessingFilter.PUBLIC_REGIST_ALLOW.equals(grantResult)) {
							logRecord(url, sessionUser);
							chain.doFilter(request, response);
							return;
						}
						//表明需要身份绑定
						if (CustomAuthenticationProcessingFilter.PUBLIC_BIND_ALLOW.equals(grantResult)) {
							logger.info("此时需要身份绑定");
							logRecord(url, sessionUser);
							if("1".equals(sessionUser.getBindStatus())){
								chain.doFilter(request, response);
								return;
							}else{
								response.sendRedirect(request.getContextPath()
								        + "/pages/um/userBind/UserBindEdit.jsp");
								return;
							}
						}
						
						//角色不存在的情况之下，根据直接配置给该用户的功能进行判定处理
						/*List<String> userCacheList = (List<String>)userTaskCacheService.getCache(userTaskCacheService.generateCacheKey(userCode));
						//直接判断用户模型
						if(userCacheList!=null&&userCacheList.size()>0){
							if(userCacheList.contains(url)){
								logRecord(url,sessionUser);
								chain.doFilter(request, response);
								return;
							}
						}*/
						/**
						 * 姚文锋：将userTaskCacheService缓存改为map
						 */
						HashMap<String,UmTMethodTask> taskMap = (HashMap<String,UmTMethodTask>)userTaskCacheService.getCache(userTaskCacheService.generateCacheKey(userCode));;
						if(taskMap!=null&&taskMap.size()>0){
							if(taskMap.containsKey(url)){
								logRecord(url,sessionUser);
								chain.doFilter(request, response);
								return;
							}
						}
						errormessage = "无权限访问URL:用户没有权限访问请求【" + url + "】";
//						request.setAttribute("errormessage", errormessage);
						throw new AccessDeniedException(errormessage);
					}else{
						errormessage = "无权限访问URL:用户没有权限访问请求【" + url + "】";
//						request.setAttribute("errormessage", errormessage);
						throw new AccessDeniedException(errormessage);
					}
				}
			}
		}
	}
	//是否对所有用户开放
	private boolean isOpenToAllUrl(String url){
		if(openedListCacheService.containsKey(url)){
			UmTMethodTask currentTask = (UmTMethodTask) openedListCacheService.getCache(url);
			//判断task中openLevel的值
			if("00".equals(currentTask.getOpenLevel()))
				return true;
		}
		return false;
	}
	//是否需要身份认证
	private boolean isOpenToBindUserUrl(String url){
		if(openedListCacheService.containsKey(url)){
			UmTMethodTask currentTask = (UmTMethodTask) openedListCacheService.getCache(url);
			//判断task中openLevel的值
			if("02".equals(currentTask.getOpenLevel()))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断当前访问的URL能否被该角色进行权限访问
	 * @param url									访问地址URL
	 * @param sessionUser								
	 * @param authorityArray				角色集合
	 * @return										是否有权限访问该URL链接
	 * 2013-9-12上午11:47:08
	 * jiangweiyang
	 */
	@SuppressWarnings("unchecked")
	private String isGrantUrl(String url,SessionUser sessionUser,GrantedAuthority[] authorityArray) throws AccessDeniedException {
		//首先判断所访问的URL是否在系统公开的访问名单中,如果在，则不需要通过角色获取权限，只需看用户身份即可
		if(openedListCacheService.containsKey(url)){
			UmTMethodTask currentTask = (UmTMethodTask) openedListCacheService.getCache(url);
			//判断task中openLevel的值
			if("00".equals(currentTask.getOpenLevel())) {
				return CustomAuthenticationProcessingFilter.PUBLIC_ALL_ALLOW;
			}
			if("01".equals(currentTask.getOpenLevel())) {
				return CustomAuthenticationProcessingFilter.PUBLIC_REGIST_ALLOW;
			}
			if("02".equals(currentTask.getOpenLevel())) {
				return CustomAuthenticationProcessingFilter.PUBLIC_BIND_ALLOW;
			}
		}
		HashMap<String,UmTMethodTask> roleTaskMap = null;
		String comId = sessionUser.getComId();
		//String roleCacheStr = null;
		if((roleTaskMap = (HashMap<String,UmTMethodTask>)urlRoleCacheService.getCache(url))!=null){
			//获取该URL所对应的角色,分隔字符串
			Map<String,Map<String,String>> urlRoleMap = null;
			boolean [] authFlag = new boolean[authorityArray.length];					//设置认证标志位(解决一个用户多个角色,对各角色的授权信息进行或运算)
			int index  = 0;
			//对授权标志authFlag进行或运算,计算出该用户是否有权限登录系统
			boolean result = false;
			for(GrantedAuthority ga : authorityArray){
				if(roleTaskMap.containsKey(ga.getAuthority())){
					/**其所在角色在包含的字符串中**/
					urlRoleMap = (Map<String,Map<String,String>>) roleDimeCacheService.getCache(comId);
					if(urlRoleMap!=null){
						Map<String,String> roleDimeMap = urlRoleMap.get(url);
						if(roleDimeMap!=null){
							if("sub".equals(roleDimeMap.get(ga.getAuthority()))){
								authFlag[index++] = false;
							}else {
								authFlag[index++] = true;
							}
						}else {
							authFlag[index++] = true;
						}
					}else {
						authFlag[index++] = true;
					}
				}else {
					/**其所在角色不在包含的字符串中**/
					urlRoleMap = (Map<String,Map<String,String>>)roleDimeCacheService.getCache(comId);
					if(urlRoleMap!=null){
						Map<String,String> roleDimeMap = urlRoleMap.get(url);
						if(roleDimeMap!=null){
							if("add".equals(roleDimeMap.get(ga.getAuthority()))){
								authFlag[index++]  = true;
							}else {
								authFlag[index++] = false;
							}
							
						}else {
							authFlag[index++] = false;
						}
					}else {
						authFlag[index++] = false;
					}
				}
			}
			//对所有的权限验证Value进行或运算得出最后值
			for(boolean value : authFlag){
				result  = result || value;
			}
			//若通过了角色授权,返回1
			if(result){
				return CustomAuthenticationProcessingFilter.ROLE_ALLOW;
			}
		}
		return CustomAuthenticationProcessingFilter.ROLE_ALLOW;
	}
	
	public void logRecord(String url,SessionUser sessionUser){
		if(LogTypeCacheService.containsKey(url)){
			LoGTTYPE lt = (LoGTTYPE) LogTypeCacheService.getCache(url);
			String message = lt.getOperateTypeCName()+"成功";
			Logger	logger = (Logger) LoggerFactory.getLogger(this.getClass());
			logger.info(message,lt.getId().getOperateTypeId(),sessionUser.getUserCode(),sessionUser.getUserName(),sessionUser.getIpAddress(),sessionUser.getComId());
		}
		
	}
	
	@Override
	public void afterPropertiesSet() throws ServletException {}

}
