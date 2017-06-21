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

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.service.facade.IQpTCOMCityService;


public class QpTCOMCityAction extends Struts2Action{
	
	private IQpTCOMCityService qpTCOMCityService;	
	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}

	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}
	
	private QpTCOMCity qpTCOMCity;
	
	private QpTCOMCityId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTCOMCity getter/setter **/
	public QpTCOMCity getQpTCOMCity() {
		return this.qpTCOMCity;
	}
	
	public void setQpTCOMCity(QpTCOMCity qpTCOMCity) {
		this.qpTCOMCity = qpTCOMCity;
	}
	/** QpTCOMCityId getter/setter **/
	public QpTCOMCityId getId() {
		return this.id;
	}
	
	public void setId(QpTCOMCityId id) {
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
	private String cityId;
	public String getCityId() {
		return this.cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
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
	 * QpTCOMCity查询，根据qpTCOMCity带过来的值为查询条件进行查询。
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
			Page resultPage = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTCOMCity信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTCOMCity = qpTCOMCityService.findQpTCOMCityByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTCOMCity信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTCOMCityService.updateQpTCOMCity(qpTCOMCity);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTCOMCity信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTCOMCity信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTCOMCityService.saveQpTCOMCity(qpTCOMCity);
		return SUCCESS;
	}



	/**
	 * 删除QpTCOMCity信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTCOMCityService.deleteByPK(id);
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
	 * 查看QpTCOMCity信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTCOMCity = qpTCOMCityService.findQpTCOMCityByPK(id);
		return SUCCESS;
	}
	
}
