/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.tm.common.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;
import com.picc.tm.common.service.facade.ITmTAppServiceConfigService;

/**
 * 服务环境变量action类
 * @author moxiaoguang 2013-12-12
 *
 */
public class TmTAppServiceConfigAction extends Struts2Action{
	
	private ITmTAppServiceConfigService tmTAppServiceConfigService;	
	public void setTmTAppServiceConfigService(ITmTAppServiceConfigService tmTAppServiceConfigService) {
		this.tmTAppServiceConfigService = tmTAppServiceConfigService;
	}

	public ITmTAppServiceConfigService getTmTAppServiceConfigService() {
		return tmTAppServiceConfigService;
	}
	
	private TmTAppServiceConfig tmTAppServiceConfig;
	
	private TmTAppServiceConfigId id;
	
	/** 操作类型 **/
	private String operateType;
	/** TmTAppServiceConfig getter/setter **/
	public TmTAppServiceConfig getTmTAppServiceConfig() {
		return this.tmTAppServiceConfig;
	}
	
	public void setTmTAppServiceConfig(TmTAppServiceConfig tmTAppServiceConfig) {
		this.tmTAppServiceConfig = tmTAppServiceConfig;
	}
	/** TmTAppServiceConfigId getter/setter **/
	public TmTAppServiceConfigId getId() {
		return this.id;
	}
	
	public void setId(TmTAppServiceConfigId id) {
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
	private String serverCode;
	public String getServerCode() {
		return this.serverCode;
	}
	
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	/** 主键对象 */
	private String environmentCode;
	public String getEnvironmentCode() {
		return this.environmentCode;
	}
	
	public void setEnvironmentCode(String environmentCode) {
		this.environmentCode = environmentCode;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * TmTAppServiceConfig查询，根据tmTAppServiceConfig带过来的值为查询条件进行查询。
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
			Page resultPage = tmTAppServiceConfigService.findByTmTAppServiceConfig(tmTAppServiceConfig, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新TmTAppServiceConfig信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		tmTAppServiceConfig = tmTAppServiceConfigService.findTmTAppServiceConfigByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新TmTAppServiceConfig信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		tmTAppServiceConfigService.updateTmTAppServiceConfig(tmTAppServiceConfig);
		return SUCCESS;
	}


	/**
	 * 准备增加TmTAppServiceConfig信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增TmTAppServiceConfig信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		tmTAppServiceConfigService.saveTmTAppServiceConfig(tmTAppServiceConfig);
		return SUCCESS;
	}



	/**
	 * 删除TmTAppServiceConfig信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				tmTAppServiceConfigService.deleteByPK(id);
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
	 * 查看TmTAppServiceConfig信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		tmTAppServiceConfig = tmTAppServiceConfigService.findTmTAppServiceConfigByPK(id);
		return SUCCESS;
	}
}
