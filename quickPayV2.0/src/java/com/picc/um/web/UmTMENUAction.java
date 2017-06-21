/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;


import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.picc.um.schema.model.UmTMENU;
import com.picc.um.schema.model.UmTMENUId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTMENUService;

/**
 * 菜单Action处理层
 * @author 杨联
 */
@SuppressWarnings("serial")
public class UmTMENUAction extends Struts2Action{
	
	private IUmTMENUService umTMENUService;	
	private SessionUser user;
	private static CacheService userMenuCacheService = CacheManager.getInstance("UM_T_USERMENU");
	 
	private String menu;    //营销系统中的菜单
	private String cognosMenu;  //Cognos菜单

	public SessionUser getUser() {
		return user;
	}

	public void setUser(SessionUser user) {
		this.user = user;
	}

	public void setUmTMENUService(IUmTMENUService umTMENUService) {
		this.umTMENUService = umTMENUService;
	}

	public IUmTMENUService getUmTMENUService() {
		return umTMENUService;
	}


	private UmTMENU umTMENU;
	
	private UmTMENUId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTMENU getter/setter **/
	public UmTMENU getUmTMENU() {
		return this.umTMENU;
	}
	
	public void setUmTMENU(UmTMENU umTMENU) {
		this.umTMENU = umTMENU;
	}
	/** UmTMENUId getter/setter **/
	public UmTMENUId getId() {
		return this.id;
	}
	
	public void setId(UmTMENUId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	/** 主键对象 */
	private String menuId;
	public String getMenuId() {
		return this.menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public String getCognosMenu() {
		return cognosMenu;
	}

	public void setCognosMenu(String cognosMenu) {
		this.cognosMenu = cognosMenu;
	}

	/****************************Function Start*******************************/
	
	/**
	 * 准备初始化方法，用于首页面的菜单树展示
	 * 
	 * @return
	 */
	public String prepareAll() throws Exception {	
		//menu=umTMENUService.findAllMenu(false);
		try{
		    user = getUserFromSession();
		    serverName=new String(serverName.getBytes("ISO-8859-1"),"UTF-8");
			String usercode = user.getUserCode();
			
			//serverCode = (String)getSession().getAttribute(Constants.SERVERCODE);
			//判断是内部系统还是外部系统
//			String serverType = (String)getSession().getAttribute(Constants.SERVERTYPE);
			String serverType = "01";
			//获取访问系统的菜单
			//menu=(String) userMenuCacheService.getCache(usercode);
			menu = (String)umTMENUService.findMenuByUsercode(usercode,user.getComId(),serverCode,serverType,false);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		logger.info("#######创建的菜单树########{}",menu);
		return SUCCESS;
	}
	

	

	/**
	 * 准备查询
	 * @return
	 * @throws Exception
	 */
	public String prepareQuery() throws Exception {		
		
		
		return SUCCESS;
	}
	
	/**
	 * UmTMENU查询，根据umTMENU带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = umTMENUService.findByUmTMENU(umTMENU, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTMENU信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		menu=umTMENUService.findAllMenu(true);
		umTMENU = umTMENUService.findUmTMENUByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTMENU信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		umTMENUService.updateUmTMENU(umTMENU,userCode);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTMENU信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		menu=umTMENUService.findAllMenu(true);
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTMENU信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		
		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		umTMENUService.saveUmTMENU(umTMENU,userCode);
		return SUCCESS;
	}



	/**
	 * 删除UmTMENU信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTMENUService.deleteByPK(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);

		}
		return NONE;
	}



	/**
	 * 查看UmTMENU信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		umTMENU = umTMENUService.findUmTMENUByPK(id);
		return SUCCESS;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	
	/**
	 * 获取Cognos菜单
	 * @return
	 */
	private String loadCognosMenu() {
		return null;
	}
}
