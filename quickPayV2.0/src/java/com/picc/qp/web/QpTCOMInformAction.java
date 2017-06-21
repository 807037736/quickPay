package com.picc.qp.web;


import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.List;

import com.picc.qp.schema.model.QpTCOMInform;
import com.picc.qp.service.facade.IQpTCOMInformService;

public class QpTCOMInformAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	
	private IQpTCOMInformService qpTCOMInformService;
	private QpTCOMInform qpTCOMInform;
	private int informId;
	private String creator;
	private String createTime;
	private String title;
	private String content;
	private String type;
	private String state;
	private String operateType;
	private String endTime;
	private String startTime;
	
	public String prepareQuery(){
		return SUCCESS;
	}
	public String query() throws Exception{
		List<QpTCOMInform> checkQpTCOMInforms = qpTCOMInformService.findQpTCOMInformByState("0");
		if(null != checkQpTCOMInforms){
			for(QpTCOMInform inform : checkQpTCOMInforms){
				if(qpTCOMInformService.isTimeOut(new Date(),inform.getEndTime())){
					inform.setState("2");
					qpTCOMInformService.updateQpTCOMInform(inform);
				}
			}
		}
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		QpTCOMInform qpTCOMInform = new QpTCOMInform();
		qpTCOMInform.setTitle(title);
		qpTCOMInform.setCreator(creator);
		try {
			Page resultPage = qpTCOMInformService.findQpTCOMInformByQpTCOMInform(qpTCOMInform, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	public String prepareUpdate() throws Exception{
		operateType = "update";
		QpTCOMInform qpTCOMInform = new QpTCOMInform();
		qpTCOMInform.setInformId(informId);
		List<QpTCOMInform> informs=  qpTCOMInformService.findByQpTCOMInform(qpTCOMInform);
		if(null != informs && informs.size()>0){
			qpTCOMInform = informs.get(0);
		}
		content = qpTCOMInform.getContent();
		createTime = qpTCOMInform.getCreateTime().substring(0, 19);
		creator = qpTCOMInform.getCreator();
		endTime = qpTCOMInform.getEndTime().substring(0, 19);
		if(null != qpTCOMInform.getStartTime() && !"".equals(qpTCOMInform.getStartTime()) ){
			startTime = qpTCOMInform.getStartTime().substring(0, 19);
		}
		state = qpTCOMInform.getState();
		title = qpTCOMInform.getTitle();
		type = qpTCOMInform.getType();
		
		return SUCCESS;
	}
	public String update() throws Exception{
		if(state=="1" || "1".equals(state)){
			qpTCOMInformService.updateBySQL("1");
		}
		QpTCOMInform qpTCOMInform = new QpTCOMInform();
		qpTCOMInform.setCreator(creator);
		qpTCOMInform.setTitle(title);
		qpTCOMInform.setContent(content);
		qpTCOMInform.setInformId(informId);
		qpTCOMInform.setEndTime(endTime);
		if(null==state || "".equals(state)){
			qpTCOMInform.setState("0");
		}else{
			qpTCOMInform.setState(state);
		}
		if(null==type || "".equals(type)){
			qpTCOMInform.setType("0");
		}else{
			qpTCOMInform.setType(type);
		}
		if(null!=startTime && !"".equals(startTime)){
			qpTCOMInform.setStartTime(startTime);
		}
		qpTCOMInformService.updateQpTCOMInform(qpTCOMInform);
		return SUCCESS;
	}
	
	public String prepareAdd(){
		operateType = "add";
		return SUCCESS;
	}
	public String add() throws Exception{
		if(state=="1" || "1".equals(state)){
			qpTCOMInformService.updateBySQL("1");
		}
		QpTCOMInform qpTCOMInform = new QpTCOMInform();
		qpTCOMInform.setCreator(creator);
		qpTCOMInform.setTitle(title);
		qpTCOMInform.setContent(content);
		qpTCOMInform.setEndTime(endTime);
		if(null==startTime || "".equals(startTime)){
			
		}else{
			qpTCOMInform.setStartTime(startTime);
		}
		if(null==state || "".equals(state)){
			qpTCOMInform.setState("0");
		}else{
			qpTCOMInform.setState(state);
		}
		if(null==type || "".equals(type)){
			qpTCOMInform.setType("0");
		}else{
			qpTCOMInform.setType(type);
		}
		qpTCOMInformService.addQpTCOMInform(qpTCOMInform);
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		qpTCOMInformService.deleteQpTCOMInform(informId);
		return NONE;
	}
	
	public String view(){
		operateType="view";
		return SUCCESS;
	}
	
	public IQpTCOMInformService getQpTCOMInformService() {
		return qpTCOMInformService;
	}
	public void setQpTCOMInformService(IQpTCOMInformService qpTCOMInformService) {
		this.qpTCOMInformService = qpTCOMInformService;
	}
	public QpTCOMInform getQpTCOMInform() {
		return qpTCOMInform;
	}
	public void setQpTCOMInform(QpTCOMInform qpTCOMInform) {
		this.qpTCOMInform = qpTCOMInform;
	}
	public int getInformId() {
		return informId;
	}
	public void setInformId(int informId) {
		this.informId = informId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
