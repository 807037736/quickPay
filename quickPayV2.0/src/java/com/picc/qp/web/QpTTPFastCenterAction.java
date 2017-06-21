/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCenterId;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCOMCityService;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.schema.vo.SessionUser;


public class QpTTPFastCenterAction extends Struts2Action{
	
	private IQpTTPFastCenterService qpTTPFastCenterService;	
	
	private IQpTCOMCityService qpTCOMCityService;
	
	private IQpTCommonService qpTCommonService;
	
	public IQpTCOMCityService getQpTCOMCityService() {
		return qpTCOMCityService;
	}

	public void setQpTCOMCityService(IQpTCOMCityService qpTCOMCityService) {
		this.qpTCOMCityService = qpTCOMCityService;
	}

	public void setQpTTPFastCenterService(IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}
	
	
	public IQpTCommonService getQpTCommonService() {
	    return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
	    this.qpTCommonService = qpTCommonService;
	}


	private QpTTPFastCenter qpTTPFastCenter;
	
	private QpTTPFastCenterId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTTPFastCenter getter/setter **/
	public QpTTPFastCenter getQpTTPFastCenter() {
		return this.qpTTPFastCenter;
	}
	
	public void setQpTTPFastCenter(QpTTPFastCenter qpTTPFastCenter) {
		this.qpTTPFastCenter = qpTTPFastCenter;
	}
	/** QpTTPFastCenterId getter/setter **/
	public QpTTPFastCenterId getId() {
		return this.id;
	}
	
	public void setId(QpTTPFastCenterId id) {
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
	private String centerId;
	public String getCenterId() {
		return this.centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}


	
	/****************************Function Start*******************************/
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
	    /*QpTCOMCity qpTCOMCity =new QpTCOMCity();
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		*/
	    	HttpServletRequest httpServletRequest = this.getRequest();
	    	//快赔点所属地市 
	  	List<QpSelectDataVo> citiesList =  qpTCommonService.getSelectData("cities");
	  	httpServletRequest.setAttribute("citiesList", citiesList);
	  		
		SessionUser sessionUser =getUserFromSession(); 
		//(SessionUser)ServletActionContext.getRequest().getSession().getAttribute("SessionUser");
		String userCityId = sessionUser.getCity();
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		this.getRequest().setAttribute("userCityId", userCityId);
		return SUCCESS;
	}
	
	/**
	 * QpTTPFastCenter查询，根据qpTTPFastCenter带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setCharacterEncoding("utf-8");
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {
			Page resultPage = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTTPFastCenter信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		if(id == null) {
			id = new QpTTPFastCenterId();
			id.setCenterId(centerId);
		}
		qpTTPFastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(id);
		
		QpTCOMCity qpTCOMCity =new QpTCOMCity();
		SessionUser sessionUser =getUserFromSession(); 
		String userCityId = sessionUser.getCity();
		this.getRequest().setAttribute("userCityId", userCityId);
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		
		//快赔点所属地市 
		List<QpSelectDataVo> citiesList =  qpTCommonService.getSelectData("cities");
		this.getRequest().setAttribute("citiesList", citiesList);
		
		return SUCCESS;
	}
	
	/**
	 * 更新QpTTPFastCenter信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		qpTTPFastCenterService.updateQpTTPFastCenter(qpTTPFastCenter);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTTPFastCenter信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		/*QpTCOMCity qpTCOMCity =new QpTCOMCity();
		qpTCOMCity.setProvId("430000");
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		return SUCCESS;*/
		HttpServletRequest httpServletRequest = this.getRequest();
		//快赔点所属地市 
		List<QpSelectDataVo> citiesList =  qpTCommonService.getSelectData("cities");
		httpServletRequest.setAttribute("citiesList", citiesList);
		
		SessionUser sessionUser =getUserFromSession(); 
		//(SessionUser)ServletActionContext.getRequest().getSession().getAttribute("SessionUser");
	    String userCityId = sessionUser.getCity();
	    QpTCOMCity qpTCOMCity =new QpTCOMCity();
		List<QpTCOMCity> citylist = qpTCOMCityService.findByQpTCOMCity(qpTCOMCity, 1, 10000).getResult();
		this.getRequest().setAttribute("citylist", citylist);
		this.getRequest().setAttribute("userCityId", userCityId);
		return SUCCESS;
	}
	
	/**
	 * 新增QpTTPFastCenter信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTTPFastCenterService.saveQpTTPFastCenter(qpTTPFastCenter);
		return SUCCESS;
	}



	/**
	 * 删除QpTTPFastCenter信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id == null) {
				id = new QpTTPFastCenterId();
				id.setCenterId(centerId);
			}
		     qpTTPFastCenterService.deleteByPK(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}



	/**
	 * 查看QpTTPFastCenter信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTTPFastCenter = qpTTPFastCenterService.findQpTTPFastCenterByPK(id);
		return SUCCESS;
	}
}
