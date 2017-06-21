/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.Date;

import com.picc.um.log.schema.model.LoGTINFO;
import com.picc.um.log.schema.model.LoGTINFOId;
import com.picc.um.log.service.facade.ILoGTINFOService;

/**
 * 日志信息Action处理层
 * @author 杨联
 */
@SuppressWarnings("serial")
public class LoGTINFOAction extends Struts2Action{
	
	private ILoGTINFOService loGTINFOService;	
	public void setLoGTINFOService(ILoGTINFOService loGTINFOService) {
		this.loGTINFOService = loGTINFOService;
	}

	public ILoGTINFOService getLoGTINFOService() {
		return loGTINFOService;
	}
	
	private LoGTINFO loGTINFO;
	
	private LoGTINFOId id;
	
	private Date operateTime2;
	
	
	
	public Date getOperateTime2() {
		return operateTime2;
	}

	public void setOperateTime2(Date operateTime2) {
		this.operateTime2 = operateTime2;
	}

	/** 操作类型 **/
	private String operateType;
	/** LoGTINFO getter/setter **/
	public LoGTINFO getLoGTINFO() {
		return this.loGTINFO;
	}
	
	public void setLoGTINFO(LoGTINFO loGTINFO) {
		this.loGTINFO = loGTINFO;
	}
	/** LoGTINFOId getter/setter **/
	public LoGTINFOId getId() {
		return this.id;
	}
	
	public void setId(LoGTINFOId id) {
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
	private String logId;
	public String getLogId() {
		return this.logId;
	}
	
	public void setLogId(String logId) {
		this.logId = logId;
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
	 * LoGTINFO查询，根据loGTINFO带过来的值为查询条件进行查询。
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
			Page resultPage = loGTINFOService.findByLoGTINFO(loGTINFO,operateTime2,page,rows);
			
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	


	/**
	 * 准备更新LoGTINFO信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		loGTINFO = loGTINFOService.findLoGTINFOByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新LoGTINFO信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		loGTINFOService.updateLoGTINFO(loGTINFO);
		return SUCCESS;
	}


	/**
	 * 准备增加LoGTINFO信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增LoGTINFO信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		loGTINFOService.saveLoGTINFO(loGTINFO);
		return SUCCESS;
	}



	/**
	 * 删除LoGTINFO信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				loGTINFOService.deleteByPK(id);
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
	 * 查看LoGTINFO信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		loGTINFO = loGTINFOService.findLoGTINFOByPK(id);
		return SUCCESS;
	}
}
