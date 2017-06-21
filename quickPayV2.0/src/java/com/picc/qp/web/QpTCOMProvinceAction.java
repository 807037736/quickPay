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

import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.service.facade.IQpTCOMProvinceService;


public class QpTCOMProvinceAction extends Struts2Action{
	
	private IQpTCOMProvinceService qpTCOMProvinceService;	
	public void setQpTCOMProvinceService(IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}

	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}
	
	private QpTCOMProvince qpTCOMProvince;
	
	private QpTCOMProvinceId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTCOMProvince getter/setter **/
	public QpTCOMProvince getQpTCOMProvince() {
		return this.qpTCOMProvince;
	}
	
	public void setQpTCOMProvince(QpTCOMProvince qpTCOMProvince) {
		this.qpTCOMProvince = qpTCOMProvince;
	}
	/** QpTCOMProvinceId getter/setter **/
	public QpTCOMProvinceId getId() {
		return this.id;
	}
	
	public void setId(QpTCOMProvinceId id) {
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
	private String provId;
	public String getProvId() {
		return this.provId;
	}
	
	public void setProvId(String provId) {
		this.provId = provId;
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
	 * QpTCOMProvince查询，根据qpTCOMProvince带过来的值为查询条件进行查询。
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
			Page resultPage = qpTCOMProvinceService.findByQpTCOMProvince(qpTCOMProvince, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTCOMProvince信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTCOMProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTCOMProvince信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTCOMProvinceService.updateQpTCOMProvince(qpTCOMProvince);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTCOMProvince信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTCOMProvince信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTCOMProvinceService.saveQpTCOMProvince(qpTCOMProvince);
		return SUCCESS;
	}



	/**
	 * 删除QpTCOMProvince信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTCOMProvinceService.deleteByPK(id);
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
	 * 查看QpTCOMProvince信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTCOMProvince = qpTCOMProvinceService.findQpTCOMProvinceByPK(id);
		return SUCCESS;
	}
}
