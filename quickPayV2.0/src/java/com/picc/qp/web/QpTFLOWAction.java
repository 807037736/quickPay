/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2016
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMCityId;
import com.picc.qp.schema.model.QpTCOMProvince;
import com.picc.qp.schema.model.QpTCOMProvinceId;
import com.picc.qp.schema.model.QpTFLOW;
import com.picc.qp.schema.model.QpTFLOWId;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCOMProvinceService;
import com.picc.qp.service.facade.IQpTFLOWService;


public class QpTFLOWAction extends Struts2Action{
	
	private IQpTCOMCityService qpTCOMCityService;
	
	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}

	private IQpTCOMProvinceService qpTCOMProvinceService;

	public IQpTCOMProvinceService getQpTCOMProvinceService() {
		return qpTCOMProvinceService;
	}

	public void setQpTCOMProvinceService(IQpTCOMProvinceService qpTCOMProvinceService) {
		this.qpTCOMProvinceService = qpTCOMProvinceService;
	}

	private IQpTFLOWService qpTFLOWService;	
	public void setQpTFLOWService(IQpTFLOWService qpTFLOWService) {
		this.qpTFLOWService = qpTFLOWService;
	}

	public IQpTFLOWService getQpTFLOWService() {
		return qpTFLOWService;
	}
	
	private QpTFLOW qpTFLOW;
	
	private QpTFLOWId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTFLOW getter/setter **/
	public QpTFLOW getQpTFLOW() {
		return this.qpTFLOW;
	}
	
	public void setQpTFLOW(QpTFLOW qpTFLOW) {
		this.qpTFLOW = qpTFLOW;
	}
	/** QpTFLOWId getter/setter **/
	public QpTFLOWId getId() {
		return this.id;
	}
	
	public void setId(QpTFLOWId id) {
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
	private String flowId;
	public String getFlowId() {
		return this.flowId;
	}
	
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
		List<QpTCOMCity> provicelist = qpTCOMProvinceService.findByQpTCOMProvince(qpTCOMProvince,1, 10000).getResult();
		this.getRequest().setAttribute("provicelist", provicelist);
		
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		
		return SUCCESS;
	}
	
	/**
	 * QpTFLOW查询，根据qpTFLOW带过来的值为查询条件进行查询。
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
			Page resultPage = qpTFLOWService.findByQpTFLOW(qpTFLOW, page, rows);
			List<QpTFLOW> list=resultPage.getResult();
			for(QpTFLOW qpTFLOW : list){
				QpTCOMProvinceId qpTCOMProvinceId = new QpTCOMProvinceId();
				qpTCOMProvinceId.setProvId(qpTFLOW.getProviceId());
				QpTCOMProvince province = qpTCOMProvinceService.findQpTCOMProvinceByPK(qpTCOMProvinceId);
				qpTFLOW.setProviceId(province.getProvName());
				
				QpTCOMCityId qpTCOMCityId = new QpTCOMCityId();
				qpTCOMCityId.setCityId(qpTFLOW.getCityId());
				QpTCOMCity city = qpTCOMCityService.findQpTCOMCityByPK(qpTCOMCityId);
				qpTFLOW.setCityId(city.getCityName());
				
				qpTFLOW.setPolicePro("0".equals(qpTFLOW.getPolicePro()) ? "无":"有");
				qpTFLOW.setValidStatus("0".equals(qpTFLOW.getValidStatus())? "无效" : "有效");
//				qpTFLOW.setPolicePro(qpTFLOW.getPolicePro()=="0" ? "无":"有");
//				qpTFLOW.setValidStatus(qpTFLOW.getValidStatus()=="0" ? "无效" : "有效");
			}
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTFLOW信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		
		/*QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
		List<QpTCOMCity> provicelist = qpTCOMProvinceService.findByQpTCOMProvince(qpTCOMProvince,1, 10000).getResult();
		this.getRequest().setAttribute("provicelist", provicelist);
		
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);*/
		
		qpTFLOW = qpTFLOWService.findQpTFLOWByPK(id);
		
		QpTCOMProvinceId provinceId = new QpTCOMProvinceId();
		provinceId.setProvId(qpTFLOW.getProviceId());
		QpTCOMProvince province= qpTCOMProvinceService.findQpTCOMProvinceByPK(provinceId);
		qpTFLOW.setProviceId(province.getProvName());
		
		QpTCOMCityId cityId=new QpTCOMCityId();
		cityId.setCityId(qpTFLOW.getCityId());
		QpTCOMCity city = qpTCOMCityService.findQpTCOMCityByPK(cityId);
		qpTFLOW.setCityId(city.getCityName());
		return SUCCESS;
	}
	
	/**
	 * 更新QpTFLOW信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTFLOWService.updateQpTFLOW(qpTFLOW);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTFLOW信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		QpTCOMProvince qpTCOMProvince = new QpTCOMProvince();
		List<QpTCOMCity> provicelist = qpTCOMProvinceService.findByQpTCOMProvince(qpTCOMProvince,1, 10000).getResult();
		this.getRequest().setAttribute("provicelist", provicelist);
		
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		return SUCCESS;
	}
	
	/**
	 * 新增QpTFLOW信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTFLOWService.saveQpTFLOW(qpTFLOW);
		return SUCCESS;
	}



	/**
	 * 删除QpTFLOW信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTFLOWService.deleteByPK(id);
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
	 * 查看QpTFLOW信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTFLOW = qpTFLOWService.findQpTFLOWByPK(id);
		return SUCCESS;
	}
}
