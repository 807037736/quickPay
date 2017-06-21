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

import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTICCompanyId;
import com.picc.qp.service.facade.IQpTICCompanyService;


public class QpTICCompanyAction extends Struts2Action{
	
	private IQpTICCompanyService qpTICCompanyService;	
	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}
	
	private QpTICCompany qpTICCompany;
	
	private QpTICCompanyId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTICCompany getter/setter **/
	public QpTICCompany getQpTICCompany() {
		return this.qpTICCompany;
	}
	
	public void setQpTICCompany(QpTICCompany qpTICCompany) {
		this.qpTICCompany = qpTICCompany;
	}
	/** QpTICCompanyId getter/setter **/
	public QpTICCompanyId getId() {
		return this.id;
	}
	
	public void setId(QpTICCompanyId id) {
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
	private String coId;
	public String getCoId() {
		return this.coId;
	}
	
	public void setCoId(String coId) {
		this.coId = coId;
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
	 * QpTICCompany查询，根据qpTICCompany带过来的值为查询条件进行查询。
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
			Page resultPage = qpTICCompanyService.findByQpTICCompany(qpTICCompany, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTICCompany信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTICCompany = qpTICCompanyService.findQpTICCompanyByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTICCompany信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTICCompanyService.updateQpTICCompany(qpTICCompany);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTICCompany信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTICCompany信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTICCompanyService.saveQpTICCompany(qpTICCompany);
		return SUCCESS;
	}



	/**
	 * 删除QpTICCompany信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTICCompanyService.deleteByPK(id);
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
	 * 查看QpTICCompany信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTICCompany = qpTICCompanyService.findQpTICCompanyByPK(id);
		return SUCCESS;
	}
}
