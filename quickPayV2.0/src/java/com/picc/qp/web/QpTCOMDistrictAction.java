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

import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMDistrictId;
import com.picc.qp.service.facade.IQpTCOMDistrictService;


public class QpTCOMDistrictAction extends Struts2Action{
	
	private IQpTCOMDistrictService qpTCOMDistrictService;	
	public void setQpTCOMDistrictService(IQpTCOMDistrictService qpTCOMDistrictService) {
		this.qpTCOMDistrictService = qpTCOMDistrictService;
	}

	public IQpTCOMDistrictService getQpTCOMDistrictService() {
		return qpTCOMDistrictService;
	}
	
	private QpTCOMDistrict qpTCOMDistrict;
	
	private QpTCOMDistrictId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTCOMDistrict getter/setter **/
	public QpTCOMDistrict getQpTCOMDistrict() {
		return this.qpTCOMDistrict;
	}
	
	public void setQpTCOMDistrict(QpTCOMDistrict qpTCOMDistrict) {
		this.qpTCOMDistrict = qpTCOMDistrict;
	}
	/** QpTCOMDistrictId getter/setter **/
	public QpTCOMDistrictId getId() {
		return this.id;
	}
	
	public void setId(QpTCOMDistrictId id) {
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
	private String districtId;
	public String getDistrictId() {
		return this.districtId;
	}
	
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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
	 * QpTCOMDistrict查询，根据qpTCOMDistrict带过来的值为查询条件进行查询。
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
			Page resultPage = qpTCOMDistrictService.findByQpTCOMDistrict(qpTCOMDistrict, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTCOMDistrict信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTCOMDistrict = qpTCOMDistrictService.findQpTCOMDistrictByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTCOMDistrict信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTCOMDistrictService.updateQpTCOMDistrict(qpTCOMDistrict);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTCOMDistrict信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTCOMDistrict信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTCOMDistrictService.saveQpTCOMDistrict(qpTCOMDistrict);
		return SUCCESS;
	}



	/**
	 * 删除QpTCOMDistrict信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTCOMDistrictService.deleteByPK(id);
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
	 * 查看QpTCOMDistrict信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTCOMDistrict = qpTCOMDistrictService.findQpTCOMDistrictByPK(id);
		return SUCCESS;
	}
}
