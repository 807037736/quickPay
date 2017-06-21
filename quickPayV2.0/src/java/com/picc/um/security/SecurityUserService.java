package com.picc.um.security;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.ServiceFactory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;

import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * Security实现UserDetailService接口类
 * 主要用于返回根据用户名获取用户实体对象的处理
 * @author 姜卫洋
 *
 */
public class SecurityUserService implements UserDetailsService{

	private static Logger logger = LoggerFactory.getLogger(SecurityUserService.class);
	
	/**
	 * 用户信息缓存(Account用户帐户信息,UserRole用户角色信息)
	 * 每天进行数据更新同步,有效时间为一天
	 * 当用户帐户、角色数据发生变化时,同时更新
	 */
	private static CacheService userCacheService = CacheManager.getInstance("UM_T_USER");
	
	private static CacheService userRoleCacheService = CacheManager.getInstance("UM_T_USERROLE");
	
	@Autowired
	private IUmTRoleService umTRoleService = null;
	
	@Autowired
	private IUmTAccountService umTAccountService = null;
	
	@Autowired
	private IUmTUserService umTUserService = null;
	
	/*
	 * 谁写的？ userName = userCode？？？？ 
	 * 此处userName 其实是userCode
	 */
	public UserDetails loadUserByUsername(String userName)
			throws AuthenticationException, DataAccessException {
		if (userName == null || "".equals(userName.trim())) {
			return null;
		}else if(CustomAuthenticationProcessingFilter.ANONYMOUS.equals(userName)){
			GrantedAuthority[] authorityList = new GrantedAuthority[1];
			authorityList[0] = new GrantedAuthorityImpl(CustomAuthenticationProcessingFilter.ANONYMOUS);
			return new User(userName, DESUtils.encodeSHA(DESUtils.getEncryptStr(CustomAuthenticationProcessingFilter.ANONYMOUS)),
					true, true, true, true, authorityList);
		}else {
			//根据用户名代码获取用户帐户对象(用户登录过程中每次均从数据库中读取用户帐户信息)
			UmTAccount account  = null;
			if (umTAccountService == null) 
				umTAccountService = (IUmTAccountService)ServiceFactory.getService("umTAccountService");
			try {
				account = umTAccountService.findUmTAccountByUserCode(userName);
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}																		//根据帐户接口查询帐户对象
			if(account==null){
				throw new BadCredentialsException("帐户【"+userName+"】不存在");
			}else {
			    if(!"1".equals(account.getValidStatus())){
				throw new BadCredentialsException("帐户无效【"+userName+"】");
			    }
				userCacheService.putCache(userCacheService.generateCacheKey("ACCOUNT",userName), account);						//缓存帐户数据
			}
			
			UmTUser umTUser = null;
			if (umTUserService == null) 
			    umTUserService = (IUmTUserService)ServiceFactory.getService("umTUserService");
			try {
			    	UmTUserId id = new UmTUserId(userName);
			    	umTUser = umTUserService.findUmTUserByPK(id);
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
			if(umTUser==null){
				throw new BadCredentialsException("帐户【"+userName+"】不存在");
			}else if(!"1".equals(umTUser.getValidStatus())){
				throw new BadCredentialsException("帐户无效【"+userName+"】");
			}
			/**帐户存在的情况下,缓存该用户角色信息**/
			GrantedAuthority[] authorityList = new GrantedAuthority[0];
			List<UmTRole> roleList = null;
			//缓存中没有用户角色数据(用户登录过程中每次均从数据库中读取用户角色信息)
			if (umTRoleService == null) 
				umTRoleService = (IUmTRoleService)ServiceFactory.getService("umTRoleService");
			roleList = umTRoleService.findByUserCode(userName);
			if(roleList!=null&&roleList.size()>0){
				authorityList = new GrantedAuthority[roleList.size()+1];					//构造角色集合
				int index = 0;
				for(UmTRole role : roleList){
					authorityList[index++] = new GrantedAuthorityImpl(role.getRoleCode());								
				}
				userRoleCacheService.putCache(userRoleCacheService.generateCacheKey("USERROLE",userName), authorityList);
				//当前用户验证成功(添加一个已登录用户的ROLE_USER角色)
				authorityList[roleList.size()] = new GrantedAuthorityImpl("ROLE_USER");
			}
			if ("1".equals(account.getValidStatus())) {
				return new User(userName, account.getPassword(),
						true, true, true, true, authorityList);
			} else {
				return new User(userName, account.getPassword(),
						false, true, true, true, authorityList);
			}
		}
	}
}
