/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.picc.qp.schema.model.QpTCOMHandlePolice;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.service.facade.IQpTComHandlePoliceService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.schema.vo.SessionUser;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;


public class QpTComHandlePoliceAction extends Struts2Action{
	private static final long serialVersionUID = 1L;

	private IQpTComHandlePoliceService qpTComHandlePoliceService;	
	private IQpTTPFastCenterService qpTTPFastCenterService;
	private QpTCOMHandlePolice qpTComHandlePolice;
	private Integer handlePoliceId;
	private String centerId;
	/** 操作类型 **/
	private String operateType;

	/****************************Function Start*******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
		this.getRequest().setAttribute("fastCenterList", fastCenterList);
		return SUCCESS;
	}
	
	public String getListByCenterId() throws Exception {	
		if(centerId != null && !centerId.equals("")){
			QpTCOMHandlePolice query = new QpTCOMHandlePolice();
			query.setCenterId(centerId);
			List<QpTCOMHandlePolice> findByHandlePolice = qpTComHandlePoliceService.findByHandlePolice(query);
			this.writeJson(findByHandlePolice);
		}
		return NONE;
	}

	/**
	 * qpTComHandlePolice查询，根据qpTComHandlePolice带过来的值为查询条件进行查询。
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
			Page resultPage = qpTComHandlePoliceService.getPageByHandlePolice(qpTComHandlePolice, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新qpTComHandlePolice信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
		this.getRequest().setAttribute("fastCenterList", fastCenterList);
		operateType = "update";
		qpTComHandlePolice = qpTComHandlePoliceService.findById(handlePoliceId);
		return SUCCESS;
	}

	/**
	 * 更新qpTComHandlePolice信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		if(StringUtils.isEmpty(qpTComHandlePolice.getCenterId()) ){
			return "false";
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePoliceNO()) ){
			return "false";
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePoliceName()) ){
			return "false";
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePolicePhone()) ){
			return "false";
		}
		
		SessionUser sessionUser = this.getUserFromSession();
		qpTComHandlePolice.setUpdaterCode(sessionUser.getUserCode());
		qpTComHandlePoliceService.updateHandlePolice(qpTComHandlePolice);
		return SUCCESS;
	}


	/**
	 * 准备增加qpTComHandlePolice信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		// 快陪中心
		QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		qpTTPFastCenter.setValidStatus("1");
		List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
		this.getRequest().setAttribute("fastCenterList", fastCenterList);
		operateType = "add";
		return SUCCESS;
	}

	/**
	 * 新增qpTComHandlePolice信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		SessionUser sessionUser = this.getUserFromSession();
		qpTComHandlePolice.setCreateCode(sessionUser.getUserCode());
		qpTComHandlePoliceService.saveHandlePolice(qpTComHandlePolice);
		return SUCCESS;
	}
	public String voildAddParam() throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		StringBuffer buff = new StringBuffer();
		if(StringUtils.isEmpty(qpTComHandlePolice.getCenterId()) ){
			buff.append("快赔中心参数为空;");
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePoliceNO()) ){
			buff.append("民警编号为空;");
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePoliceName()) ){
			buff.append("民警姓名为空;");
		}
		if(StringUtils.isEmpty(qpTComHandlePolice.getHandlePolicePhone()) ){
			buff.append("民警手机号码为空;");
		}
		
		if(buff.length()>0){
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg",buff.toString());
			this.writeAjaxByMap(resultMap);
			return NONE;
		}
		
		QpTCOMHandlePolice query = new QpTCOMHandlePolice();
		query.setHandlePoliceNO(qpTComHandlePolice.getHandlePoliceNO());
		List<QpTCOMHandlePolice> findByHandlePolice = qpTComHandlePoliceService.findByHandlePolice(query);
		if(query != null && findByHandlePolice.size()>0){
			resultMap.put("errorCode", "-2");
			resultMap.put("errorMsg", "警号重复,无法添加");
		}else{
			resultMap.put("errorCode", "0");
			resultMap.put("errorMsg", "校验成功");
		}
		this.writeAjaxByMap(resultMap);
		return NONE;
	}
	

	/**
	 * 删除qpTComHandlePolice信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(handlePoliceId!=0){
				qpTComHandlePoliceService.deleteByPK(handlePoliceId);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}

	/**
	 * 查看qpTComHandlePolice信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTComHandlePolice = qpTComHandlePoliceService.findById(handlePoliceId);
		return SUCCESS;
	}

	public IQpTComHandlePoliceService getqpTComHandlePoliceService() {
		return qpTComHandlePoliceService;
	}

	public void setqpTComHandlePoliceService(IQpTComHandlePoliceService qpTComHandlePoliceService) {
		this.qpTComHandlePoliceService = qpTComHandlePoliceService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getHandlePoliceId() {
		return handlePoliceId;
	}

	public void setHandlePoliceId(Integer handlePoliceId) {
		this.handlePoliceId = handlePoliceId;
	}

	public IQpTComHandlePoliceService getQpTComHandlePoliceService() {
		return qpTComHandlePoliceService;
	}

	public void setQpTComHandlePoliceService(IQpTComHandlePoliceService qpTComHandlePoliceService) {
		this.qpTComHandlePoliceService = qpTComHandlePoliceService;
	}
	
	public QpTCOMHandlePolice getQpTComHandlePolice() {
		return qpTComHandlePolice;
	}

	public void setQpTComHandlePolice(QpTCOMHandlePolice qpTComHandlePolice) {
		this.qpTComHandlePolice = qpTComHandlePolice;
	}
	
	public String getCenterId() {
		return centerId;
	}
	
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
}
