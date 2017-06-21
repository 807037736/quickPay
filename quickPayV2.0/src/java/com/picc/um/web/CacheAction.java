package com.picc.um.web;

import ins.framework.cache.info.CacheManagerInfo;
import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.List;

import net.sf.json.JSONObject;

import com.picc.um.service.facade.ICacheService;

/**
 * 缓存服务Action处理层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class CacheAction extends Struts2Action {
	private ICacheService cacheService;
	private List<CacheManagerInfo> cacheManagerInfoList;
	private CacheManagerInfo cacheManagerInfo;
	private String cacheName;
	private List<String> cacheNameArray;
	
	
	private String userCode;
	
	private String roleCode;									//角色代码
	
	public List<String> getCacheNameArray() {
		return cacheNameArray;
	}

	public void setCacheNameArray(List<String> cacheNameArray) {
		this.cacheNameArray = cacheNameArray;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public CacheManagerInfo getCacheManagerInfo() {
		return cacheManagerInfo;
	}

	public void setCacheManagerInfo(CacheManagerInfo cacheManagerInfo) {
		this.cacheManagerInfo = cacheManagerInfo;
	}

	public List<CacheManagerInfo> getCacheManagerInfoList() {
		return cacheManagerInfoList;
	}

	public void setCacheManagerInfoList(
			List<CacheManagerInfo> cacheManagerInfoList) {
		this.cacheManagerInfoList = cacheManagerInfoList;
	}

	public ICacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(ICacheService cacheService) {
		this.cacheService = cacheService;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getRoleCode() {
		return roleCode;
	}
	
	public String queryAll() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		Page resultPage = cacheService.findCacheManager(cacheManagerInfo, page,
				rows);
		// cacheManagerInfoList = cacheService.findAllCacheManager();

		this.writeEasyUiData(resultPage);
		return NONE;

	}

	public String prepareQuery() throws Exception {

		return SUCCESS;
	}

	public String delete() throws Exception {
		
		for(String cn:cacheNameArray){
		cacheManagerInfo = cacheService
				.findCacheManagerInfoByCacheName(cn);
		cacheService.deleteCacheManager(cacheManagerInfo);
		}
		return NONE;
	}
	
	public String reloadUserTaskCache() throws Exception {
		JSONObject resultValue = new JSONObject();
		if(userCode!=null&&!"".equals(userCode)){
			try{
				cacheService.reloadUserTaskCache(userCode);
				resultValue.put("success", true);
			}catch(Exception ex){
				resultValue.put("success", false);
				resultValue.put("errorMsg", ex.getMessage());
			}finally{
				this.writeEasyUiData(resultValue);
			}
		}
		return NONE;
	}
	
	
	public String reloadRoleTaskCache() throws Exception {
		JSONObject resultValue = new JSONObject();
		cacheService.reloadUrlRoleCache();
		resultValue.put("success", true);
		this.writeEasyUiData(resultValue);
		return NONE;
	}
	
	
	
	
}
