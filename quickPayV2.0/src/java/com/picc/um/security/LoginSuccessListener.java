package com.picc.um.security;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.ServiceFactory;

import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;
import org.springframework.security.userdetails.UserDetails;

import ch.qos.logback.classic.Logger;

import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTMENUService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTTaskService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 登录功能事件监控器(当用户登录系统之后进行触发的后置过程)
 * @author 姜卫洋
 * 
 */
public class LoginSuccessListener implements ApplicationListener {
	
	private static Logger logger = (Logger)LoggerFactory.getLogger(LoginSuccessListener.class);

	@Autowired
	private IUmTMethodTaskService umTMethodTaskService; // 访问URL操作对象

	@Autowired
	private IUmTMENUService umTMENUService = null;

	@Autowired
	private IUmTUserService umTUserService = null;
	
	@Autowired
	private IUmTTaskService umTTaskService = null;
	
	
	
	/** 用户与可访问URL对应映射关联 **/
	private static CacheService userTaskCacheService = CacheManager
			.getInstance("UM_T_USERTASK");

	private static CacheService userMenuCacheService = CacheManager
			.getInstance("UM_T_USERMENU");
	
	private static CacheService userSystemCacheService = CacheManager
			.getInstance("UM_T_USERSYSTEM");

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
			final UserDetails userDetails = (UserDetails) authEvent
					.getAuthentication().getPrincipal();
			String userName = userDetails.getUsername();
			if(userName!=null&&CustomAuthenticationProcessingFilter.ANONYMOUS.equals(userName)){
				return;
			}
			
			/*用户URL的缓存*/
			// 用户重新登录时,重新缓存该用户配置的功能访问URL
/*			List<String> urlList = null;
			try {
				urlList = umTMethodTaskService
						.getMethodCodeByUserCode(userName);

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (urlList != null && urlList.size() > 0) {
				List<String> urlListResult = new ArrayList<String>();
				for (String url : urlList) {
					if (url != null) {
						if(url.indexOf("?") != -1)
							url = url.substring(0, url.indexOf("?"));
						urlListResult.add(url);
					}
				}
				userURLCacheService.putCache(userName,
						urlListResult);
			}*/
			/**
			 * 姚文锋：将userTaskCacheService缓存改为map
			 */
			HashMap<String,UmTMethodTask> taskMap = null;
			try {
				taskMap = umTMethodTaskService.getMethodTaskMapByUserCode(userName);
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
			if (taskMap != null){
				userTaskCacheService.clearCache(userName);
				userTaskCacheService.putCache(userName, taskMap);
			}

			/*用户系统权限的缓存start*/
			if(umTTaskService == null)
				umTTaskService = (IUmTTaskService)ServiceFactory
				.getService("umTTaskService");
			try{
				final String comCode = umTUserService.findUmTUserByUserCode(userName).getComCode();
				userSystemCacheService.putCache(userName,
						umTTaskService.findAppByUser(userName, SessionUser.getComIdByComCode(comCode), "menu"));
			}catch(Exception e){
				e.printStackTrace();
				logger.error("", e);
			}
			/*用户系统权限的缓存end*/
			
			/*用户菜单缓存start*/
			/*if (umTMENUService == null)
				umTMENUService = (IUmTMENUService) ServiceFactory
						.getService("umTMENUService");
			try {
				final String comCode = umTUserService.findUmTUserByUserCode(userDetails.getUsername()).getComCode();
				userMenuCacheService.putCache(userDetails.getUsername(),
						umTMENUService.findMnueByUsercode(userDetails.getUsername(), SessionUser.getComIdByComCode(comCode), false));
				//话务平台登录接口
				new Thread(new Runnable(){
					public void run() {
						try {
							cMSInterfaceForSysService.conLoginCMS(userDetails.getUsername(), comCode);
						} catch (IOException e) {
							logger.warn("Login HW Failured:"+e.getMessage());
						}		
					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		}
	}
}
