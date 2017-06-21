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

import com.picc.qp.schema.model.QpTCOMRoad;
import com.picc.qp.schema.model.QpTCOMRoadId;
import com.picc.qp.service.facade.IQpTCOMRoadService;


public class QpTCOMRoadAction extends Struts2Action{
	
	private IQpTCOMRoadService qpTCOMRoadService;	
	public void setQpTCOMRoadService(IQpTCOMRoadService qpTCOMRoadService) {
		this.qpTCOMRoadService = qpTCOMRoadService;
	}

	public IQpTCOMRoadService getQpTCOMRoadService() {
		return qpTCOMRoadService;
	}
	
	private QpTCOMRoad qpTCOMRoad;
	
	private QpTCOMRoadId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTCOMRoad getter/setter **/
	public QpTCOMRoad getQpTCOMRoad() {
		return this.qpTCOMRoad;
	}
	
	public void setQpTCOMRoad(QpTCOMRoad qpTCOMRoad) {
		this.qpTCOMRoad = qpTCOMRoad;
	}
	/** QpTCOMRoadId getter/setter **/
	public QpTCOMRoadId getId() {
		return this.id;
	}
	
	public void setId(QpTCOMRoadId id) {
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
	private String roadId;
	public String getRoadId() {
		return this.roadId;
	}
	
	public void setRoadId(String roadId) {
		this.roadId = roadId;
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
	 * QpTCOMRoad查询，根据qpTCOMRoad带过来的值为查询条件进行查询。
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
			Page resultPage = qpTCOMRoadService.findByQpTCOMRoad(qpTCOMRoad, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTCOMRoad信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTCOMRoad = qpTCOMRoadService.findQpTCOMRoadByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTCOMRoad信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTCOMRoadService.updateQpTCOMRoad(qpTCOMRoad);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTCOMRoad信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTCOMRoad信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTCOMRoadService.saveQpTCOMRoad(qpTCOMRoad);
		return SUCCESS;
	}



	/**
	 * 删除QpTCOMRoad信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTCOMRoadService.deleteByPK(id);
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
	 * 查看QpTCOMRoad信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTCOMRoad = qpTCOMRoadService.findQpTCOMRoadByPK(id);
		return SUCCESS;
	}
}
