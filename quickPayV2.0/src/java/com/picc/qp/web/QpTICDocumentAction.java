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

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICDocument;
import com.picc.qp.schema.model.QpTICDocumentDetail;
import com.picc.qp.schema.model.QpTICDocumentDetailId;
import com.picc.qp.schema.model.QpTICDocumentId;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.service.facade.IQpTICDocumentDetailService;
import com.picc.qp.service.facade.IQpTICDocumentService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserService;


public class QpTICDocumentAction extends Struts2Action{
	
	private IQpTICDocumentDetailService qpTICDocumentDetailService;
	
	private IQpTTPFastCenterService qpTTPFastCenterService;
	
	private IUmTUserService umTUserService;
	
	
	

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(
			IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public IQpTICDocumentDetailService getQpTICDocumentDetailService() {
		return qpTICDocumentDetailService;
	}

	public void setQpTICDocumentDetailService(
			IQpTICDocumentDetailService qpTICDocumentDetailService) {
		this.qpTICDocumentDetailService = qpTICDocumentDetailService;
	}

	private IQpTICDocumentService qpTICDocumentService;	
	public void setQpTICDocumentService(IQpTICDocumentService qpTICDocumentService) {
		this.qpTICDocumentService = qpTICDocumentService;
	}

	public IQpTICDocumentService getQpTICDocumentService() {
		return qpTICDocumentService;
	}
	
	private IQpTICDocumentDetailService iQpTICDocumentDetailService;
	
	public IQpTICDocumentDetailService getiQpTICDocumentDetailService() {
		return iQpTICDocumentDetailService;
	}

	public void setiQpTICDocumentDetailService(
			IQpTICDocumentDetailService iQpTICDocumentDetailService) {
		this.iQpTICDocumentDetailService = iQpTICDocumentDetailService;
	}
	private QpTICDocument qpTICDocument;
	
	private QpTICDocumentId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTICDocument getter/setter **/
	public QpTICDocument getQpTICDocument() {
		return this.qpTICDocument;
	}
	
	public void setQpTICDocument(QpTICDocument qpTICDocument) {
		this.qpTICDocument = qpTICDocument;
	}
	/** QpTICDocumentId getter/setter **/
	public QpTICDocumentId getId() {
		return this.id;
	}
	
	public void setId(QpTICDocumentId id) {
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
	private String batchNo;
	public String getBatchNo() {
		return this.batchNo;
	}
	
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	 * QpTICDocument查询，根据qpTICDocument带过来的值为查询条件进行查询。
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
			Page resultPage = qpTICDocumentService.findByQpTICDocument(qpTICDocument, page, rows);
			List<QpTICDocument> documents = resultPage.getResult();
			if(documents.size()!=0){
				for(QpTICDocument document : documents){
					if(ToolsUtils.notEmpty(document.getGrantor())){
						UmTUserId userId = new UmTUserId();
						userId.setUserCode(document.getGrantor());
						UmTUser user = umTUserService.findUmTUserByPK(userId);
						if(user != null){
							document.setGrantor(user.getUserName());
						}
					}
//					qpTFLOW.setValidStatus(qpTFLOW.getValidStatus()=="0" ? "无效" : "有效");
					document.setValidStatus(document.getValidStatus().equals("0") ? "无效":"有效");
				}
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
	 * 准备更新QpTICDocument信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTICDocument = qpTICDocumentService.findQpTICDocumentByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTICDocument信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		qpTICDocumentService.updateQpTICDocument(qpTICDocument);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTICDocument信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		QpTTPFastCenter fastCenter=new QpTTPFastCenter();
		fastCenter.setValidStatus("1");
		List<QpTTPFastCenter> centers=qpTTPFastCenterService.findByQpTTPFastCenter(fastCenter, 1, 1000).getResult();
		this.getRequest().setAttribute("centers", centers);
		return SUCCESS;
	}
	
	/**
	 * 新增QpTICDocument信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		
		//添加单证
		String startNo="";
		String endNo="";
		String last=qpTICDocumentService.findEndNo();
		Integer start=Integer.parseInt(last)+1;
		Integer end=0;
		if(!"".equals(last)){
			if(qpTICDocument.getGrantCount()!=null){
				end=start+qpTICDocument.getGrantCount().intValue()-1;
			}
//			startNo=last.substring(0, last.length()-start.toString().length())+start.toString();
//			endNo=last.substring(0, last.length()-end.toString().length())+end.toString();
			startNo=qpTICDocumentService.toNumber(start);
			endNo=qpTICDocumentService.toNumber(end);
		}
		SessionUser sessionUser = this.getUserFromSession();
		String userCode = sessionUser.getUserCode();
		qpTICDocument.setGrantor(userCode);
		qpTICDocument.setGrantTime(new Date());
		qpTICDocument.setStartNo(startNo);
		qpTICDocument.setEndNo(endNo);
		qpTICDocument.setValidStatus("1");
		qpTICDocumentService.saveQpTICDocument(qpTICDocument);
		
		//添加单证明细
		QpTICDocumentDetail documentDetail = null;
		QpTICDocumentDetailId detailId=null;
/*		for(Integer i=0 ; i<qpTICDocument.getGrantCount().intValue() ; i++){
			documentDetail = new QpTICDocumentDetail();
			detailId=new QpTICDocumentDetailId();
			Integer top=start+i;
			String documentId=qpTICDocument.getStartNo().substring(0, qpTICDocument.getStartNo().length()-top.toString().length())+top.toString();
			detailId.setDocumentNo(documentId);
			documentDetail.setId(detailId);
			documentDetail.setBatchNo(qpTICDocument.getId().getBatchNo());
			documentDetail.setCenterId(qpTICDocument.getCenterId());
		
//			documentDetail.setId();
//			documentDetail.setUsedPerson()
			documentDetail.setValidStatus("0");
			documentDetail.setDocumentFlag("0");
			qpTICDocumentDetailService.saveQpTICDocumentDetail(documentDetail);
		}*/
		
		for(start.intValue();start<=end;start++){
			documentDetail = new QpTICDocumentDetail();
			detailId=new QpTICDocumentDetailId();
			String documentId=qpTICDocumentService.toNumber(start);
			detailId.setDocumentNo(documentId);
			documentDetail.setId(detailId);
			documentDetail.setBatchNo(qpTICDocument.getId().getBatchNo());
			documentDetail.setCenterId(qpTICDocument.getCenterId());
			documentDetail.setValidStatus("0");
			documentDetail.setDocumentFlag("0");
			qpTICDocumentDetailService.saveQpTICDocumentDetail(documentDetail);
		}
		
		
		//拿到当前最后一个单证号，转成int，并加一
		
		//

		
		return SUCCESS;
	}



	/**
	 * 删除QpTICDocument信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTICDocumentService.deleteByPK(id);
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
	 * 查看QpTICDocument信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTICDocument = qpTICDocumentService.findQpTICDocumentByPK(id);
		return SUCCESS;
	}
	

}
