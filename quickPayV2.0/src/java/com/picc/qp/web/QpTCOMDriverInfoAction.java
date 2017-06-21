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

import com.picc.qp.schema.model.QpTCOMDriverInfo;
import com.picc.qp.schema.model.QpTCOMDriverInfoId;
import com.picc.qp.service.facade.IQpTCOMDriverInfoService;


public class QpTCOMDriverInfoAction extends Struts2Action{
	
	private IQpTCOMDriverInfoService qpTCOMDriverInfoService;	
	public void setQpTCOMDriverInfoService(IQpTCOMDriverInfoService qpTCOMDriverInfoService) {
		this.qpTCOMDriverInfoService = qpTCOMDriverInfoService;
	}

	public IQpTCOMDriverInfoService getQpTCOMDriverInfoService() {
		return qpTCOMDriverInfoService;
	}
	
	private QpTCOMDriverInfo qpTCOMDriverInfo;
	
	private QpTCOMDriverInfoId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTCOMDriverInfo getter/setter **/
	public QpTCOMDriverInfo getQpTCOMDriverInfo() {
		return this.qpTCOMDriverInfo;
	}
	
	public void setQpTCOMDriverInfo(QpTCOMDriverInfo qpTCOMDriverInfo) {
		this.qpTCOMDriverInfo = qpTCOMDriverInfo;
	}
	/** QpTCOMDriverInfoId getter/setter **/
	public QpTCOMDriverInfoId getId() {
		return this.id;
	}
	
	public void setId(QpTCOMDriverInfoId id) {
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
	private String driverInfoId;
	public String getDriverInfoId() {
		return this.driverInfoId;
	}
	
	public void setDriverInfoId(String driverInfoId) {
		this.driverInfoId = driverInfoId;
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
	 * QpTCOMDriverInfo查询，根据qpTCOMDriverInfo带过来的值为查询条件进行查询。
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
			Page resultPage = qpTCOMDriverInfoService.findByQpTCOMDriverInfo(qpTCOMDriverInfo, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTCOMDriverInfo信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTCOMDriverInfo = qpTCOMDriverInfoService.findQpTCOMDriverInfoByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTCOMDriverInfo信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTCOMDriverInfoService.updateQpTCOMDriverInfo(qpTCOMDriverInfo);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTCOMDriverInfo信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTCOMDriverInfo信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTCOMDriverInfoService.saveQpTCOMDriverInfo(qpTCOMDriverInfo);
		return SUCCESS;
	}



	/**
	 * 删除QpTCOMDriverInfo信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTCOMDriverInfoService.deleteByPK(id);
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
	 * 查看QpTCOMDriverInfo信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTCOMDriverInfo = qpTCOMDriverInfoService.findQpTCOMDriverInfoByPK(id);
		return SUCCESS;
	}
}
