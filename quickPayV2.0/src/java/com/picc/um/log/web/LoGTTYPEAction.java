/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.log.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.log.schema.model.LoGTTYPEId;
import com.picc.um.log.service.facade.ILoGTTYPEService;

/**
 * 日志类型Action处理层
 * @author 杨联
 */
@SuppressWarnings("serial")
public class LoGTTYPEAction extends Struts2Action{
	
	private ILoGTTYPEService loGTTYPEService;	
	public void setLoGTTYPEService(ILoGTTYPEService loGTTYPEService) {
		this.loGTTYPEService = loGTTYPEService;
	}

	public ILoGTTYPEService getLoGTTYPEService() {
		return loGTTYPEService;
	}
	
	private LoGTTYPE loGTTYPE;
	
	private LoGTTYPEId id;
	
	/** 操作类型 **/
	private String operateType;
	/** LoGTTYPE getter/setter **/
	public LoGTTYPE getLoGTTYPE() {
		return this.loGTTYPE;
	}
	
	public void setLoGTTYPE(LoGTTYPE loGTTYPE) {
		this.loGTTYPE = loGTTYPE;
	}
	/** LoGTTYPEId getter/setter **/
	public LoGTTYPEId getId() {
		return this.id;
	}
	
	public void setId(LoGTTYPEId id) {
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
	private String operateTypeId;
	public String getOperateTypeId() {
		return this.operateTypeId;
	}
	
	public void setOperateTypeId(String operateTypeId) {
		this.operateTypeId = operateTypeId;
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
	 * LoGTTYPE查询，根据loGTTYPE带过来的值为查询条件进行查询。
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
			Page resultPage = loGTTYPEService.findByLoGTTYPE(loGTTYPE, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	


	/**
	 * 准备更新LoGTTYPE信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		loGTTYPE = loGTTYPEService.findLoGTTYPEByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新LoGTTYPE信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		loGTTYPEService.updateLoGTTYPE(loGTTYPE);
		return SUCCESS;
	}


	/**
	 * 准备增加LoGTTYPE信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增LoGTTYPE信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		loGTTYPEService.saveLoGTTYPE(loGTTYPE);
		return SUCCESS;
	}



	/**
	 * 删除LoGTTYPE信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				loGTTYPEService.deleteByPK(id);
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
	 * 查看LoGTTYPE信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		loGTTYPE = loGTTYPEService.findLoGTTYPEByPK(id);
		return SUCCESS;
	}
}
