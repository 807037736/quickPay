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

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.model.QpTTPLawId;
import com.picc.qp.service.facade.IQpTTPLawService;


public class QpTTPLawAction extends Struts2Action{
	
	private IQpTTPLawService qpTTPLawService;	
	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}
	
	private QpTTPLaw qpTTPLaw;
	
	private QpTTPLawId id;
	
	/** 操作类型 **/
	private String operateType;
	/** QpTTPLaw getter/setter **/
	public QpTTPLaw getQpTTPLaw() {
		return this.qpTTPLaw;
	}
	
	public void setQpTTPLaw(QpTTPLaw qpTTPLaw) {
		this.qpTTPLaw = qpTTPLaw;
	}
	/** QpTTPLawId getter/setter **/
	public QpTTPLawId getId() {
		return this.id;
	}
	
	public void setId(QpTTPLawId id) {
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
	private String lawId;
	public String getLawId() {
		return this.lawId;
	}
	
	public void setLawId(String lawId) {
		this.lawId = lawId;
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
	 * QpTTPLaw查询，根据qpTTPLaw带过来的值为查询条件进行查询。
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
			Page resultPage = qpTTPLawService.findByQpTTPLaw(qpTTPLaw, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新QpTTPLaw信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新QpTTPLaw信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		qpTTPLawService.updateQpTTPLaw(qpTTPLaw);
		return SUCCESS;
	}


	/**
	 * 准备增加QpTTPLaw信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增QpTTPLaw信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		String lawName = (qpTTPLaw.getLawName()==null?"":qpTTPLaw.getLawName());
		StringBuffer lawNameBuffer = new StringBuffer(lawName);
		lawNameBuffer.append("		第").append(qpTTPLaw.getLawItem()).append("条");
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawSegment())) {
			lawNameBuffer.append("第" + qpTTPLaw.getLawSegment() + "款");
		}
		if(ToolsUtils.notEmpty(qpTTPLaw.getLawSection())) {
			lawNameBuffer.append("第" + qpTTPLaw.getLawSection() + "项");
		}
		
		qpTTPLaw.setLawName(lawNameBuffer.toString());
		qpTTPLawService.saveQpTTPLaw(qpTTPLaw);
		return SUCCESS;
	}



	/**
	 * 删除QpTTPLaw信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				qpTTPLawService.deleteByPK(id);
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
	 * 查看QpTTPLaw信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		qpTTPLaw = qpTTPLawService.findQpTTPLawByPK(id);
		return SUCCESS;
	}
}
