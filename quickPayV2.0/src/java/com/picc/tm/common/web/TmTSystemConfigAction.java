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

import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.schema.model.TmTSystemConfigId;
import com.picc.tm.common.service.facade.ITmTSystemConfigService;
import com.picc.um.schema.vo.SessionUser;

/**
 * 系统参数配置action类
 * @author moxiaoguang 更新 2013-12-12
 *
 */
public class TmTSystemConfigAction extends Struts2Action{
	
	private ITmTSystemConfigService tmTSystemConfigService;	
	public void setTmTSystemConfigService(ITmTSystemConfigService tmTSystemConfigService) {
		this.tmTSystemConfigService = tmTSystemConfigService;
	}

	public ITmTSystemConfigService getTmTSystemConfigService() {
		return tmTSystemConfigService;
	}
	
	private TmTSystemConfig tmTSystemConfig;
	
	private TmTSystemConfigId id;
	
	/** 操作类型 **/
	private String operateType;
	/** TmTSystemConfig getter/setter **/
	public TmTSystemConfig getTmTSystemConfig() {
		return this.tmTSystemConfig;
	}
	
	public void setTmTSystemConfig(TmTSystemConfig tmTSystemConfig) {
		this.tmTSystemConfig = tmTSystemConfig;
	}
	/** TmTSystemConfigId getter/setter **/
	public TmTSystemConfigId getId() {
		return this.id;
	}
	
	public void setId(TmTSystemConfigId id) {
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
	private String configId;
	public String getConfigId() {
		return this.configId;
	}
	
	public void setConfigId(String configId) {
		this.configId = configId;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String prepareQuery() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * TmTSystemConfig查询，根据tmTSystemConfig带过来的值为查询条件进行查询。
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String query() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			SessionUser sessionUser = getUserFromSession();
			Page resultPage = tmTSystemConfigService.findByTmTSystemConfig(tmTSystemConfig, page, rows,sessionUser);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新TmTSystemConfig信息
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		tmTSystemConfig = tmTSystemConfigService.findTmTSystemConfigByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新TmTSystemConfig信息
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String update() throws Exception { 
		SessionUser sessionUser = getUserFromSession();
		if(null!=tmTSystemConfig)
		{
			tmTSystemConfig.setUpdaterCode(sessionUser.getUserCode());
		}
		tmTSystemConfigService.updateTmTSystemConfig(tmTSystemConfig);
		return SUCCESS;
	}


	/**
	 * 准备增加TmTSystemConfig信息
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增TmTSystemConfig信息
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String add() throws Exception {
		String comId4 = this.tmTSystemConfig.getComId();
		if(null==comId4||"".equals(comId4))
		{
			comId4 = "00000000";
		}
		configId = tmTSystemConfigService.genId(comId4);
		id = new TmTSystemConfigId();
		id.setConfigId(configId);
		tmTSystemConfig.setId(id);
		tmTSystemConfigService.saveTmTSystemConfig(tmTSystemConfig);
		return SUCCESS;
	}



	/**
	 * 删除TmTSystemConfig信息
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				tmTSystemConfigService.deleteByPK(id);
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
	 * 查看TmTSystemConfig信息方法
	 * 
	 * @return
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public String view() throws Exception {
		operateType = "view";
		tmTSystemConfig = tmTSystemConfigService.findTmTSystemConfigByPK(id);
		return SUCCESS;
	}
}
