/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.qp.schema.model.QpTTPPolice;
import com.picc.qp.schema.model.QpTTPPoliceId;
import com.picc.qp.service.facade.IQpTTPPoliceService;


public class QpTTPPoliceAction extends Struts2Action{
	
	private IQpTTPPoliceService qpTTPPoliceService;	
	public void setQpTTPPoliceService(IQpTTPPoliceService qpTTPPoliceService) {
		this.qpTTPPoliceService = qpTTPPoliceService;
	}

	public IQpTTPPoliceService getQpTTPPoliceService() {
		return qpTTPPoliceService;
	}
	
	private QpTTPPolice qpTTPPolice;
	
	private QpTTPPoliceId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTTPPolice getter/setter **/
	public QpTTPPolice getQpTTPPolice() {
		return this.qpTTPPolice;
	}
	
	public void setQpTTPPolice(QpTTPPolice qpTTPPolice) {
		this.qpTTPPolice = qpTTPPolice;
	}
	/** QpTTPPoliceId getter/setter **/
	public QpTTPPoliceId getId() {
		return this.id;
	}
	
	public void setId(QpTTPPoliceId id) {
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
	private String policeId;
	public String getPoliceId() {
		return this.policeId;
	}
	
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
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
	 * QpTTPPolice查询，根据qpTTPPolice带过来的值为查询条件进行查询。
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
			Page resultPage = qpTTPPoliceService.findByQpTTPPolice(qpTTPPolice, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTTPPolice信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTTPPolice = qpTTPPoliceService.findQpTTPPoliceByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTTPPolice信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTTPPoliceService.updateQpTTPPolice(qpTTPPolice);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTTPPolice信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTTPPolice信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTTPPoliceService.saveQpTTPPolice(qpTTPPolice);
		return SUCCESS;
	}



	/**
	 * 删除QpTTPPolice信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTTPPoliceService.deleteByPK(id);
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
	 * 查看QpTTPPolice信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTTPPolice = qpTTPPoliceService.findQpTTPPoliceByPK(id);
		return SUCCESS;
	}
}
