/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.qp.schema.model.QpTICDocumentDetail;
import com.picc.qp.schema.model.QpTICDocumentDetailId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.service.facade.IQpTICDocumentDetailService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.schema.vo.SessionUser;


public class QpTICDocumentDetailAction extends Struts2Action{
	
	private IQpTTPFastCenterService qpTTPFastCenterService;
	
	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(
			IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	private IQpTICDocumentDetailService qpTICDocumentDetailService;	
	public void setQpTICDocumentDetailService(IQpTICDocumentDetailService qpTICDocumentDetailService) {
		this.qpTICDocumentDetailService = qpTICDocumentDetailService;
	}

	public IQpTICDocumentDetailService getQpTICDocumentDetailService() {
		return qpTICDocumentDetailService;
	}
	
	private QpTICDocumentDetail qpTICDocumentDetail;
	
	private QpTICDocumentDetailId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTICDocumentDetail getter/setter **/
	public QpTICDocumentDetail getQpTICDocumentDetail() {
		return this.qpTICDocumentDetail;
	}
	
	public void setQpTICDocumentDetail(QpTICDocumentDetail qpTICDocumentDetail) {
		this.qpTICDocumentDetail = qpTICDocumentDetail;
	}
	/** QpTICDocumentDetailId getter/setter **/
	public QpTICDocumentDetailId getId() {
		return this.id;
	}
	
	public void setId(QpTICDocumentDetailId id) {
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
	private String documentNo;
	public String getDocumentNo() {
		return this.documentNo;
	}
	
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	
	
	/****************************Function Start*******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		QpTTPFastCenter fastCenter=new QpTTPFastCenter();
		fastCenter.setValidStatus("1");
		List<QpTTPFastCenter> centers=qpTTPFastCenterService.findByQpTTPFastCenter(fastCenter, 1, 1000).getResult();
		this.getRequest().setAttribute("centers", centers);
		return SUCCESS;
	}
	

	/**
	 * QpTICDocumentDetail查询，根据qpTICDocumentDetail带过来的值为查询条件进行查询。
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
			Page resultPage = qpTICDocumentDetailService.findByQpTICDocumentDetail(qpTICDocumentDetail, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTICDocumentDetail信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTICDocumentDetail = qpTICDocumentDetailService.findQpTICDocumentDetailByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTICDocumentDetail信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTICDocumentDetailService.updateQpTICDocumentDetail(qpTICDocumentDetail);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTICDocumentDetail信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTICDocumentDetail信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		qpTICDocumentDetailService.saveQpTICDocumentDetail(qpTICDocumentDetail);
		return SUCCESS;
	}



	/**
	 * 删除QpTICDocumentDetail信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTICDocumentDetailService.deleteByPK(id);
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
	 * 查看QpTICDocumentDetail信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		
		QpTICDocumentDetailId id = new QpTICDocumentDetailId();
		id.setDocumentNo(documentNo);
		qpTICDocumentDetail = qpTICDocumentDetailService.findQpTICDocumentDetailByPK(id);
		return SUCCESS;
	}
	
	public void modify(){
		Map<String,String> resultMap = new HashMap();
		//查询符合条件的单证记录
		String documentId=getRequest().getParameter("documentId");
		String caseId=getRequest().getParameter("qpTTPCaseCaseId");
		String centerId=getRequest().getParameter("centerId");
		QpTICDocumentDetail documentDetail=new QpTICDocumentDetail();
		try {
			documentDetail=qpTICDocumentDetailService.findDetial(documentId, centerId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		
		//修改相应的单证记录
		if(documentDetail!=null){
			SessionUser sessionUser = this.getUserFromSession();
			String userCode = sessionUser.getUserCode();
			if(!userCode.isEmpty()){
				documentDetail.setUsedPerson(userCode);
			}
			if(!caseId.isEmpty()){
				documentDetail.setBusinessNo(caseId);
			}
			documentDetail.setUsedTime(new Date());
			documentDetail.setDocumentFlag("2");
			try {
				qpTICDocumentDetailService.updateQpTICDocumentDetail(documentDetail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("", e);
			}
			resultMap.put("flag", "Y");
		}else{
			resultMap.put("flag", "N");
			resultMap.put("msg", "找不到您需要使用的单证号，请核对单证号、单证状态、快处中心是否正确！");
		}
        
        this.writeJson(resultMap);
	}
}
