/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import com.picc.um.schema.model.UmTUserHistory;
import com.picc.um.schema.model.UmTUserHistoryId;
import com.picc.um.service.facade.IUmTUserHistoryService;


public class UmTUserHistoryAction extends Struts2Action{
	
	private IUmTUserHistoryService umTUserHistoryService;	
	public void setUmTUserHistoryService(IUmTUserHistoryService umTUserHistoryService) {
		this.umTUserHistoryService = umTUserHistoryService;
	}

	public IUmTUserHistoryService getUmTUserHistoryService() {
		return umTUserHistoryService;
	}
	
	private UmTUserHistory umTUserHistory;
	
	private UmTUserHistoryId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTUserHistory getter/setter **/
	public UmTUserHistory getUmTUserHistory() {
		return this.umTUserHistory;
	}
	
	public void setUmTUserHistory(UmTUserHistory umTUserHistory) {
		this.umTUserHistory = umTUserHistory;
	}
	/** UmTUserHistoryId getter/setter **/
	public UmTUserHistoryId getId() {
		return this.id;
	}
	
	public void setId(UmTUserHistoryId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		logger.debug("正在执行UmTUserHistoryAction.prepareQuery，准备进入UmTUserHistory查询方法");
		
		return SUCCESS;
	}
	
	/**
	 * UmTUserHistory查询，根据umTUserHistory带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		logger.debug("正在执行UmTUserHistoryAction.query，查询满足条件的UmTUserHistory信息");
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}


		try {
			Page resultPage = umTUserHistoryService.findByUmTUserHistory(umTUserHistory, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTUserHistory信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		logger.debug("正在执行UmTUserHistoryAction.prepareUpdate，准备更新UmTUserHistory信息");
		operateType = "update";
		umTUserHistory = umTUserHistoryService.findUmTUserHistoryByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserHistory信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		logger.debug("正在执行UmTUserHistoryAction.update，更新UmTUserHistory信息");
		umTUserHistoryService.updateUmTUserHistory(umTUserHistory);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTUserHistory信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		logger.debug("正在执行UmTUserHistoryAction.prepareAdd，准备新增UmTUserHistory信息");
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTUserHistory信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		logger.debug("正在执行UmTUserHistoryAction.prepareAdd，新增UmTUserHistory信息");

		umTUserHistoryService.saveUmTUserHistory(umTUserHistory);
		return SUCCESS;
	}



	/**
	 * 删除UmTUserHistory信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				logger.debug("正在执行UmTUserHistoryAction.delete，删除" +id + "UmTUserHistory信息");
				umTUserHistoryService.deleteByPK(id);
				this.writeJSONMsg(SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}



	/**
	 * 查看UmTUserHistory信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		logger.debug("正在执行UmTUserHistoryAction.view,查看UmTUserHistory信息");
		operateType = "view";
		umTUserHistory = umTUserHistoryService.findUmTUserHistoryByPK(id);
		return SUCCESS;
	}
}
